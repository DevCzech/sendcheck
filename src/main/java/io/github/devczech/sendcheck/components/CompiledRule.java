package io.github.devczech.sendcheck.components;

public interface CompiledRule {
    int find(String input);

    String getCategory();

    String getName();
}
