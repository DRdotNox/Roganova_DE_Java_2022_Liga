import java.util.ArrayList;
import java.util.List;

public class User {

  private int id;
  private String name;
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

  public static User findUserById(List<User> userList, int id) {
    return userList.stream()
        .filter(user -> user.getId() == id)
        .findFirst()
        .orElse(null);
  }


}
