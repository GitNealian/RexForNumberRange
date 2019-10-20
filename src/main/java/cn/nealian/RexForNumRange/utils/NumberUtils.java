package cn.nealian.RexForNumRange.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.pow;

public class NumberUtils {

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        return new BigDecimal(Double.toString(value)).setScale(places, RoundingMode.FLOOR).doubleValue();
    }

    public static int getIntegerPart(double number) {
        return (int) round(number, 0);
    }

    public static int getDecimalPart(double number, int places) {
        return getIntegerPart((number - getIntegerPart(number)) * pow(10, places));
    }
}
