package cn.nealian.RexForNumRange.generator;

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
public class FloatRangeRexGeneratorTest {
    private static FloatRangeRexGenerator generator;
    private static FloatRangeRexGenerator fakeGenerator;

    @BeforeClass
    public static void setUp() {
        FloatRangeRexGenerator.getFloatRangeRexGenerator().setRexBuilder(new FakeRexBuilder());
        fakeGenerator = FloatRangeRexGenerator.getFloatRangeRexGenerator();
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
    public void testGenerateResultWithFakeBuilder(NumberRange range) {
        try {
            assertThat(fakeGenerator.generate(range),
                    equalTo(range.toString()));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
