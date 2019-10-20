package cn.nealian.RexForNumRange.model;

import static cn.nealian.RexForNumRange.utils.NumberUtils.round;

public class NumberRange {
    private double start;
    private double end;
    private int decimalPlaces;

    public NumberRange(double start, double end, int decimalPlaces) {
        if (start < 0 || decimalPlaces < 0) {
            throw new IllegalArgumentException();
        }
        setRange(round(start, decimalPlaces), round(end, decimalPlaces), decimalPlaces);
    }

    private void setRange(double start, double end, int decimalPlaces) {
        if (end <= start) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
        this.decimalPlaces = decimalPlaces;
    }

    public double length() {
        return end - start;
    }

    public double integerLength() {
        return round(end, 0) - round(start, 0);
    }

    public boolean isDecimalPartChangeOnly() {
        return integerLength() == 0;
    }


    public double getStart() {
        return start;
    }


    public double getEnd() {
        return end;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    @Override
    public String toString() {
        String format = "[%." + decimalPlaces + "f, %." + decimalPlaces + "f]";
        return String.format(format, start, end);
    }

}
