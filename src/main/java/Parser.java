import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Parser {

    public List<Employee> parseJSONFile(String path) {
        List<Employee> employees = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray employeeList = (JSONArray) jsonObject.get("employees");

            Iterator<JSONObject> iterator = employeeList.iterator();
            while (iterator.hasNext()) {
                employees.add(parseSingleJSONObject(iterator.next()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return employees;
    }


    public List<Employee> parseCSVFile(String path) {
        List<Employee> employees = new ArrayList<>();

        Path directPath = Paths.get(path);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (BufferedReader br = Files.newBufferedReader(directPath,
                StandardCharsets.ISO_8859_1);
             CSVReader reader = new CSVReaderBuilder(br).withCSVParser(parser).withSkipLines(1)
                     .build()) {

            String[] line;
            while ((line = reader.readNext()) != null) {
                employees.add(parseSingleCSVLine(line));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return employees;
    }

    private Employee parseSingleCSVLine(String[] row) throws java.text.ParseException {
        BigDecimal salary = parseStringToDecimal(row[4]);
        return new Employee(
                row[1],
                row[2],
                Integer.parseInt(row[0].replaceAll("\\s+", "")),
                row[3],
                salary);
    }


    private Employee parseSingleJSONObject(JSONObject jsonObject) throws java.text.ParseException {
        BigDecimal salary = parseStringToDecimal((String) jsonObject.get("salary"));
        return new Employee(
                (String) jsonObject.get("surname"),
                (String) jsonObject.get("name"),
                (int) (long) jsonObject.get("id"),
                (String) jsonObject.get("job"),
                salary);
    }

    private BigDecimal parseStringToDecimal(String string) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(string.replace('.', ','));
        return new BigDecimal(number.toString());
    }
}
