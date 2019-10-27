package cn.nealian.RexForNumRange.generator;

import javax.script.ScriptException;
import java.util.Collections;

public class RexBuilder implements IRexBuilder {
    private StringBuilder sb;
    private IntegerRangeRexGenerator engine;

    public RexBuilder(IntegerRangeRexGenerator engine) {
        this.sb = new StringBuilder();
        this.engine = engine;
    }

    @Override
    public IRexBuilder createNew() {
        this.sb = new StringBuilder();
        return this;
    }

    @Override
    public IRexBuilder number(long number) {
        sb.append(number);
        return this;
    }

    @Override
    public IRexBuilder rangeFull(int decimalPlaces) {
        sb.append(String.format("[0-9]{%d}", decimalPlaces));
        return this;
    }

    @Override
    public IRexBuilder rangeToCeiling(long start, int decimalPlaces) throws ScriptException {
        return range(start, Long.parseLong(String.join("", Collections.nCopies(decimalPlaces, "9"))));
    }

    @Override
    public IRexBuilder range(long min, long max) throws ScriptException {
        sb.append(engine.generate(min, max));
        return this;
    }

    @Override
    public IRexBuilder or() {
        sb.append("|");
        return this;
    }

    @Override
    public IRexBuilder dot() {
        sb.append("\\.(?!$)");
        return this;
    }

    @Override
    public String build() {
        return sb.toString();
    }
}
