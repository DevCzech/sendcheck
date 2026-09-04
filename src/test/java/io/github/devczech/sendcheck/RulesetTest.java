package io.github.devczech.sendcheck;

import io.github.devczech.sendcheck.components.CompiledRuleImpl;
import io.github.devczech.sendcheck.components.ScanResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

class RulesetTest {

    private Ruleset ruleset;

    @BeforeEach
    void setup() {
        this.ruleset = Ruleset.load(Path.of("src/test/resources/rules"), CompiledRuleImpl::parse);

        Assertions.assertNotNull(this.ruleset);
    }

    @Test
    void scanSsn() {
        final String testInput = "xyz012-01-0124xyz";
        final List<ScanResult> results = ruleset.scan(testInput);

        Assertions.assertNotNull(results);

        Assertions.assertEquals(1, results.size());

        ScanResult result = results.get(0);

        String category = result.getCategory();
        String name = result.getName();
        long line = result.getLine();
        long position = result.getPosition();

        Assertions.assertEquals("identity", category);
        Assertions.assertEquals("ssn", name);
        Assertions.assertEquals(0, line);
        Assertions.assertEquals(3, position);
    }

    @Test
    void scanVisa() {
        final String testInput = "xyz4123412341234123xyz"; // Visa card numbers start with a 4
        final List<ScanResult> results = ruleset.scan(testInput);

        Assertions.assertNotNull(results);

        Assertions.assertEquals(1, results.size());

        ScanResult result = results.get(0);

        String category = result.getCategory();
        String name = result.getName();
        long line = result.getLine();
        long position = result.getPosition();

        Assertions.assertEquals("finance", category);
        Assertions.assertEquals("visa", name);
        Assertions.assertEquals(0, line);
        Assertions.assertEquals(3, position);
    }
}