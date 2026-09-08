package io.github.devczech.sendcheck;

import io.github.devczech.sendcheck.components.CompiledRule;
import io.github.devczech.sendcheck.components.ScanResult;
import io.github.devczech.sendcheck.components.ScanResultImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class Ruleset {
    private final List<CompiledRule> rules;

    public Ruleset(List<CompiledRule> rules) {
        this.rules = rules;
    }

    public static Ruleset load(Path rulesFile, Function<String, CompiledRule> compiledRuleFactory) {
        List<String> lines;
        try {
            lines = new ArrayList<>(Files.readAllLines(rulesFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final List<CompiledRule> compiledRules = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("#")) { // Lines starting with a # are comments
                continue;
            }

            if (line.isBlank()) { // Blank lines should be skipped to avoid complicating the parsing logic
                continue;
            }

            compiledRules.add(compiledRuleFactory.apply(line));
        }

        return new Ruleset(compiledRules);
    }

    public List<ScanResult> scan(String text) {
        final List<ScanResult> scanResults = new ArrayList<>();
        final String[] lines = text.split(System.lineSeparator());

        for (CompiledRule rule : rules) {
            for (int i = 0; i < lines.length; i++) {
                final String line = lines[i];
                final List<Integer> matchIndices = rule.find(line);
                for (int matchIndex : matchIndices) {
                    scanResults.add(new ScanResultImpl(rule.getCategory(), rule.getName(), i, matchIndex));
                }
            }
        }

        return scanResults;
    }

    public ScanResult scan(Path inputFile) {
        throw new UnsupportedOperationException("scan(Path)");
    }
}
