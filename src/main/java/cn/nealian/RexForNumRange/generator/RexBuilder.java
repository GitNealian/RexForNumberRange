package cn.nealian.RexForNumRange.generator;

import javax.script.ScriptException;
import java.util.Collections;

public class RexBuilder {
    private StringBuilder sb;
    private IntegerRangeRexGenerator engine;

    public RexBuilder(IntegerRangeRexGenerator engine) {
        this.sb = new StringBuilder();
        this.engine = engine;
    }

    public RexBuilder number(int number) {
        sb.append(number);
        return this;
    }

    public RexBuilder rangeFull(int decimalPlaces) {
        sb.append(String.format("[0-9]{%d}", decimalPlaces));
        return this;
    }

    public RexBuilder rangeToCeiling(int start, int decimalPlaces) throws ScriptException {
        return range(start, Integer.parseInt(String.join("", Collections.nCopies(decimalPlaces, "9"))));
    }

    public RexBuilder range(int min, int max) throws ScriptException {
        sb.append(engine.generate(min, max));
        return this;
    }

    public RexBuilder or() {
        sb.append("|");
        return this;
    }

    public RexBuilder dot() {
        sb.append("\\.");
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
