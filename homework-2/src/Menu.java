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
        List.of("1 Все задания 2 Только NEW  3 Только IN_PROGRESS 4 Только DONE","5 Удалить задачу",
            "6 Поменять статус задачи", "7 Вернуться к списку пользователей", "8 Выход"));
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
          String input = in.nextLine();
          int id = Validation.checkInput(input);
          user = Service.findUserById(userList,id);
          if(user == null)System.out.println("Нет пользователя с таким id!\nПопробуйте еще раз");
        }

      Service.showUsersTasks(user,null);

      state = showTaskMenu(in, user);
    }
  }

  public void showChangeStatusMenu(Scanner in, User user){
    if (user.getTasks().isEmpty()){
      System.out.println("Список задач пуст!");
      Service.showUsersTasks(user, null);
      return;
    }
    System.out.println("Введите id задачи:");
    String input = in.nextLine();
    int taskId = Validation.checkInput(input);
    if (taskId < 0){
      Service.showUsersTasks(user, null);
      return;
    }
    Task task = Service.findTaskById(user, taskId);
    if (task == null){
      Service.showUsersTasks(user, null);
      return;
    }

    System.out.println("Какой статус присвоить задаче?");
    subOptions.forEach(System.out::println);

    input = in.nextLine();
    int status = Validation.checkInput(input);
    if(status<0) {
      Service.showUsersTasks(user, null);
      return;
    }
    if (status>= 1 && status <=4){
      if (status !=4) {
        Service.changeStatusOfTheTask(task, statusMap.get(status+1));
      }
      Service.showUsersTasks(user, null);
    }
  }

  public Boolean showTaskMenu(Scanner in, User user){
    boolean innerState = true;
    while (innerState) {
      mainOptions.forEach(System.out::println);
      String input = in.nextLine();
      int option = Validation.checkInput(input);
      switch (option) {
        case 1, 2, 3, 4 -> Service.showUsersTasks(user, statusMap.get(option));
        case 5 -> {
          if (user.getTasks().isEmpty()) {
            System.out.println("Список задач пуст!");
            Service.showUsersTasks(user, null);
            showTaskMenu(in, user);
          } else if (user.getTasks().size() == 1) {
            user.getTasks().remove(0);
          } else {
            System.out.println("Введите id задачи:");
            input = in.nextLine();
            int taskId = Validation.checkInput(input);
            if (taskId < 0) {
              Service.showUsersTasks(user, null);
              showTaskMenu(in, user);
            }
            Task task = Service.findTaskById(user, taskId);
            if (task == null) {
              Service.showUsersTasks(user, null);
              showTaskMenu(in, user);
            }
            user.getTasks().remove(task);
          }
          Service.showUsersTasks(user, null);
        }
        case 6 -> showChangeStatusMenu(in, user);
        case 7 -> innerState = false;
        default -> {
          return false;
        }
      }

    }
    return true;
  }

  public Map <Integer, Status> createStatusMap(){
    Map <Integer, Status> statusMap = new HashMap<>();
    statusMap.put(1, null);
    statusMap.put(2, Status.NEW);
    statusMap.put(3, Status.IN_PROGRESS);
    statusMap.put(4, Status.DONE);
    return statusMap;
  }


}