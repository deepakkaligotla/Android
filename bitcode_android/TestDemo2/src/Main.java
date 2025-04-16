public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.display();

        Person p1 = new Person("Name 1", "City 1", "State 1");
        p1.display();
        p1.state = "Karnataka";
        p1.display();

        Person p2 = new Person();
        p2.display();
    }
}