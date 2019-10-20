package cn.nealian.RexForNumRange;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * @author zhukejia
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(
                getClass().getClassLoader().getResource("sample.fxml")));
        primaryStage.setTitle("数字范围正则表达式生成工具");
        primaryStage.setScene(new Scene(root, 450, 250));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initEngine(String jsPath){

    }

    public static void main(String[] args) {
        launch(args);
    }
}
