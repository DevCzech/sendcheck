package io.github.devczech.sendcheck.components;

import java.util.List;

public class ScanResultImpl implements ScanResult {
    private final List<String> categories;
    private final List<String> names;

    public ScanResultImpl(List<String> categories, List<String> names) {
        this.categories = categories;
        this.names = names;
    }

    @Override
    public List<String> getCategories() {
        return this.categories;
    }

    @Override
    public List<String> getNames() {
        return this.names;
    }
}
