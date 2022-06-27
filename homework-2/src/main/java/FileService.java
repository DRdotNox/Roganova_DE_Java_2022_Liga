
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import enums.TypeOfFile;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileService {
  public void clean(String filename) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(filename));
    writer.writeNext(new String[0]);

  }

  public void updateFile(String filename, Task record, String oldValue, String newValue)
      throws IOException, CsvException {
    CSVReader reader = new CSVReader(new FileReader(filename));
    List<String[]> csvBody = reader.readAll();
    String[] rowToChange = record.toString().concat(", ").split(",");
    CSVWriter writer = new CSVWriter(new FileWriter(filename));
    for (int k = 1; k < rowToChange.length; k++) {
     csvBody.forEach(System.out::println);
      if (k == 3) k++;
      if (rowToChange[k].equals(oldValue) ) {

        for (String[] strings : csvBody) {

          String[] tempString = strings;
          if (strings.length ==5) {
            String[] status = {record.getStatus().toString()};

            tempString = Stream.concat(Arrays.stream(strings), Arrays.stream(status))
                .toArray(
                    s -> (String[]) Array.newInstance(status.getClass().getComponentType(), s));
          }

          if (tempString[0].equals(String.valueOf(record.getId()))) {
            tempString[k] = newValue;
          }
          writer.writeNext(tempString);
        }
      }
    }
    reader.close();
    writer.flush();
    writer.close();
  }


  public <T> List <T> readAllRecords(String filename, TypeOfFile fileType){
    List<T> recordList;
    if(fileType.equals(TypeOfFile.USERS)) recordList = (List<T>) Parser.parseCSVforUser(filename);
    else recordList = (List<T>) Parser.parseCSVforTasks(filename);
    return recordList;
  }

  public void addToFile(String filename, Task record) throws IOException {
    CSVWriter writer = new CSVWriter(new FileWriter(filename, true));
    System.out.println("record.toString() = " + record.toString());
    System.out.println("record.getDate().toString() = " + record.getDate().toString());
    writer.writeNext(record.toString().split(","));
    writer.close();
  }
  }