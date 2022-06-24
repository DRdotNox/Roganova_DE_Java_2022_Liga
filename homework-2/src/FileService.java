import enums.FileType;
import java.util.List;

public class FileService {
  public void clean(String filename) {

  }

  public <T> void saveFile(String filename, List<T> recordList){

  }

  public <T> List <T> read(String filename, FileType fileType){
    List<T> recordList;
    if(fileType.equals(FileType.USERS)) recordList = (List<T>) Parser.parseCSVforUser(filename);
    else recordList = (List<T>) Parser.parseCSVforTasks(filename);
    return recordList;
  }
}
