package cn.nealian.RexForNumRange.generator;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class IntegerRangeRexGenerator {
    private ScriptEngine engine;

    public IntegerRangeRexGenerator(ScriptEngine engine) {
        this.engine = engine;
    }

    public String generate(int min, int max) throws ScriptException {
        if (min < 0 || max < min) {
            throw new RuntimeException("[min] should be bigger than zero and smaller than [max]");
        }
        StringBuilder scriptBuilder = new StringBuilder();
        scriptBuilder.append("new RegNumericRange(")
                .append(min)
                .append(",")
                .append(max)
                .append(",{}).generate().pattern");
        return (String) engine.eval(scriptBuilder.toString());
    }
}
