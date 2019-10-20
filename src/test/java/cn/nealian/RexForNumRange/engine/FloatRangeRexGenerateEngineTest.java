package cn.nealian.RexForNumRange.engine;

import cn.nealian.RexForNumRange.model.NumberRange;
import org.junit.BeforeClass;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStreamReader;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author zhukejia
 */
@RunWith(Theories.class)
public class FloatRangeRexGenerateEngineTest {
    private static FloatRangeRexGenerateEngine engine;
    private static IntegerRangeRexGenerateEngine integerEngine;

    @BeforeClass
    public static void setUp() {
        ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            nashorn.eval(new InputStreamReader(Objects.requireNonNull(FloatRangeRexGenerateEngineTest.class.getClassLoader()
                    .getResourceAsStream("RegNumericRange.js"))));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        integerEngine = new IntegerRangeRexGenerateEngine(nashorn);
        engine = new FloatRangeRexGenerateEngine(integerEngine);
    }


    @DataPoints
    public static NumberRange[] ranges =
            {
                    new NumberRange(123.3, 145.6, 0),
                    new NumberRange(124.67, 167.789, 3),
                    new NumberRange(123.45, 123.789, 3),
                    new NumberRange(123.56, 124.77, 1)
            };

    @DataPoints
    public static String[] numbers = {"99", "123.45", "125.67", "123.55", "124.56", "155.45", "188.56"};

    @Theory
    public void testGenerateResult(NumberRange range, String number) {
        try {
            boolean testResult = number.matches(engine.generate(range));
            assertThat(testResult,
                    equalTo(isNumberInRange(number, range)));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    private boolean isNumberInRange(String number, NumberRange range) {
        double numberValue = new Double(number);
        return numberValue >= range.getStart() && numberValue <= range.getEnd() &&
                range.getDecimalPlaces() == calDecimalPlacesByPartsSplitByPoint(number);
    }

    private int calDecimalPlacesByPartsSplitByPoint(String number) {
        String[] parts = number.split("\\.");
        if (parts.length == 1) {
            return 0;
        }
        return parts[1].length();
    }

}
