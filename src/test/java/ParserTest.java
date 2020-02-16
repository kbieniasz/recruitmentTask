import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ParserTest {
    private Parser parser = new Parser();
    public static final String JSONPath = "\\src\\test\\resources\\employeesTest.json";
    public static final String CSVPath = "\\src\\test\\resources\\employeesTest.csv";
    private Employee employeeMock;
    private List<Employee> employeesJSON;
    private List<Employee> employeesCSV;

    @Before
    public void setUp() throws IOException {
        employeeMock = new Employee("Red", "Johann", 1, "Teacher", new BigDecimal("3543333.21"));
        employeesJSON = parser.parseJSONFile(new java.io.File(".").getCanonicalPath()+JSONPath);
        employeesCSV = parser.parseCSVFile(new java.io.File(".").getCanonicalPath()+CSVPath);

    }

    @Test
    public void JSONtest1()
    {
        assertEquals("Teacher", employeesJSON.get(0).getJob());
    }

    @Test
    public void JSONtest2()
    {
        assertEquals(employeeMock.getSalary(), employeesJSON.get(0).getSalary());
    }

    @Test
    public void CSVtest1()
    {
        assertEquals("Teacher", employeesJSON.get(0).getJob());
    }

    @Test
    public void CSVtest2()
    {
        assertEquals(employeeMock.getSalary(), employeesJSON.get(0).getSalary());
    }
}
