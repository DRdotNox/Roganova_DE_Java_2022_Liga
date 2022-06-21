import java.time.LocalDate;
import java.util.List;

public class Task {

  private int id;
  private int userId;
  private String header;
  private String description;
  private LocalDate data;
  private Status status;

  public Task() {
  }

  public Task(int id, String header, String description, int userId, LocalDate data) {
    this.id = id;
    this.header = header;
    this.description = description;
    this.userId = userId;
    this.data = data;
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

  public LocalDate getData() {
    return data;
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

  @Override
  public String toString() {
    return getId() + " " + getHeader() + " " + getDescription() + " " + getData() + " " + getStatus();
  }
}

