package io.github.devczech.sendcheck.components;

import java.util.List;

public interface CompiledRule {
    List<Integer> find(String input);

    String getCategory();

    String getName();
}
