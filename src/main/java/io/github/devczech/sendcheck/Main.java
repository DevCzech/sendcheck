package io.github.devczech.sendcheck;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> helpArgs = List.of("--help", "-h");

        if(args.length < 1 || Arrays.stream(args).anyMatch(helpArgs::contains)) {
            System.out.println("Usage: sendcheck ruleFile scanFile");
        }
    }
}
