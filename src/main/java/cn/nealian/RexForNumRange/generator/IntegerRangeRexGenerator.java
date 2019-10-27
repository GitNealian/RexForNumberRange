package cn.nealian.RexForNumRange.generator;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class IntegerRangeRexGenerator {
    private ScriptEngine engine;

    public IntegerRangeRexGenerator(ScriptEngine engine) {
        this.engine = engine;
    }

    public String generate(long min, long max) throws ScriptException {
        if (min < 0 || max < min) {
            throw new RuntimeException("[min] should be bigger than zero and smaller than [max]");
        }
        if (min == max) {
            return "(" + min + ")";
        }
        StringBuilder scriptBuilder = new StringBuilder();
        scriptBuilder.append("new RegNumericRange(")
                .append(min)
                .append(",")
                .append(max)
                .append(",{}).generate().pattern");
        System.out.println(scriptBuilder.toString());
        return (String) engine.eval(scriptBuilder.toString());
    }
}
