package io.github.devczech.sendcheck.components;

public interface ScanResult {
    String getCategory();

    String getName();

    long getLine();

    long getPosition();
}
