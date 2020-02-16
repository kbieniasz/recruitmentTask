import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Main {
    public static final String JSONPath = "\\src\\main\\resources\\employees.json";
    public static final String CSVPath = "\\src\\main\\resources\\employees.csv";


    public static void main(String[] args) throws IOException {

        Parser parser = new Parser();
        SumCalculator sumCalculator = new SumCalculator();

        List<Employee> employeeFromJSON = parser.parseJSONFile(new java.io.File(".").getCanonicalPath()+JSONPath);
        Map<String, BigDecimal> statsJSON = sumCalculator.calculateSum(employeeFromJSON);
        displayAnswer(statsJSON, "employees.json");

        List<Employee> employeesFromCSV = parser.parseCSVFile(new java.io.File(".").getCanonicalPath()+CSVPath);
        Map<String, BigDecimal> statsCSV = sumCalculator.calculateSum(employeesFromCSV);
        displayAnswer(statsCSV, "employees.csv");

    }

    public static void displayAnswer(Map<String, BigDecimal> stats, String fileName)
    {
        System.out.println("Calculated values for " + fileName + ":");
        for (String key : stats.keySet()) {
            System.out.println(key + " - " + (stats.get(key).setScale(2).toString()).replace('.', ','));
        }
    }
}
