package cn.nealian.RexForNumRange.generator;

import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class IntegerRangeRexGeneratorSpec {
    private IntegerRangeRexGenerator rexEngine;
    private ScriptEngine nashorn;

    @Before
    public void init() throws ScriptException {
        nashorn = mock(ScriptEngine.class);
        doReturn("").when(nashorn).eval(any(String.class));
        rexEngine = new IntegerRangeRexGenerator(nashorn);
    }

    @Test
    public void whenCallGenerateWithProperParametersThen() throws ScriptException {
        rexEngine.generate(98, 123);
        verify(nashorn, times(1))
                .eval("new RegNumericRange(98,123,{}).generate().pattern");
    }

    @Test(expected = RuntimeException.class)
    public void whenTheSmallerIsBelowZeroThenThrowRuntimeException() throws ScriptException {
        rexEngine.generate(-1, 0);
    }

    @Test(expected = RuntimeException.class)
    public void whenTheParameterOneBiggerThanParameterTwoThenThrowRuntimeException() throws ScriptException {
        rexEngine.generate(2, 1);
    }
}
