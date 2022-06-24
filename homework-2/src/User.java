import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {

  private final int id;
  private final String name;
  List<Task> tasks;

  public User(int id, String name) {
    this.id = id;
    this.name = name;
    this.tasks = new ArrayList<>();
  }


  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public void addTask(Task task) {
    this.tasks.add(task);
  }

  public void showTask(int taskId) {
    Task task = this.getTasks().stream().filter(t -> t.getId() == taskId).findFirst().orElse(null);
    System.out.println("Выбранная задача ");
    System.out.println("---------------------------------------------------------------------------"
        + "------------------------------------------------------");
    System.out.format("%-10s%-20s%-70s%-20s%-20s\n", "id", "Заголовок", "Описание", "Дедлайн",
        "Статус");
    System.out.format("%-10d%-20s%-70s%-20s%-20s\n", task.getId(),
        task.getHeader(), task.getDescription(), task.getDate().toString(),
        task.getStatus().toString());
    System.out.println("---------------------------------------------------------------------------"
        + "------------------------------------------------------");
  }
  }
