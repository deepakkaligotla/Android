package in.kaligotla.lifecyclejava;

public class Test {
    public static final String APP_NAME = "Demo";  // static constant
    private volatile int counter;                 // concurrency-safe variable
    private transient String password;            // will not be serialized
    private final int max = 100;                  // cannot be changed after init

    public synchronized void increment() {
        counter++;
    }

    public void demoVar() {
        var localMessage = "Hello";
        var localNumber = 123;
        var localPrice = 2245.00;
        var localStatus = true;
    }
}