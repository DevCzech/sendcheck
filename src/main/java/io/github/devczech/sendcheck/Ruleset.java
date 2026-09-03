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
            compiledRules.add(compiledRuleFactory.apply(line));
        }

        return new Ruleset(compiledRules);
    }

    public ScanResult scan(String text) {
        final List<String> categories = new ArrayList<>();
        final List<String> names = new ArrayList<>();

        for (CompiledRule rule : this.rules) {
            if (rule.find(text)) {
                categories.add(rule.getCategory());
                names.add(rule.getName());
            }
        }

        return new ScanResultImpl(categories, names);
    }

    public ScanResult scan(Path inputFile) {
        throw new UnsupportedOperationException("scan(Path)");
    }
}
