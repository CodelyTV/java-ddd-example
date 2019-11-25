package tv.codely.shared.infrastructure.cli;

import tv.codely.shared.domain.Service;

@Service
public abstract class ConsoleCommand {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED   = "\u001B[31m";
    private static final String ANSI_CYAN  = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";

    abstract public void execute(String[] args);

    protected void log(String text) {
        System.out.println(String.format("%s%s%s", ANSI_GREEN, text, ANSI_RESET));
    }

    protected void info(String text) {
        System.out.println(String.format("%s%s%s", ANSI_CYAN, text, ANSI_RESET));
    }

    protected void error(String text) {
        System.out.println(String.format("%s%s%s", ANSI_RED, text, ANSI_RESET));
    }
}
