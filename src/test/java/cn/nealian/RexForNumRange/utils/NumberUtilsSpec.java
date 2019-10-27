package cn.nealian.RexForNumRange.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NumberUtilsSpec {
    @Test(expected = IllegalArgumentException.class)
    public void whenPlaceBelowZeroThenThrowIllegalArgumentException() {
        NumberUtils.round(10.999, -1);
    }

    @Test
    public void testRoundResult() {
        assertThat(NumberUtils.round(10.999, 2), equalTo(10.99));
    }

    @Test
    public void testGetIntegerPartResult() {
        assertThat(NumberUtils.getIntegerPart(10.99), equalTo(10L));
    }

    @Test
    public void testGetDecimalPartResultWhenPlacesBigger() {
        assertThat(NumberUtils.getDecimalPart(10.9, 2, true), equalTo(90L));
    }

    @Test
    public void testGetDecimalPartResultWhenPlacesSmaller() {
        assertThat(NumberUtils.getDecimalPart(10.999, 2, true), equalTo(99L));
    }
}
