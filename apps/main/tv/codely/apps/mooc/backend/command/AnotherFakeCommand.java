package tv.codely.apps.mooc.backend.command;

import tv.codely.shared.infrastructure.cli.ConsoleCommand;

public final class AnotherFakeCommand extends ConsoleCommand {
    @Override
    public void execute(String[] args) {
        info("This is another fake command!");
    }
}
