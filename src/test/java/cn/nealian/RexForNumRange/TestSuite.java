package cn.nealian.RexForNumRange;

import cn.nealian.RexForNumRange.generator.FloatRangeRexGeneratorTest;
import cn.nealian.RexForNumRange.model.NumberRangeSpec;
import cn.nealian.RexForNumRange.utils.NumberUtilsSpec;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({NumberRangeSpec.class, NumberUtilsSpec.class, FloatRangeRexGeneratorTest.class})
public class TestSuite {
}
