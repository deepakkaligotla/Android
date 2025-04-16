public class Employee extends Person{
    private int employeeId;
    private float employeeSalary;

    public Employee() {
        this.employeeId = 101;
        this.employeeSalary = 42324.456f;
    }

    public Employee(int employeeId, float employeeSalary) {
        this.employeeId = employeeId;
        this.employeeSalary = employeeSalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public float getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(float employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public void displayEmployee() {
        System.out.println("Employee{" +
                "employeeId=" + employeeId +
                ", employeeSalary=" + employeeSalary +
                '}');
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeSalary=" + employeeSalary +
                '}';
    }
}
