package by.pvt.mazanov.stone.beans;

import by.pvt.mazanov.stone.enums.StoneType;

/**
 * Created by mazanov on 11.03.17.
 */
public class PrecisiousStone extends Stone {

    public PrecisiousStone(int id, String name, double weight, double cost) {
        super(id, name,  StoneType.PRECISIOUS, weight, cost);
    }
}
