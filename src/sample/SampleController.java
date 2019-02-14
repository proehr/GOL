package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SampleController implements Initializable {

    public TextField heightField;
    public TextField widthField;
    public AnchorPane rootAnchor;

    public void loadNewScene(ActionEvent actionEvent) throws Exception{
        int height = Integer.parseInt(heightField.getText());
        int width = Integer.parseInt(widthField.getText());
        GameData.getInstance().setHeight(height);
        GameData.getInstance().setWidth(width);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("playfield.fxml"));
        pane.setAccessibleText(height + " " + width);
        rootAnchor.getChildren().setAll(pane);
        /*BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        TextField ruleField = new TextField();
        Label ruleLabel = new Label("Rule:");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Dead",
                        "Alive",
                        "Go Around"
                );
        ComboBox borderBox = new ComboBox(options);
        Label borderLabel = new Label("Border:");
        gridPane.add(ruleLabel,0,0);
        gridPane.add(ruleField,1,0);
        gridPane.add(borderLabel,0,1);
        gridPane.add(borderBox, 1,1);
        borderPane.setTop(gridPane);
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene= stage.getScene();*/

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}

