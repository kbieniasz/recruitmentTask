import java.math.BigDecimal;

public class Employee {
    private String surname;
    private String name;
    private int id;
    private String job;
    private BigDecimal salary;

    public Employee(String surname, String name, int id, String job, BigDecimal salary) {
        this.surname = surname;
        this.name = name;
        this.id = id;
        this.job = job;
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public BigDecimal getSalary() {
        return salary;
    }

}
