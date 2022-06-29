import enums.StatusOfTask;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DataService {

  public static User findUserById(List<User> userList, int id) {
    return userList.stream()
        .filter(user -> user.getId() == id)
        .findFirst()
        .orElse(null);
  }

  public static void showUsersTasks(User user, StatusOfTask status) {
    System.out.println("Задания пользователя " + user.getName());
    System.out.println("---------------------------------------------------------------------------"
        + "------------------------------------------------------");
    System.out.format("%-10s%-20s%-70s%-20s%-20s\n", "id", "Заголовок", "Описание", "Дедлайн", "Статус");

    List<Task> taskList;
    if (status == null) {
      taskList = user.getTasks();
    } else {
      taskList = user.getTasks().stream().filter(task -> task.getStatus().equals(status)).collect(
          Collectors.toList());
    }
    if (taskList.isEmpty()) {
      System.out.println("Нет задач\n");
      return;
    }

    taskList.forEach(task -> System.out.format("%-10d%-20s%-70s%-20s%-20s\n", task.getId(),
        task.getHeader(), task.getDescription(), task.getDate().toString(),
        task.getStatus().toString()));
    System.out.println("---------------------------------------------------------------------------"
        + "------------------------------------------------------");
  }

  public static void changeStatusOfTheTask(Task task, StatusOfTask status){
    task.setStatus(status);
  }

  public static void showAllUsers(List<User> userList){
    System.out.println("Все пользователи:");
    userList.forEach(user -> System.out.println(user.getId() + "." + user.getName()));
  }

  public static Task findTaskById(User user, int taskId) {
    List<Task> taskList = user.getTasks();
    Task task = taskList.stream().filter(t -> t.getId() == taskId).findFirst().orElse(null);
    if (task == null) {
      System.out.println("\n Такого задания нет в списке!\n");
    }
    return task;
  }

  public static void addTasksToUsers(List<User> userList, List<Task> taskList){
    taskList.forEach(task -> {
      User user = DataService.findUserById(userList,task.getUserId());
      user.addTask(task);
    });
  }

  public static Task addNewTask(User user, List<Task> taskList){
    Scanner in = new Scanner(System.in);
    int lastId = taskList.get(taskList.size()-1).getId();

    System.out.println("Введите заголовок:");
    String header = in.nextLine();

    System.out.println("Введите описание:");
    String description = in.nextLine();

    System.out.println("Введите дату дедлайна (dd.MM.yyyy):");
    String stringDate = in.nextLine();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate date = LocalDate.parse(stringDate, formatter);

    Task task = new Task(lastId+1, header, description, user.getId(), date);

    user.addTask(task);
    taskList.add(task);
    return task;
  }

  public static void deleteTask(List<Task> taskList, User user, int taskId){
    Task task = DataService.findTaskById(user, taskId);
    if (task == null) {
      DataService.showUsersTasks(user, null);
      System.out.println("Такого задания нет");
      return;
    }
    user.getTasks().remove(task);
    taskList.remove(task);
  }

  public static void deleteAllTasks(List<Task> taskList){
    taskList.clear();
  }
}
