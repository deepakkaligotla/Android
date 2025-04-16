public class Employee extends Person {
    private int empId;
    private double salary;

    public Employee() {
        this.empId = 1001;
        this.salary = 45000;
    }

    public Employee(int empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    @Override
    public final void calculateSalary() {

    }

    void display() {
        System.out.println(
                "emp Id=" + empId +
                ", salary=" + salary);
    }
}
