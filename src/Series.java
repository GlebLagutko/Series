import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Series {
    protected double first;
    protected double step;
    protected int n;

    public Series() {
        this.first = 0;
        this.step = 0;
        this.n = 0;
    }

    public Series(double first, double step, int n) {
        this.first = first;
        this.step = step;
        this.n = n;
    }

    public double getFirst() {
        return first;
    }

    public double getStep() {
        return step;
    }

    public int getN() {
        return n;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public void setN(int n) {
        this.n = n;
    }

    abstract double getElement(int j);

    double sum() {
        double sum = 0;
        for (int i = 0; i < n; i++)
            sum += getElement(i);
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++)
            s.append(getElement(i) + " ");
        return s.toString();
    }

    void save(String filename)  {
        try (PrintWriter wr = new PrintWriter(new File(filename))) {
            wr.println(this.toString());
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
