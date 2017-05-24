package com.luis.neoncity.Tools;

/**
 * Created by Jacob on 5/19/2017.
 */

public class Tile {
    boolean isUsable;
    int building;

    public Tile(boolean isUsable){
        this.isUsable = isUsable;
    }

    public int getBuilding() {
        System.out.println("Index: " + building);
        return building;
    }

    public void setBuilding(int building) {
        System.out.println("Index: " + building);
        this.building = building;
    }
    public boolean isUsable() {
        return isUsable;
    }

    public void setUsable(boolean usable) {
        isUsable = usable;
    }

    public String toString(){
        return ""+isUsable();
    }
}
