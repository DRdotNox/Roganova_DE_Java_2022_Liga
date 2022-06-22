import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Parser {
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
        User user = Service.findUserById(userList,Integer.parseInt(values[3].trim()));
        user.addTask(new Task (Integer.parseInt(values[0].trim()), values[1].trim(), values[2].trim(),
            Integer.parseInt(values[3].trim()), LocalDate.parse(values[4].trim(), formatter)));

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
