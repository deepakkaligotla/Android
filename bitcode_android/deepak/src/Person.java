public class Person {
    private String name;
    private int age;
    private String gender;
    private int height;
    private int aadhaar;

    public Person() {
        this.name = "Deepak";
        this.age = 32;
        this.gender = "Male";
        this.height = 5;
        this.aadhaar = 1234;
    }

    public Person(String name, int age, String gender, int height, int aadhaar) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.aadhaar = aadhaar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(int aadhaar) {
        this.aadhaar = aadhaar;
    }

    public void display() {
        System.out.println("Person{" +
                "name=" + name +
                ", age=" + age +
                ", gender=" + gender +
                ", height=" + height +
                ", aadhaar=" + aadhaar +
                '}');
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", aadhaar=" + aadhaar +
                '}';
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
}
