package io.github.devczech.sendcheck.components;

import java.util.List;

public interface ScanResult {
    List<String> getCategories();

    List<String> getNames();
}
