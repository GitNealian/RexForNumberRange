package cn.nealian.RexForNumRange.generator;

import javax.script.ScriptException;

public interface IRexBuilder {
    RexBuilder number(int number);

    RexBuilder rangeFull(int decimalPlaces);

    RexBuilder rangeToCeiling(int start, int decimalPlaces) throws ScriptException;

    RexBuilder range(int min, int max) throws ScriptException;

    RexBuilder or();

    RexBuilder dot();

    @Override
    String toString();
}
