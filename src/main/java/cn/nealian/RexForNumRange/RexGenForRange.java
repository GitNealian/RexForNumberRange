package cn.nealian.RexForNumRange;

/**
 * @author zhukejia
 */
public class RexGenForRange {

    private double start;
    private double end;
    private boolean decimal;


    public RexGenForRange(double start, double end, boolean decimal) {
        this.start = start;
        this.end = end;
        this.decimal = decimal;
    }

    public String getRexString() {
        return "";
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public boolean isDecimal() {
        return decimal;
    }

    public void setDecimal(boolean decimal) {
        this.decimal = decimal;
    }
}
