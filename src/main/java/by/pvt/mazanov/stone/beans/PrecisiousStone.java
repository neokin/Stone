package by.pvt.mazanov.stone.beans;

import by.pvt.mazanov.stone.enums.StoneType;

/**
 * Created by mazanov on 11.03.17.
 */
public class PrecisiousStone extends Stone {

    public PrecisiousStone(String name, double cost, double weight) {
        super(name,  StoneType.PRECISIOUS, cost, weight);
    }
}
