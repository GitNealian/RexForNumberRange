package cn.nealian.RexForNumRange.controller;

import cn.nealian.RexForNumRange.generator.FloatRangeRexGenerator;
import cn.nealian.RexForNumRange.model.NumberRange;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author zhukejia
 */
public class Controller {
    private FloatRangeRexGenerator generator = FloatRangeRexGenerator.getFloatRangeRexGenerator();

    @FXML
    private TextField tfMin;
    @FXML
    private TextField tfMax;
    @FXML
    private Spinner<Integer> spPlaces;
    @FXML
    private TextArea taResult;

    @FXML
    public void initialize() {
        spPlaces.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
    }


    public void generate() {
        try {
            taResult.setText(generator.generate(new NumberRange(Double.parseDouble(tfMin.getText()),
                    Double.parseDouble(tfMax.getText()),
                    spPlaces.getValue())));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            taResult.setText("不合法的输入！");
        } catch (Exception e) {
            e.printStackTrace();
            taResult.setText("生成正则表达式过程中出错：\n"
                    + e.getClass().getName()
                    + ": "
                    + e.getMessage()
                    + "\n"
                    + Arrays.stream(e.getStackTrace())
                    .map(StackTraceElement::toString).collect(Collectors.joining("\n")));
        }
    }
}

