import java.util.Objects;

class Student extends Person{
    private int rollNo;
    private int classNo;
    private int score;

    public Student() {
        this.rollNo = 0;
        this.classNo = 0;
        this.score = 0;
    }

    public Student(int rollNo, int classNo, int score) {
        this.rollNo = rollNo;
        this.classNo = classNo;
        this.score = score;
    }

    public int getRollNo() {
        return rollNo;
    }

    public int getClassNo() {
        return classNo;
    }

    public int getScore() {
        return score;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Student{" +
                "rollNo=" + rollNo +
                ", classNo=" + classNo +
                ", score=" + score +
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
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", classNo=" + classNo +
                ", score=" + score +
                '}';
    }
}