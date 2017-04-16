package by.pvt.mazanov.stone.beans;

import by.pvt.mazanov.stone.enums.StoneType;

/**
 * Created by mazanov on 18.03.17.
 */

//Factory method
public class StoneSelector {
    public Stone getStone(int id, StoneType type, String name, double weight, double cost){
        Stone stone = null;
        switch (type){
            case PRECISIOUS:
                stone = new PrecisiousStone(id, name, weight, cost);
                break;
            case SEMIPRECISIOUS:
                stone = new SemiprecisiousStone(id, name, weight, cost);
                break;
        }
        return stone;
    }
}
