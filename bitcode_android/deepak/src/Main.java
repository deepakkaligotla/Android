//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.display();

        MathsOp m1 = new MathsOp();
        System.out.println(m1.add(24,36));

        MathsOp m2 = new MathsOp(2,3);
        System.out.println(m2.add());
    }
}