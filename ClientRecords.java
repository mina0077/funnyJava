import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class ClientRecords {

    public static ArrayList<String> nameList = new ArrayList<String>();

    public static ArrayList<Double> whightList = new ArrayList<Double>();

    public static ArrayList<Double> heightList = new ArrayList<Double>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter File Path : ");
        String filePath;
        filePath = scanner.nextLine();
        scanner.close();
        getFilesLineElements(filePath);
        System.out.println("     name  " + "          weghit   " + "         height");
        System.out.println("  ==========  " + "       ===========   " + "      =========");
        showData();
        showStatistcs();
    }
    public static String getFilesLineElements(String filePath) {
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String tempLine;

                while ((tempLine = bufferedReader.readLine()) != null) {
                    String name = "", weight = "", height = "";
                    int dots = 0;
                    for (int i = 0; i < tempLine.length(); i++) {
                        if (tempLine.charAt(i) != '.' && !Character.isDigit(tempLine.charAt(i))) {
                            name += tempLine.charAt(i);
                        } else if (tempLine.charAt(i) == '.' || tempLine.charAt(i) == ' ') {
                            dots++;
                            i++;
                        } else if (Character.isDigit(tempLine.charAt(i)) && dots < 2) {
                            weight += tempLine.charAt(i);
                        } else if (Character.isDigit(tempLine.charAt(i)) && dots >= 2) {
                            height += tempLine.charAt(i);
                        } else {
                            break;
                        }
                    }
                    nameList.add(name);
                    whightList.add(Double.parseDouble(weight));
                    heightList.add(Double.parseDouble(height));
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return null;
    }

    public static void showData() {
        for (int i = 0; i < nameList.size(); i++) {
            System.out.println(nameList.get(i) + "            " + whightList.get(i) + "            " + heightList.get(i));
        }
    }

    public static void showStatistcs() {
        try {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.print("Statistics:  ");
        System.out.println("Largest Weight: " + Collections.max(whightList));
        System.out.println("Largest Height: " + Collections.max(heightList));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
