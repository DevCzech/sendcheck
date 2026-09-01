package io.github.devczech.sendcheck;

import io.github.devczech.sendcheck.components.CompiledRule;
import io.github.devczech.sendcheck.components.ScanResult;

import java.nio.file.Path;
import java.util.List;

public final class Ruleset {
    private final List<CompiledRule> rules;

    public Ruleset(List<CompiledRule> rules) {
        this.rules = rules;
    }

    public static Ruleset load(Path rulesFile) {
        throw new UnsupportedOperationException("load");
    }

    public ScanResult scan(String text) {
        throw new UnsupportedOperationException("scan(String)");
    }

    public ScanResult scan(Path inputFile) {
        throw new UnsupportedOperationException("scan(Path)");
    }
}
