public class MyTest {
    public static final String APP_NAME = "Demo";
    volatile int counter;
    private transient String password;
    private final int max = 100;

    public synchronized void increment() {
        counter++;
    }
}
