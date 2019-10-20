package cn.nealian.RexForNumRange.engine;

import cn.nealian.RexForNumRange.model.NumberRange;

import javax.script.ScriptException;

import java.util.Collections;

import static cn.nealian.RexForNumRange.utils.NumberUtils.getDecimalPart;
import static cn.nealian.RexForNumRange.utils.NumberUtils.getIntegerPart;

public class FloatRangeRexGenerateEngine {
    private IntegerRangeRexGenerateEngine engine;

    public FloatRangeRexGenerateEngine(IntegerRangeRexGenerateEngine engine) {
        this.engine = engine;
    }

    public String generate(NumberRange range) throws ScriptException {
        RexBuilder rb = new RexBuilder(engine);
        if (range.getDecimalPlaces() == 0) {
            return engine.generate(getIntegerPart(range.getStart()), getIntegerPart(range.getEnd()));
        } else {
            StringBuilder sb = new StringBuilder();
            if (range.integerLength() > 1) {
                sb.append(getIntegerPart(range.getStart()))
                        .append("\\.")
                        .append(engine.generate(getDecimalPart(range.getStart(), range.getDecimalPlaces()),
                                Integer.parseInt(String.join("", Collections.nCopies(range.getDecimalPlaces(), "9")))));
                sb.append("|");

                sb.append(engine.generate(getIntegerPart(range.getStart()) + 1, getIntegerPart(range.getEnd()) - 1))
                        .append("\\.")
                        .append(String.format("[0-9]{%d}", range.getDecimalPlaces()));

                sb.append("|");
                sb.append(getIntegerPart(range.getEnd()))
                        .append("\\.")
                        .append(engine.generate(getDecimalPart(range.getEnd(), range.getDecimalPlaces()),
                                Integer.parseInt(String.join("", Collections.nCopies(range.getDecimalPlaces(), "9")))));
            } else if (range.isDecimalPartChangeOnly()) {
                sb.append(getIntegerPart(range.getStart()))
                        .append("\\.")
                        .append(engine.generate(getDecimalPart(range.getStart(), range.getDecimalPlaces()),
                                getDecimalPart(range.getEnd(), range.getDecimalPlaces())));
            } else {
                sb.append(getIntegerPart(range.getStart()))
                        .append("\\.")
                        .append(engine.generate(getDecimalPart(range.getStart(), range.getDecimalPlaces()),
                                Integer.parseInt(String.join("", Collections.nCopies(range.getDecimalPlaces(), "9")))));
                sb.append("|");
                sb.append(getIntegerPart(range.getEnd()))
                        .append("\\.")
                        .append(engine.generate(getDecimalPart(range.getEnd(), range.getDecimalPlaces()),
                                Integer.parseInt(String.join("", Collections.nCopies(range.getDecimalPlaces(), "9")))));
            }
            return sb.toString();
        }
    }
}
