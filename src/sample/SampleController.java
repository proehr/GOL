package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SampleController implements Initializable {

    public TextField heightField;
    public TextField widthField;
    public AnchorPane rootAnchor;

    public void loadNewScene(ActionEvent actionEvent) throws Exception{
        int height = Integer.parseInt(heightField.getText());
        int width = Integer.parseInt(widthField.getText());
        GameData.getInstance().setData(height, width);
        Stage stage = Main.getStage();
        stage.setHeight(137 + GameData.getInstance().getRectangleSize()*height);
        stage.setWidth(GameData.getInstance().getRectangleSize()*width + 50);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("playfield.fxml"));
        rootAnchor.getChildren().setAll(pane);

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}

