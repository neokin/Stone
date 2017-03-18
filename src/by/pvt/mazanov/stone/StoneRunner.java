package by.pvt.mazanov.stone;

import by.pvt.mazanov.stone.beans.Necklace;
import by.pvt.mazanov.stone.beans.Stone;
import by.pvt.mazanov.stone.beans.StoneSelector;
import by.pvt.mazanov.stone.comparators.StoneNameComparator;
import by.pvt.mazanov.stone.enums.StoneType;
import by.pvt.mazanov.stone.interfaces.Expression;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StoneRunner {

    public static void main(String[] args) throws IOException {

        BufferedReader bReader = new BufferedReader(new FileReader("src/by/pvt/mazanov/stone/files/InputData"));

        String lineContents;
        String[] tok;
        StoneSelector stoneSelector = new StoneSelector();
        Necklace necklace1 = new Necklace(new ArrayList<>());

        while ((lineContents = bReader.readLine())
                != null) {
            tok = lineContents.split(" +");


            Stone stone = stoneSelector.getStone(StoneType.valueOf(tok[0]), tok[1], Double.parseDouble(tok[2]), Double.parseDouble(tok[3]));
            necklace1.addStone(stone);


        }
        bReader.close();


        Expression expPrice = (Stone stone) -> {
            return stone.getCost() > 0 && stone.getCost() < 11;
        };
        for (Stone s : necklace1.find(expPrice)) {
            s.printStone();
        }


        for(Stone s : necklace1.sort(new StoneNameComparator())){
            s.printStone();
        }


        System.out.println("Cost " + necklace1.getCost());
        System.out.println("Weight " + necklace1.getWeight());
    }
}
