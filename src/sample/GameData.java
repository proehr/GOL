package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameData {
    private final static GameData gameInstance = new GameData();

    static GameData getInstance(){
        return gameInstance;
    }

    private Rectangle[][] rectangles;
    private int width;
    private int height;
    private int[][] lifeList;
    private String rule0;
    private String rule1;
    public ScheduledExecutorService ses;

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    void setRule0(String rule0) {
        this.rule0 = rule0;
    }

    void setRule1(String rule1) {
        this.rule1 = rule1;
    }

    void setData(int height, int width) {
        this.height=height;
        this.width=width;
        this.lifeList=new int[height][width];
    }

    public void setRectangles(Rectangle[][] rectangles) {
        this.rectangles= rectangles;
    }

    void changeLife(int row, int column) {
        if(lifeList[row][column]==0) {
            lifeList[row][column] = 1;
            rectangles[row][column].setFill(Color.DARKGRAY);
        }else{
            lifeList[row][column] = 0;
            rectangles[row][column].setFill(Color.WHITE);
        }
    }

    void start(){
        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                int[][] count = new int[height][width];
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        count[i][j] = countAround(i, j);
                    }
                }
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        checkRule(i, j, count[i][j]);
                    }
                }
            }
        },500,500, TimeUnit.MILLISECONDS);

    }

    private void checkRule(int i, int j, int counter) {
        if(lifeList[i][j]==1){
            if(!rule1.contains(Integer.toString(counter))){
                lifeList[i][j] = 0;
                rectangles[i][j].setFill(Color.WHITE);
            }
        }else{
            if(rule0.contains(Integer.toString(counter))){
                lifeList[i][j] = 1;
                rectangles[i][j].setFill(Color.DARKGRAY);
            }
        }
    }

    private int countAround(int row, int column) {
        int counter=0;
        for(int i = row-1; i<=row+1; i++){
            for(int j = column-1;j<=column+1;j++){
                if(i != row || j != column){
                    try {
                        counter+=lifeList[i][j];
                    }catch(Exception ignored){

                    }
                }
            }
        }
        return counter;
    }


    public void terminateSES() {
        ses.shutdown();
    }
}
