package io.github.devczech.sendcheck;

import io.github.devczech.sendcheck.components.CompiledRule;
import io.github.devczech.sendcheck.components.ScanResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Ruleset {
    private final List<CompiledRule> rules;

    public Ruleset(List<CompiledRule> rules) {
        this.rules = rules;
    }

    public static Ruleset load(Path rulesFile) {
        List<String> lines;
        try {
            lines = new ArrayList<>(Files.readAllLines(rulesFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Loaded lines:");
        for (String line : lines) {
            System.out.println(line);
        }

        return new Ruleset(Collections.emptyList());
    }

    public ScanResult scan(String text) {
        throw new UnsupportedOperationException("scan(String)");
    }

    public ScanResult scan(Path inputFile) {
        throw new UnsupportedOperationException("scan(Path)");
    }
}
