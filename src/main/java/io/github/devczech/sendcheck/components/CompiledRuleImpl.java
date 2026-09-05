package io.github.devczech.sendcheck.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
        try {
            final int firstSpaceIndex = ruleLine.indexOf(' ');
            final String name = ruleLine.substring(0, firstSpaceIndex);
            final int secondSpaceIndex = ruleLine.indexOf(' ', firstSpaceIndex + 1);
            final String category = ruleLine.substring(firstSpaceIndex + 1, secondSpaceIndex);
            final String pattern = ruleLine.substring(secondSpaceIndex + 1);

            return new CompiledRuleImpl(name, category, Pattern.compile(pattern));
        } catch (PatternSyntaxException | StringIndexOutOfBoundsException | NullPointerException e) {
            throw new IllegalArgumentException("Could not parse rule line", e);
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
