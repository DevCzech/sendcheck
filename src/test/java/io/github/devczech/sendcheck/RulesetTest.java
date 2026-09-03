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
    void scan() {
        final String testInput = "xyz012-01-0124xyz";
        final ScanResult result = ruleset.scan(testInput);

        Assertions.assertNotNull(result);

        List<String> categories = result.getCategories();
        List<String> names = result.getNames();

        Assertions.assertTrue(categories.contains("identity"));
        Assertions.assertTrue(names.contains("ssn"));
    }
}