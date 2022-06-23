public class Validation {
  public static int checkInput(String input) {
    int option;
    try {
      option = Integer.parseInt(input);
    } catch (NumberFormatException e) {
      System.out.println("Неверный формат ввода");
      return -1;
    }
    return option;
  }
}
