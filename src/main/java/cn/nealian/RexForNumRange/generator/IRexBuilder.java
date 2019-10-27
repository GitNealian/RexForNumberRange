package cn.nealian.RexForNumRange.generator;

import javax.script.ScriptException;

public interface IRexBuilder {
    IRexBuilder createNew();

    IRexBuilder number(long number);

    IRexBuilder rangeFull(int decimalPlaces);

    IRexBuilder rangeToCeiling(long start, int decimalPlaces) throws ScriptException;

    IRexBuilder range(long min, long max) throws ScriptException;

    IRexBuilder or();

    IRexBuilder dot();

    String build();
}
