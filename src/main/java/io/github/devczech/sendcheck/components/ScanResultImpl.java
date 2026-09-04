package io.github.devczech.sendcheck.components;

public class ScanResultImpl implements ScanResult {
    private final String category;
    private final String name;
    private final long line;
    private final long position;

    public ScanResultImpl(String category, String name, long line, long position) {
        this.category = category;
        this.name = name;
        this.line = line;
        this.position = position;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getLine() {
        return line;
    }

    @Override
    public long getPosition() {
        return position;
    }
}
