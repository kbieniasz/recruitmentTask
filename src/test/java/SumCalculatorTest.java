import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

public class SumCalculatorTest {
    private SumCalculator sumCalculator = new SumCalculator();
    private ArrayList<Employee> employees = new ArrayList<>();
    private Map<String, BigDecimal> results;

    @Before
    public void setUp() {
        employees.add(new Employee("Mock","mock", 1, "Sailor", new BigDecimal("100.00")));
        employees.add(new Employee("Bock", "bock",2, "Tailor", new BigDecimal("100.01")));
        employees.add(new Employee("Teaching", "IT", 3, "Teacher", new BigDecimal("99.99")));
        employees.add(new Employee("Teaching", "IT", 4, "Teacher", new BigDecimal("99.99")));
        employees.add(new Employee("Teaching", "IT", 5, "Teacher", new BigDecimal("99.99")));
        employees.add(new Employee("Teaching", "IT", 6, "Teacher", new BigDecimal("99.99")));
        results = sumCalculator.calculateSum(employees);
    }

    @Test
    public void test1()
    {
        assertEquals(new BigDecimal("100.00"),results.get("Sailor"));
    }

    @Test
    public void test2()
    {
        assertEquals(new BigDecimal("399.96"),results.get("Teacher"));
    }

    @Test
    public void test3()
    {
        assertNotEquals(new BigDecimal("100.1"),results.get("Teacher"));
    }

    @Test
    public void test4()
    {
        assertNull(results.get("Priest"));
    }
}
