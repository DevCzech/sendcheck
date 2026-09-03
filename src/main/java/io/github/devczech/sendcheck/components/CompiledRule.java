package io.github.devczech.sendcheck.components;

public interface CompiledRule {
    boolean find(String input);

    String getCategory();

    String getName();
}
