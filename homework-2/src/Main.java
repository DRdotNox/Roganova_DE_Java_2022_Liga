import enums.FileType;
import java.util.List;

public class Main {

  public static void main(String[] args) {

    List<User> userList;
    List<Task> taskList;

    FileService fileService = new FileService();

    if (args.length == 1 || args.length == 0) {
      System.out.println("Вы не указали файл(ы) для считывания");
      System.out.println("Будут использованы тестовые csv\n");

      userList = fileService.read("homework-2/users.csv", FileType.USERS);
      taskList = fileService.read("homework-2/tasks.csv", FileType.TASKS);
      DataService.addTasksToUsers(userList,taskList);
    }
    else {
      userList = fileService.read(args[0], FileType.USERS);
      taskList = fileService.read(args[1], FileType.TASKS);
      DataService.addTasksToUsers(userList,taskList);
    }

    Menu menu = new Menu(userList,taskList);
    menu.showMenu();
  }
}
