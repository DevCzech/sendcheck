package io.github.devczech.sendcheck.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompiledRuleImpl implements CompiledRule {
    private final String name;
    private final String category;
    private final Pattern pattern;

    public CompiledRuleImpl(String name, String category, Pattern pattern) {
        this.name = name;
        this.category = category;
        this.pattern = pattern;
    }

    public static CompiledRuleImpl parse(String ruleLine) {
        String[] split = ruleLine.split(",");
        if (split.length == 3) {
            return new CompiledRuleImpl(split[0].strip(), split[1].strip(), Pattern.compile(split[2].strip()));
        } else {
            throw new IllegalArgumentException("Could not parse rule line");
        }
    }

    @Override
    public int find(String input) {
        final Matcher matcher = this.pattern.matcher(input);
        return matcher.find() ? matcher.start() : -1;
    }

    @Override
    public String getCategory() {
        return this.category;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
