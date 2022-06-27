
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import enums.TypeOfFile;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileService {
  public void clean(String filename) {

  }

  public void updateFile(String filename, Task record, String oldValue, String newValue)
      throws IOException, CsvException {
    CSVReader reader = new CSVReader(new FileReader(filename));
    List<String[]> csvBody = reader.readAll();
    String[] rowToChange = record.toString().concat(", ").split(",");
    System.out.println("rowToChange = " + rowToChange);
    CSVWriter writer = new CSVWriter(new FileWriter(filename));
    for (int k = 1; k < rowToChange.length; k++) {
     csvBody.stream().forEach(System.out::println);
      if (k == 3) k++;
      if (rowToChange[k].equals(oldValue) ) {

        for (String[] strings : csvBody) {
          ArrayList<String> temp = new ArrayList<>(List.of(strings));

          String[] tempString = strings;
          if (strings.length ==5) {
            String[] status = {record.getStatus().toString()};

            tempString = Stream.concat(Arrays.stream(strings), Arrays.stream(status))
                .toArray(
                    s -> (String[]) Array.newInstance(status.getClass().getComponentType(), s));
          }

          if (tempString[0].equals(String.valueOf(record.getId()))) {
            System.out.println("newValue = " + newValue);
            tempString[k] = newValue;
//            temp.set(k, newValue);
//            temp.add(record.getStatus().toString());
          }
          System.out.println("temp.toString() = " + temp.toString());
//          writer.writeNext(temp.toArray(temp.toArray(new String[0])));
          writer.writeNext(tempString);
        }
      }
    }
    reader.close();
    writer.flush();
    writer.close();
  }

//    CSVReader reader = new CSVReader(new FileReader(filename),",");
//    List<String[]> csvBody = reader.readAll();
//    csvBody.get(1)[2]=record.toString();
//    reader.close();
//
//    CSVWriter writer = new CSVWriter(new FileWriter(filename),",",' ');
//    writer.writeAll(csvBody);
//    writer.flush();
//    writer.close();

  public <T> List <T> readAllRecords(String filename, TypeOfFile fileType){
    List<T> recordList;
    if(fileType.equals(TypeOfFile.USERS)) recordList = (List<T>) Parser.parseCSVforUser(filename);
    else recordList = (List<T>) Parser.parseCSVforTasks(filename);
    return recordList;
  }
}
