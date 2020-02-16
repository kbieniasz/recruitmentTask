import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumCalculator {
    public Map<String, BigDecimal> calculateSum(List<Employee> employees)
    {
        Map<String, BigDecimal> stats = new HashMap<>();
        for(Employee employee : employees)
        {
            if(!stats.containsKey(employee.getJob()))
            {
                stats.put(employee.getJob(), employee.getSalary());
            }
            else
            {
                BigDecimal previous = stats.get(employee.getJob());
                stats.put(employee.getJob(), previous.add(employee.getSalary()));

            }
        }
        return stats;
    }
}
