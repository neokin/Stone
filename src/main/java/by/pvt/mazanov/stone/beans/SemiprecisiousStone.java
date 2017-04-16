package by.pvt.mazanov.stone.beans;

import by.pvt.mazanov.stone.enums.StoneType;


public class SemiprecisiousStone extends  Stone {

    public SemiprecisiousStone(int id, String name, double weight, double cost) {
        super(id, name,  StoneType.SEMIPRECISIOUS, weight, cost);
    }
}
