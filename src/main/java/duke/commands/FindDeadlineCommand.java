package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindDeadlineCommand extends Command {
    protected String userInput;

    public FindDeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            LocalDate by = LocalDate.parse(userInput);
            ArrayList<Task> deadlines = tasks.getDeadlineBefore(by);
            if (deadlines.size() == 0) {
                ui.showToUser("There's no deadlines before " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            } else {
                ui.showToUser("Here are the deadlines before "
                        + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
            }

            for (int i = 0; i < deadlines.size(); i++) {
                ui.showToUser(deadlines.get(i).toString());
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date!");
        }
    }
}
