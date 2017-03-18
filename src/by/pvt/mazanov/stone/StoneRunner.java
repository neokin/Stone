package by.pvt.mazanov.stone;

import by.pvt.mazanov.stone.beans.*;
import by.pvt.mazanov.stone.enums.StoneType;
import by.pvt.mazanov.stone.handlers.*;
import by.pvt.mazanov.stone.interfaces.Expression;

import java.io.*;
import java.util.ArrayList;

public class StoneRunner {

    public static void main(String[] args) throws IOException {
        Necklace necklace1 = new Necklace(new ArrayList<>());
        BufferedReader bReader = new BufferedReader(new FileReader("src/by/pvt/mazanov/stone/files/InputData"));
        PrintWriter fWriter = new PrintWriter(new FileWriter("src/by/pvt/mazanov/stone/files/OutputData"));

        String lineContents;
        String[] tok;
        StoneSelector stoneSelector = new StoneSelector();
        ConsoleSort consoleSort = new ConsoleSort();
        ConsoleFind consoleFind = new ConsoleFind();


        bReader.readLine();
        while ((lineContents = bReader.readLine())
                != null) {
            tok = lineContents.split(" +");
            Stone stone = stoneSelector.getStone(StoneType.valueOf(tok[0]), tok[1], Double.parseDouble(tok[2]), Double.parseDouble(tok[3]));
            necklace1.addStone(stone);
        }
        bReader.close();

        consoleSort.startcon(necklace1, fWriter, "name");
        consoleSort.startcon(necklace1, fWriter, "cost");
        consoleSort.startcon(necklace1, fWriter, "weight");

        consoleFind.startcon(necklace1, fWriter, "name");
        consoleFind.startcon(necklace1, fWriter, "cost");
        consoleFind.startcon(necklace1, fWriter, "weight");

        fWriter.println("Total Cost " + necklace1.getCost());
        fWriter.println("Total Weight " + necklace1.getWeight());
        fWriter.close();
    }



}
