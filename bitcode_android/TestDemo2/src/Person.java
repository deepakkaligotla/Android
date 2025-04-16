public class Person {
    private String empName;
    private String city;
    private double height;
    static String state;

    public Person() {
        this.empName = "Radha";
        this.city = "Nagpur";
        state = "Maharashtra";
    }

    public Person(String empName, String city, String stateName) {
        this.empName = empName;
        this.city = city;
        state = stateName;
    }

    public void calculateSalary() {

    }

    void display() {
        System.out.println("empName= " + empName +
                ", city= " + city +
                ", state= " + state);
    }
}
