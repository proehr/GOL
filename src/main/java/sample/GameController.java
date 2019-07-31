package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    public AnchorPane anchor;
    public GridPane sceneGrid;
    public TextField ruleField0;
    public ComboBox borderBox;
    private GridPane fieldGrid;
    public TextField ruleField1;
    private Rectangle[][] rectangles;
    public javafx.scene.control.Button Button;


    EventHandler handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            int row = (int)(mouseEvent.getY()/20);
            int column = (int)(mouseEvent.getX()/20);
            GameData.getInstance().changeLife(row,column);
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fieldGrid = createField(GameData.getInstance().getWidth(),GameData.getInstance().getHeight(), GameData.getInstance().getRectangleSize());
        sceneGrid.add(fieldGrid, 0,2,5,1);
        fieldGrid.setOnMouseClicked(handler);
    }

    public static void main(String[] args){
        Application.launch(Main.class, args);
    }

    public GridPane createField(int width,int height, int rs){
        GridPane gp = new GridPane();
        rectangles = new Rectangle[height][width];
        for(int i = 0; i < width; i++) {
            ColumnConstraints column = new ColumnConstraints(rs);
            gp.getColumnConstraints().add(column);
        }

        for(int i = 0; i < height; i++) {
            RowConstraints row = new RowConstraints(rs);
            gp.getRowConstraints().add(row);
        }
        gp.setStyle("-fx-grid-lines-visible: true");
        for(int i = 0;i < height; i++){
            for(int j = 0; j < width; j++){
                Rectangle r = new Rectangle();
                r.setX(0);
                r.setY(0);
                r.setHeight(rs);
                r.setWidth(rs);
                r.setFill(Color.WHITE);
                rectangles[i][j]=r;
                gp.add(rectangles[i][j], j, i);
            }
        }
        GameData.getInstance().setRectangles(rectangles);
        return gp;
    }

    public void startGame(ActionEvent actionEvent) {
        GameData.getInstance().setRule0(ruleField0.getText());
        GameData.getInstance().setRule1(ruleField1.getText());
        GameData.getInstance().setBorder(borderBox.getSelectionModel().getSelectedIndex());
        Button.setText("Stop!");
        Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GameData.getInstance().terminateSES();
                Button.setText("Start!");
                changeButton();
            }
        });
        GameData.getInstance().start();
    }

    public void changeButton(){
        Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startGame(actionEvent);
            }
        });
    }
}

