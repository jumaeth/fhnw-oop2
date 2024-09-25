package lambda;

public class LogEntry {
    private final Employee employee;
    private final String file;

    public LogEntry(Employee employee, String file) {
        this.employee = employee;
        this.file = file;
    }

    public Employee employee() {
        return employee;
    }

    public String file() {
        return file;
    }
}
