import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Menu {

  private List<String> mainOptions = new ArrayList<>(
      List.of("1 Все задания", "2 NEW ", "3 IN_PROGRESS", "4 DONE", "5 Поменять статус задачи",
          "6 Вернуться к списку пользователей", "7 Выход"));
  private List<String> subOptions = new ArrayList<>(List.of("1 NEW ", "2 IN_PROGRESS", "3 DONE", "4 Вернуться"));

  Map <Integer, Status> statusMap;

  public void showMenu(List<User> userList){
    statusMap = createStatusMap();

    boolean state = true;
    Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

    while (state) {

      Service.showAllUsers(userList);
      System.out.print("Введите id пользователя, чтобы просмотреть его задания: ");
      int id = Integer.parseInt(in.nextLine());
      User user = Service.findUserById(userList,id);
      Service.showUsersTasks(user,null);

      state = showTaskMenu(in, user);
    }
  }

  public Map <Integer, Status> createStatusMap(){
    Map <Integer, Status> statusMap = new HashMap<>();
    statusMap.put(1, null);
    statusMap.put(2, Status.NEW);
    statusMap.put(3, Status.IN_PROGRESS);
    statusMap.put(4, Status.DONE);
    return statusMap;
  }

  public void showChangeStatusMenu(Scanner in, User user){
    System.out.println("Введите id задачи:");
    int taskId = Integer.parseInt(in.nextLine());

    System.out.println("Какой статус присвоить задаче?");
    subOptions.forEach(System.out::println);

    int status = Integer.parseInt(in.nextLine());

    if (status>= 1 && status <=4){
      if (status !=4) Service.changeStatusOfTheTask(user, taskId, statusMap.get(status+1));
      Service.showUsersTasks(user, null);
    }
  }
  public Boolean showTaskMenu(Scanner in, User user){
    boolean innerState = true;
    while (innerState) {
      mainOptions.forEach(System.out::println);

      int option = Integer.parseInt(in.nextLine());
      if (option >= 1 && option <= 4) {
        Service.showUsersTasks(user, statusMap.get(option));
      } else if (option == 5) {
        showChangeStatusMenu(in, user);
      } else if (option == 6)
        innerState = false;
      else {
        return false;
      }
    }
    return true;
  }
}
