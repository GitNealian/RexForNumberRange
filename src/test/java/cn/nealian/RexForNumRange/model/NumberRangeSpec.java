package cn.nealian.RexForNumRange.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NumberRangeSpec {
    @Test(expected = IllegalArgumentException.class)
    public void whenStartBelowZeroThenThrowIllegalArgumentException() {
        new NumberRange(-1, 9, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEndBelowZeroThenThrowIllegalArgumentException() {
        new NumberRange(1, -9, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenDecimalPlacesBelowZeroThenThrowIllegalArgumentException() {
        new NumberRange(1, 9, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEndSmallerThanStartThenThrowIllegalArgumentException() {
        new NumberRange(10, 9, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenRoundStartEqualToRoundEndThenThrowIllegalArgumentException() {
        new NumberRange(10.99, 10.999, 2);
    }

    @Test
    public void testGetStartResult() {
        assertThat(new NumberRange(99.123, 100, 2).getStart(), equalTo(99.12));
    }

    @Test
    public void testGetEndResult() {
        assertThat(new NumberRange(99, 100.123, 2).getEnd(), equalTo(100.12));
    }

    @Test
    public void whenDecimalPartChangeOnlyThenReturnTrue() {
        assertThat(new NumberRange(99.12, 99.13, 2).isDecimalPartChangeOnly(),
                is(true));
    }

    @Test
    public void whenIntegerPartChangeThenReturnFalse() {
        assertThat(new NumberRange(99, 100, 0).isDecimalPartChangeOnly(),
                is(false));
    }

    @Test
    public void testToStringResult() {
        NumberRange nr = new NumberRange(60.1, 89.3, 5);
        assertThat(nr.toString(), equalTo("[60.10000, 89.30000]"));
    }
}
