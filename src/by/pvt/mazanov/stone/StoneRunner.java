package by.pvt.mazanov.stone;

import by.pvt.mazanov.stone.beans.*;
import by.pvt.mazanov.stone.interfaces.Expression;

import java.util.ArrayList;

public class StoneRunner {

    public static void main(String[] args) {

        Stone almaz = new PrecisiousStone("алмаз", 13, 7);
        Stone rubin = new PrecisiousStone("рубин",  10, 10);
        Stone sapfir = new PrecisiousStone("сапфир",  4, 5);
        Stone izumrud = new PrecisiousStone("изумруд",  10, 10);

        Stone ametit = new SemiprecisiousStone("аметит", 10, 10);
        Stone giacint = new SemiprecisiousStone("гиацинт",  10, 10);
        Stone granat = new SemiprecisiousStone("гранат",  10, 10);
        Necklace necklace1 = new Necklace(new ArrayList<>());
        necklace1.addStone(almaz);
        necklace1.addStone(rubin);
        necklace1.addStone(sapfir);

        //Expression expWeight = (Stone stone) -> { return stone.getWeight() > 0 && stone.getWeight() < 11; };
        Expression expPrice = (Stone stone) -> { return stone.getCost() > 0 && stone.getCost() < 11; };
        for(Stone s : necklace1.find(expPrice)){
            s.printStone();
        }

/*
        for(Stone s : necklace1.sort(new StoneNameComparator())){
            s.printStone();
        }*/


        System.out.println("Cost "+necklace1.getCost());
        System.out.println("Weight " +necklace1.getWeight());
    }
}
