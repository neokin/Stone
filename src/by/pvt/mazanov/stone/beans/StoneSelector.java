package by.pvt.mazanov.stone.beans;

import by.pvt.mazanov.stone.enums.StoneType;

/**
 * Created by mazanov on 18.03.17.
 */

//Factory method
public class StoneSelector {
    public Stone getStone(StoneType type, String name, double cost, double weight){
        Stone stone = null;
        switch (type){
            case PRECISIOUS:
                stone = new PrecisiousStone(name, cost, weight);
                break;
            case SEMIPRECISIOUS:
                stone = new SemiprecisiousStone(name, cost, weight);
                break;
        }
        return stone;
    }
}
