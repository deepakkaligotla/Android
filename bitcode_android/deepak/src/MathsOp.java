public class MathsOp implements AddOp, SubOp {
    private int n1;
    private int n2;

    public MathsOp() {
        this.n1 = 10;
        this.n2 = 12;
    }

    public MathsOp(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public int add() {
      return this.n1 + this.n2;
    }

    @Override
    public int add(int num1, int num2) {
        return num1+num2;
    }

    @Override
    public int sub(int num1, int num2) {
        return num1-num2;
    }

    @Override
    public String toString() {
        return "MathsOp{" +
                "n1=" + n1 +
                ", n2=" + n2 +
                '}';
    }
}
