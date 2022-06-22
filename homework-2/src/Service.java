import java.util.List;
import java.util.stream.Collectors;

public class Service {

  public static User findUserById(List<User> userList, int id) {
    return userList.stream()
        .filter(user -> user.getId() == id)
        .findFirst()
        .orElse(null);
  }

  public static void showUsersTasks(User user, Status status) {
    System.out.println("Задания пользователя " + user.getName());
    System.out.println("---------------------------------------------------------------------------"
        + "------------------------------");
    System.out.format("%10s%20s%30s%20s%10s\n", "id", "Заголовок", "Описание", "Дедлайн", "Статус");
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
    taskList.forEach(task -> System.out.format("%10d%20s%30s%20s%10s\n", task.getId(),
        task.getHeader(), task.getDescription(), task.getData().toString(),
        task.getStatus().toString()));
    System.out.println("---------------------------------------------------------------------------"
        + "------------------------------");
  }

  public static void changeStatusOfTheTask(User user, int taskId, Status status){
    List<Task> taskList = user.getTasks();
    Task task = taskList.stream().filter(t -> t.getId() == taskId).findFirst().orElse(null);
    if (task == null) {
      System.out.println("\n Такого задания нет в списке!\n");
      return;
    }
    task.setStatus(status);
  }

  public static void showAllUsers(List<User> userList){
    System.out.println("Все пользователи:");
    userList.forEach(user -> System.out.println(user.getId() + "." + user.getName()));
  }
}
