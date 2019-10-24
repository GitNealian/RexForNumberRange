package cn.nealian.RexForNumRange.generator;

import cn.nealian.RexForNumRange.model.NumberRange;

import javax.script.ScriptException;

import static cn.nealian.RexForNumRange.utils.NumberUtils.getDecimalPart;
import static cn.nealian.RexForNumRange.utils.NumberUtils.getIntegerPart;

public class FloatRangeRexGenerator {
    private IntegerRangeRexGenerator generator;

    public FloatRangeRexGenerator(IntegerRangeRexGenerator generator) {
        this.generator = generator;
    }

    public String generate(NumberRange range) throws ScriptException {
        RexBuilder rb = new RexBuilder(generator);
        if (range.getDecimalPlaces() == 0) {
            rb.range(getIntegerPart(range.getStart()), getIntegerPart(range.getEnd()));
        } else {
            StringBuilder sb = new StringBuilder();
            if (range.integerLength() > 1) {
                rb.number(getIntegerPart(range.getStart()))
                        .dot()
                        .rangeToCeiling(getDecimalPart(range.getStart(), range.getDecimalPlaces()), range.getDecimalPlaces())
                        .or()
                        .range(getIntegerPart(range.getStart()) + 1, getIntegerPart(range.getEnd()) - 1)
                        .dot()
                        .rangeFull(range.getDecimalPlaces())
                        .or()
                        .number(getIntegerPart(range.getEnd()))
                        .dot()
                        .rangeToCeiling(getDecimalPart(range.getEnd(), range.getDecimalPlaces()), range.getDecimalPlaces());
            } else if (range.isDecimalPartChangeOnly()) {
                rb.number(getIntegerPart(range.getStart()))
                        .dot()
                        .range(getDecimalPart(range.getStart(), range.getDecimalPlaces()),
                                getDecimalPart(range.getEnd(), range.getDecimalPlaces()));
            } else {
                rb.number(getIntegerPart(range.getStart()))
                        .dot()
                        .rangeToCeiling(getDecimalPart(range.getStart(), range.getDecimalPlaces()), range.getDecimalPlaces())
                        .or()
                        .number(getIntegerPart(range.getEnd()))
                        .dot()
                        .rangeToCeiling(getDecimalPart(range.getEnd(), range.getDecimalPlaces()), range.getDecimalPlaces());
            }
        }
        return rb.toString();
    }
}
