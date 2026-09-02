package io.github.devczech.sendcheck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class RulesetTest {

    private Ruleset ruleset;

    @Test
    void load() {
        Ruleset loadedRuleset = Ruleset.load(Path.of("src/test/resources/rules"));

        Assertions.assertNotNull(loadedRuleset);
    }

    @Test
    void scan() {
    }

    @Test
    void testScan() {
    }
}