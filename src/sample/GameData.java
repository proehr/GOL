package sample;

import java.util.ArrayList;
import java.util.List;

public class GameData {
    private final static GameData gameInstance = new GameData();

    public static GameData getInstance(){
        return gameInstance;
    }

    private int width;
    private int height;
    private List<int[]> lifeList = new ArrayList<>();
    private String[] rule0;
    private String[] rule1;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void addLife(int row, int column) {
        int[] arr = {row,column};
        lifeList.add(arr);
    }

    public void setRule0(String[] rule0) {
        this.rule0 = rule0;
    }

    public String[] getRule0() {
        return rule0;
    }

    public void setRule1(String[] rule1) {
        this.rule1 = rule1;
    }

    public String[] getRule1() {
        return rule1;
    }
}
