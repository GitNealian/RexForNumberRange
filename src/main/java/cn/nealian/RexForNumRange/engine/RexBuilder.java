package cn.nealian.RexForNumRange.engine;

import javax.script.ScriptException;

public class RexBuilder {
    private StringBuilder sb;
    private IntegerRangeRexGenerateEngine engine;

    public RexBuilder(IntegerRangeRexGenerateEngine engine) {
        this.sb = new StringBuilder();
        this.engine = engine;
    }

    public RexBuilder number(int number) {
        sb.append(number);
        return this;
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
