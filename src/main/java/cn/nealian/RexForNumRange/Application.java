package cn.nealian.RexForNumRange;

import cn.nealian.RexForNumRange.generator.FloatRangeRexGenerator;
import cn.nealian.RexForNumRange.generator.IntegerRangeRexGenerator;
import cn.nealian.RexForNumRange.generator.RexBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author zhukejia
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        initEngine("RegNumericRange.js");

        Parent root = FXMLLoader.load(Objects.requireNonNull(
                getClass().getClassLoader().getResource("sample.fxml")));
        primaryStage.setTitle("数字范围正则表达式生成工具");
        primaryStage.setScene(new Scene(root, 450, 280));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initEngine(String jsName) {
        ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            nashorn.eval(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader()
                    .getResourceAsStream(jsName))));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        IntegerRangeRexGenerator integerGenerator = new IntegerRangeRexGenerator(nashorn);

        FloatRangeRexGenerator.getFloatRangeRexGenerator().setRexBuilder(new RexBuilder(integerGenerator));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
