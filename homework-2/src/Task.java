import enums.Status;
import java.time.LocalDate;

public class Task {

  private int id;
  private int userId;
  private String header;
  private String description;
  private LocalDate date;
  private Status status;

  public Task() {
  }

  public Task(int id, String header, String description, int userId, LocalDate date) {
    this.id = id;
    this.header = header;
    this.description = description;
    this.userId = userId;
    this.date = date;
    this.status = Status.NEW;
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

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
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
    return getId() + " " + getHeader() + " " + getDescription() + " " + getDate() + " " + getStatus();
  }
}

