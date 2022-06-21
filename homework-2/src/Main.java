import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

    List<User> userList = parseCSVforUser(args[0]);
    parseCSVAndFillUserTasks(userList,args[1]);

    Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

    boolean state = true;
    boolean innerState = true;

    while (state) {
      System.out.println("Все пользователи:");
      userList.forEach(user -> System.out.println(user.getId() + "." + user.getName()));

      System.out.print("Введите id пользователя, чтобы просмотреть его задания: ");
      int id = Integer.parseInt(in.nextLine());

      User user = User.findUserById(userList,id);
      showUsersTasks(user,null);

      while (innerState) {
        System.out.println("Menu");
        System.out.println("1 Все задания 2 NEW 3 IN_PROGRESS 4 DONE");
        System.out.println("5 Поменять статус задачи");
        System.out.println("6 Вернуться к списку пользователей");
        System.out.println("7 Выход");

        String option = in.nextLine();
        switch (option) {
          case "1" -> showUsersTasks(user, null);
          case "2" -> showUsersTasks(user, Status.NEW);
          case "3" -> showUsersTasks(user, Status.IN_PROGRESS);
          case "4" -> showUsersTasks(user, Status.DONE);
          case "5" -> {
            System.out.println("Введите id задачи:");
            int taskId = Integer.parseInt(in.nextLine());
            System.out.println("Какой статус присвоить задаче?");
            System.out.println("1 NEW");
            System.out.println("2 IN_PROGRESS");
            System.out.println("3 Done");
            System.out.println("4 Вернуться");
            int status = Integer.parseInt(in.nextLine());
            switch (status) {
              case 1 -> {
                changeStatusOfTheTask(user, taskId, Status.NEW);
                showUsersTasks(user, null);
              }
              case 2 -> {
                changeStatusOfTheTask(user, taskId, Status.IN_PROGRESS);
                showUsersTasks(user, null);
              }
              case 3 -> {
                changeStatusOfTheTask(user, taskId, Status.DONE);
                showUsersTasks(user, null);
              }
              case 4 -> showUsersTasks(user, null);
              default -> {
                state = false;
                innerState = false;
              }
            }
          }
          case "6" -> innerState = false;
          default -> {
            state = false;
            innerState = false;
          }
        }
      }
    }


  }

  public static List<User> parseCSVforUser(String filename){
    List<User> userList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        userList.add(new User(Integer.parseInt(values[0].trim()),values[1]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return userList;
  }

  public static List<Task> parseCSVforTasks(String filename){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    List<Task> taskList = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        taskList.add(new Task (Integer.parseInt(values[0].trim()), values[1], values[2],
            Integer.parseInt(values[3].trim()), LocalDate.parse(values[4], formatter)));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return taskList;
  }

  public static void parseCSVAndFillUserTasks(List <User> userList, String filename){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    List<Task> taskList = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        User user = User.findUserById(userList,Integer.parseInt(values[3].trim()));
        user.addTask(new Task (Integer.parseInt(values[0].trim()), values[1], values[2],
            Integer.parseInt(values[3].trim()), LocalDate.parse(values[4], formatter)));

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void showUsersTasks(User user, Status status){
    System.out.println("Задания пользователя " + user.getName());
    System.out.format("%10s%20s%30s%20s%10s\n", "id", "Заголовок", "Описание","Дедлайн","Статус");
    List<Task> taskList;

    if(status == null){
      taskList = user.getTasks();}
    else{
      taskList = user.getTasks().stream().filter(task -> task.getStatus().equals(status)).collect(
          Collectors.toList());
    }

    if (taskList.isEmpty()) {
      System.out.println("Нет задач");
      return;
    }
    taskList.forEach(task -> System.out.format("%10d%20s%30s%20s%10s\n", task.getId(),
        task.getHeader(), task.getDescription(),task.getData().toString(),task.getStatus().toString()));
  }

  public static void changeStatusOfTheTask(User user, int taskId, Status status){
    List<Task> taskList = user.getTasks();
    taskList.get(taskId).setStatus(status);
  }

}
