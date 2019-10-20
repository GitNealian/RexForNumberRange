package cn.nealian.RexForNumRange.engine;

import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class IntegerRangeRexGenerateEngineSpec {
    private IntegerRangeRexGenerateEngine rexEngine;
    private ScriptEngine nashorn;

    @Before
    public void init() throws ScriptException {
//        nashorn = new ScriptEngineManager().getEngineByName("nashorn");
//        nashorn.eval(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("RegNumericRange.js")));
        nashorn = mock(ScriptEngine.class);
        doReturn("").when(nashorn).eval(any(String.class));
        rexEngine = new IntegerRangeRexGenerateEngine(nashorn);
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
