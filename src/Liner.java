public class Liner extends Series {
    public Liner(double first, double step, int n) {
        super(first, step, n);
    }

    @Override
    double getElement(int j) {
        return first + j * step;
    }

}
