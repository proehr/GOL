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



    private int rectangleSize;
    private int width;
    private int height;
    private int[][] lifeList;
    private String rule0;
    private String rule1;
    public ScheduledExecutorService ses;
    private int border;                                 // 0 = all dead, 1 = all alive, 2 = go around
    private String[] paintList={"C62828", "AD1457" , "6A1B9A", "4527A0" , "283593" ,
            "1565C0" , "0277BD" , "00838F" , "00695C", "2E7D32" , "558B2F" , "9E9D24" ,
            "F9A825" , "FF8F00" , "EF6C00" , "D84315" };

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    public int getRectangleSize() {
        return rectangleSize;
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
        if(height>61 || width>=98) {
            this.rectangleSize = 613 / height;
        }
        else if(height>30 || width >= 49){
            this.rectangleSize = 10;
        }
        else{
            this.rectangleSize = 20;
        }
        System.out.println(rectangleSize);
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

    private Color getRandomColor() {
        int rndm = (int)(Math.random()*16);
        String s = "0X" + paintList[rndm];
        return Color.web(s);
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
        },50,50, TimeUnit.MILLISECONDS);

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
                    }catch(Exception e){
                        counter += borderpatrol(i,j);
                    }
                }
            }
        }
        return counter;
    }

    private int borderpatrol(int i, int j) {
        if(border == 2){
            if((i<0 || i >= height)  && (j<0 || j>=width)){
                return lifeList[height-Math.abs(i)][width-Math.abs(j)];
            }
            if(i<0 || i>=height){
                return lifeList[height-Math.abs(i)][j];
            }else{
                return lifeList[i][width-Math.abs(j)];
            }
        }
        else{
            return border;
        }
    }




    public void terminateSES() {
        ses.shutdown();
    }

    public void setBorder(int border) {
        this.border = border;
    }
}
