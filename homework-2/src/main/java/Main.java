import com.opencsv.exceptions.CsvException;
import enums.TypeOfFile;
import java.io.IOException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException, CsvException {

    List<User> userList;
    List<Task> taskList;

    String taskFile;
    String userFile;

    FileService fileService = new FileService();

    if (args.length == 1 || args.length == 0) {
      System.out.println("Вы не указали файл(ы) для считывания");
      System.out.println("Будут использованы тестовые csv\n");

      taskFile = "homework-2/tasks.csv";
      userFile = "homework-2/users.csv";
    }

    else {
      taskFile = args[1];
      userFile = args[0];
    }
    userList = fileService.readAllRecords(userFile, TypeOfFile.USERS);
    taskList = fileService.readAllRecords(taskFile, TypeOfFile.TASKS);
    DataService.addTasksToUsers(userList,taskList);

    Menu menu = new Menu(userList,taskList, taskFile);
    menu.showMenu();
  }
}
