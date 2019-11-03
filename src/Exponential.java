public class Exponential extends Series {
    public Exponential(double first, double step, int n) {
        super(first, step, n);
    }

    @Override
    double solve(int j) {
        return first * Math.pow(step, j);
    }
}
