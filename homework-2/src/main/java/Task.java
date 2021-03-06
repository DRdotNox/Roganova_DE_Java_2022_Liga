import enums.StatusOfTask;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {

  private int id;
  private int userId;
  private String header;
  private String description;
  private LocalDate date;
  private StatusOfTask status;

  public Task() {
  }

  public Task(int id, String header, String description, int userId, LocalDate date) {
    this.id = id;
    this.header = header;
    this.description = description;
    this.userId = userId;
    this.date = date;
    this.status = StatusOfTask.NEW;
  }

  public Task(int id, String header, String description, int userId, LocalDate date, String status) {
    this.id = id;
    this.header = header;
    this.description = description;
    this.userId = userId;
    this.date = date;
    this.status = StatusOfTask.valueOf(status);
  }

  public int getId() {
    return id;
  }

  public String getHeader() {
    return header;
  }

  public String getDescription() {
    return description;
  }

  public LocalDate getDate() {
    return date;
  }

  public StatusOfTask getStatus() {
    return status;
  }

  public void setStatus(StatusOfTask status) {
    this.status = status;
  }

  public int getUserId() {
    return userId;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  @Override
  public String toString() {
    String formattedDate = this.getDate().format(DateTimeFormatter
        .ofPattern("dd.MM.yyyy"));
    return getId() + "," + getHeader() + "," + getDescription() + "," + getUserId()+ "," + formattedDate + "," + getStatus();
  }
}

