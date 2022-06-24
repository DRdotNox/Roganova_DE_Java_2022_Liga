import enums.Status;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Menu {

  private final List<String> mainOptions;
  private final List<String> subOptionsForChangingStatus;
  private final List<String> subOptionsForOneTask;
  private final List<String> subOptionsForEditTask;

  List<User> userList;
  List<Task> taskList;

  Map <Integer, Status> statusMap;

  public Menu(List<User> userList, List<Task> taskList) {
    this.userList = userList;
    this.taskList = taskList;

    this.mainOptions = new ArrayList<>(
        List.of("1 Все задания 2 Только NEW  3 Только IN_PROGRESS 4 Только DONE","5 Выбрать задачу","6 Добавить задачу",
            "7 Вернуться к списку пользователей", "8 Выход"));
    this.subOptionsForChangingStatus = new ArrayList<>(
        List.of("1 NEW ", "2 IN_PROGRESS", "3 DONE", "4 Вернуться"));
    this.subOptionsForOneTask = new ArrayList<>(
        List.of("1 Редактировать задачу ", "2 Удалить задачу", "3 Поменять статус задаче", "4 Вернуться"));
    this.subOptionsForEditTask = new ArrayList<>(List.of("1 Изменить заголовок ", "2 Изменить описание",
        "3 Изменить дату", "4 Изменить статус", "5 Вернуться"));
    this.statusMap = createStatusMap();

  }

  public void showMenu(){

    Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

    boolean state = true;
    while (state) {
      User user = null;
        while (user == null){
          DataService.showAllUsers(userList);
          System.out.print("Введите id пользователя, чтобы просмотреть его задания: ");
          String input = in.nextLine();
          int id = Validation.checkInput(input);
          user = DataService.findUserById(userList,id);
          if(user == null)System.out.println("Нет пользователя с таким id!\nПопробуйте еще раз");
        }

      DataService.showUsersTasks(user,null);

      state = showALLTaskMenu(in, user);
    }
  }

  public void showChangeStatusMenu(Scanner in, User user, int taskId){
    Task task = DataService.findTaskById(user, taskId);
    if (task == null){
      DataService.showUsersTasks(user, null);
      return;
    }

    System.out.println("Какой статус присвоить задаче?");
    subOptionsForChangingStatus.forEach(System.out::println);

    String input = in.nextLine();
    int status = Validation.checkInput(input);
    if(status<0) {
      DataService.showUsersTasks(user, null);
      return;
    }
    if (status>= 1 && status <=4){
      if (status !=4) {
        DataService.changeStatusOfTheTask(task, statusMap.get(status+1));
      }
      DataService.showUsersTasks(user, null);
    }
  }

  public Boolean showALLTaskMenu(Scanner in, User user){
    boolean innerState = true;
    while (innerState) {
      mainOptions.forEach(System.out::println);
      String input = in.nextLine();
      int option = Validation.checkInput(input);
      switch (option) {
        case 1, 2, 3, 4 -> DataService.showUsersTasks(user, statusMap.get(option));
        case 5 ->{
          if (user.getTasks().isEmpty()){
            System.out.println("Список задач пуст!");
            DataService.showUsersTasks(user, null);
            break;
          }
          System.out.println("Введите id задачи:");
          input = in.nextLine();
          int taskId = Validation.checkInput(input);
          if (taskId < 0){
            DataService.showUsersTasks(user, null);
            break;
          }
          showOneTaskMenu(in,user, taskId);
        }
        case 6 -> {
          DataService.addNewTask(user, taskList);
          DataService.showUsersTasks(user, null);
        }
        case 7 -> innerState = false;
        default -> {
          return false;
        }
      }

    }
    return true;
  }

  public void showOneTaskMenu(Scanner in, User user, int taskId){
    String input;
    boolean innerState = true;
    while (innerState) {
      user.showTask(taskId);
      subOptionsForOneTask.forEach(System.out::println);
      input = in.nextLine();
      int option = Validation.checkInput(input);

      switch (option) {
        case 1 -> showEditTaskMenu(in, taskList, user, taskId);
        case 2 -> DataService.deleteTask(taskList, user, taskId);
        case 3 -> showChangeStatusMenu(in, user, taskId);
        case 4 -> innerState = false;
      }
    }
    DataService.showUsersTasks(user, null);
  }

  private void showEditTaskMenu(Scanner in, List<Task> taskList, User user, int taskId) {
    String input;
    boolean innerState = true;

    Task task = DataService.findTaskById(user,taskId);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    while (innerState) {
      user.showTask(taskId);
      subOptionsForEditTask.forEach(System.out::println);
      input = in.nextLine();
      int option = Validation.checkInput(input);

      switch (option) {
        case 1 -> {
          System.out.println("Старый заголовок:" + task.getHeader());
          System.out.println("Введите новоый заголовок: ");
          input = in.nextLine();
          task.setHeader(input);
          taskList.stream().filter(t -> t.getId() == taskId)
              .findFirst().orElse(null)
              .setHeader(input);
        }
        case 2 -> {
          System.out.println("Старое описание:" + task.getDescription());
          System.out.println("Введите новое");
          input = in.nextLine();
          task.setDescription(input);
          taskList.stream().filter(t -> t.getId() == taskId)
              .findFirst().orElse(null)
              .setDescription(input);
        }
        case 3 -> {
          System.out.println("Старая дата:" + task.getDate());
          System.out.println("Введите новую");
          input = in.nextLine();
          task.setDate(LocalDate.parse(input, formatter));
          taskList.stream().filter(t -> t.getId() == taskId)
              .findFirst().orElse(null)
              .setDate(LocalDate.parse(input, formatter));
        }
        case 4 -> showChangeStatusMenu(in, user, taskId);
        default -> innerState = false;
      }
    }
    DataService.showUsersTasks(user, null);
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