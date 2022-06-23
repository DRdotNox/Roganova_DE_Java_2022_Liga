import java.util.List;

public class Main {

  public static void main(String[] args) {

    List<User> userList;

    if (args.length == 1 || args.length == 0) {
      System.out.println("Вы не указали файл(ы) для считывания");
      System.out.println("Будут использованы тестовые csv\n");

      userList = Parser.parseCSVforUser("homework-2/users.csv");
      Parser.parseCSVAndFillUserTasks(userList,"homework-2/tasks.csv");
    }
    else {
      userList = Parser.parseCSVforUser(args[0]);
      Parser.parseCSVAndFillUserTasks(userList,args[1]);
    }

    Menu menu = new Menu();
    menu.showMenu(userList);
  }
}
