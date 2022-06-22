import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Menu {

  private final List<String> mainOptions;
  private final List<String> subOptions;

  Map <Integer, Status> statusMap;

  public Menu() {
    this.mainOptions = new ArrayList<>(
        List.of("1 Все задания 2 Только NEW  3 Только IN_PROGRESS 4 Только DONE",
            "5 Поменять статус задачи", "6 Вернуться к списку пользователей", "7 Выход"));
    this.subOptions = new ArrayList<>(
        List.of("1 NEW ", "2 IN_PROGRESS", "3 DONE", "4 Вернуться"));
    this.statusMap = createStatusMap();
  }

  public void showMenu(List<User> userList){

    Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

    boolean state = true;
    while (state) {
      User user = null;
        while (user == null){

          Service.showAllUsers(userList);
          System.out.print("Введите id пользователя, чтобы просмотреть его задания: ");
          int id = 0;
          try {
            id = Integer.parseInt(in.nextLine());
          } catch (NumberFormatException e) {
            id=-1;
          }
          user = Service.findUserById(userList,id);
          if(user == null)System.out.println("Нет пользователя с таким id!\nПопробуйте еще раз");
        }

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
    int taskId;
    try {
      taskId = Integer.parseInt(in.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Неверный формат id\nПопробуйте еще раз");
      Service.showUsersTasks(user,null);
      taskId = -1;
    }
    if (taskId < 0) return;

    System.out.println("Какой статус присвоить задаче?");
    subOptions.forEach(System.out::println);

    int status = 0;
    try {
      status = Integer.parseInt(in.nextLine());
    } catch (NumberFormatException e) {
      return;
    }

    if (status>= 1 && status <=4){
      if (status !=4) {
        Service.changeStatusOfTheTask(user, taskId, statusMap.get(status+1));
      }
      Service.showUsersTasks(user, null);
    }
  }
  public Boolean showTaskMenu(Scanner in, User user){
    boolean innerState = true;
    while (innerState) {
      mainOptions.forEach(System.out::println);

      int option = 0;
      try {
        option = Integer.parseInt(in.nextLine());
      } catch (NumberFormatException e) {
        return false;
      }

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
