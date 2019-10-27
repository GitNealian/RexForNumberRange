package cn.nealian.RexForNumRange.utils;

import org.apache.commons.lang3.StringUtils;

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

    public static long getIntegerPart(double number) {
        return (int) round(number, 0);
    }

    public static long getDecimalPart(double number, int places, boolean pad) {
        String decimal = String.valueOf(number).split("\\.")[1];
        decimal = decimal.substring(0, Math.min(places, decimal.length()));
        if (pad) {
            decimal = StringUtils.rightPad(decimal, places, '0');
        }
        return Long.parseLong(decimal);
    }
}
