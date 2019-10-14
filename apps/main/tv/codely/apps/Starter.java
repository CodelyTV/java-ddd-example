package tv.codely.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ConfigurableApplicationContext;
import tv.codely.apps.mooc.MoocApplication;
import tv.codely.shared.infrastructure.cli.ConsoleCommand;

import java.util.Arrays;
import java.util.HashMap;

public class Starter {
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new RuntimeException("There are not enough arguments");
        }

        String  applicationName = args[0];
        String  commandName     = args[1];
        boolean isApiCommand    = commandName.equals("api");

        ensureApplicationExist(applicationName);
        ensureCommandExist(applicationName, commandName);

        Class<?> applicationClass = applications().get(applicationName);

        SpringApplication app = new SpringApplication(applicationClass);

        if (!isApiCommand) {
            app.setWebApplicationType(WebApplicationType.NONE);
        }

        ConfigurableApplicationContext context = app.run(args);

        if (!isApiCommand) {
            ConsoleCommand command = (ConsoleCommand) context.getBean(
                commands().get(commandKey(applicationName, commandName))
            );

            command.execute(Arrays.copyOfRange(args, 2, args.length));
        }
    }

    private static void ensureApplicationExist(String applicationName) {
        if (!applications().containsKey(applicationName)) {
            throw new RuntimeException(String.format(
                "The application <%s> doesn't exist. Valids:\n- %s",
                applicationName,
                String.join("\n- ", applications().keySet())
            ));
        }
    }

    private static void ensureCommandExist(String applicationName, String commandName) {
        if (!"api".equals(commandName) && !commands().containsKey(commandKey(applicationName, commandName))) {
            throw new RuntimeException(String.format(
                "The command <%s> for application <%s> doesn't exist. Valids (application.command):\n- api\n- %s",
                commandName,
                applicationName,
                String.join("\n- ", commands().keySet())
            ));
        }
    }

    private static HashMap<String, Class<?>> applications() {
        HashMap<String, Class<?>> applications = new HashMap<>();

        applications.put("mooc", MoocApplication.class);

        return applications;
    }

    private static HashMap<String, Class<?>> commands() {
        HashMap<String, Class<?>> commands = new HashMap<>();

        return commands;
    }

    private static String commandKey(String contextName, String commandName) {
        return String.format("%s.%s", contextName, commandName);
    }
}
