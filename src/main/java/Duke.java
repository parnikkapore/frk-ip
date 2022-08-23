import duke.MessagefulException;
import duke.modules.Todos;
import duke.modules.todos.Deadline;
import duke.modules.todos.Event;
import duke.modules.todos.Todo;

import java.util.Scanner;

import static duke.IOFormat.say;

/**
 * Entrypoint for the Duke chatbot.
 */
public class Duke {
    /**
     * Entrypoint.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        Todos todos = new Todos();

        // intro string
        say("Hello, Duke here! What can I do for you?");

        boolean fExit = false;
        while (!fExit) {
            String line = stdin.nextLine();
            Scanner scanner = new Scanner(line);
            String command = scanner.hasNext() ? scanner.next() : "";

            try {
                switch (command) {
                case "":
                    say("Sorry, I didn't catch that?");
                    break;
                case "bye":
                    say("OK. See you next time! *boings away*");
                    fExit = true;
                    break;
                case "todo":
                    todos.cmdAdd(scanner, Todo::fromChat);
                    break;
                case "deadline":
                    todos.cmdAdd(scanner, Deadline::fromChat);
                    break;
                case "event":
                    todos.cmdAdd(scanner, Event::fromChat);
                    break;
                case "list":
                    todos.cmdList();
                    break;
                case "mark":
                    todos.cmdMark(scanner);
                    break;
                case "unmark":
                    todos.cmdUnmark(scanner);
                    break;
                case "delete":
                    todos.cmdDelete(scanner);
                    break;
                default:
                    say("Sorry, I didn't understand what you said :(");
                    break;
                }
            } catch (MessagefulException e){
                say(e.message());
            }
        }
    }
}
