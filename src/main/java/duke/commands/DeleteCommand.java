package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{

    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showToUser("Noted. I've removed this task:");
        ui.showToUser("    " + idx + "." + tasks.printTask(idx - 1));
        tasks.deleteTask(idx - 1);
        try {
            storage.deleteTaskInFile(storage.getFilePath(), idx - 1);
        } catch(IOException e) {
            throw new DukeException("Error deleting");
        }
        ui.showToUser("Now you have " + tasks.getSize() + " duke.tasks in the list.");
    }
}