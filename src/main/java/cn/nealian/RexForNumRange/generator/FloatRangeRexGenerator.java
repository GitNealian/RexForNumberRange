package cn.nealian.RexForNumRange.generator;

import cn.nealian.RexForNumRange.model.NumberRange;

import javax.script.ScriptException;

import static cn.nealian.RexForNumRange.utils.NumberUtils.getDecimalPart;
import static cn.nealian.RexForNumRange.utils.NumberUtils.getIntegerPart;

public class FloatRangeRexGenerator {
    private IRexBuilder rb;
    private static FloatRangeRexGenerator generator;

    private FloatRangeRexGenerator() {
    }

    public void setRexBuilder(IRexBuilder builder) {
        this.rb = builder;
    }

    public static FloatRangeRexGenerator getFloatRangeRexGenerator() {
        if (generator == null) {
            generator = new FloatRangeRexGenerator();
        }
        return generator;
    }

    public String generate(NumberRange range) throws ScriptException {
        if (rb == null) {
            throw new RuntimeException("No RexBuilder set!");
        }
        rb.createNew();
        if (range.getDecimalPlaces() == 0) {
            rb.range(getIntegerPart(range.getStart()), getIntegerPart(range.getEnd()));
        } else {
            if (range.integerLength() > 1) {
                rb.number(getIntegerPart(range.getStart()))
                        .dot()
                        .rangeToCeiling(getDecimalPart(range.getStart(), range.getDecimalPlaces(), true), range.getDecimalPlaces())
                        .or()
                        .range(getIntegerPart(range.getStart()) + 1, getIntegerPart(range.getEnd()) - 1)
                        .dot()
                        .rangeFull(range.getDecimalPlaces())
                        .or()
                        .number(getIntegerPart(range.getEnd()))
                        .dot()
                        .range(0, getDecimalPart(range.getEnd(), range.getDecimalPlaces(), true));
            } else if (range.isDecimalPartChangeOnly()) {
                rb.number(getIntegerPart(range.getStart()))
                        .dot()
                        .range(getDecimalPart(range.getStart(), range.getDecimalPlaces(), true),
                                getDecimalPart(range.getEnd(), range.getDecimalPlaces(), true));
            } else {
                rb.number(getIntegerPart(range.getStart()))
                        .dot()
                        .rangeToCeiling(getDecimalPart(range.getStart(), range.getDecimalPlaces(), true), range.getDecimalPlaces())
                        .or()
                        .number(getIntegerPart(range.getEnd()))
                        .dot()
                        .range(0, getDecimalPart(range.getEnd(), range.getDecimalPlaces(), true));
            }
        }
        return rb.build();
    }
}
