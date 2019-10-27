package cn.nealian.RexForNumRange.generator;

import cn.nealian.RexForNumRange.model.NumberRange;
import org.junit.Before;
import org.junit.Test;

import javax.script.ScriptException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FloatRangeRexGeneratorSpec {
    private FloatRangeRexGenerator engine;

    @Before
//    public void setUp() {
//        engine = new FloatRangeRexGenerator(new FakeRexBuilder());
//    }

    @Test
    public void testGenerateResult() {
        try {
            NumberRange numberRange = new NumberRange(134.33, 467.321, 2);
            assertThat(engine.generate(numberRange), equalTo(numberRange.toString()));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
