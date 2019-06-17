import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class zad4_1 extends Application {

    Button button;
    Text text;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("lab4");

        button = new Button();
        button.setText("click");

        text = new Text();
        text.setX(40);
        text.setY(20);
        text.setText("jdsjfsbjsfbjsfd");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        layout.getChildren().add(text);

        Scene scene = new Scene(layout, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
