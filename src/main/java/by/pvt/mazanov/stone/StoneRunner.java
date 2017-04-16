package by.pvt.mazanov.stone;

import by.pvt.mazanov.stone.beans.*;
import by.pvt.mazanov.stone.enums.StoneType;

import by.pvt.mazanov.stone.beans.Necklace;
import by.pvt.mazanov.stone.beans.Stone;
import by.pvt.mazanov.stone.beans.StoneSelector;
import by.pvt.mazanov.stone.handlers.*;


import java.io.*;
import java.util.ArrayList;


public class StoneRunner {

    public static final String INPUT_FILE = "src/main/java/by/pvt/mazanov/stone/InputData";
    public static final String OUTPUT_FILE = "src/main/java/by/pvt/mazanov/stone/OutputData";

    public static void main(String[] args) throws IOException {
        Necklace necklace1 = new Necklace(new ArrayList<>());
        BufferedReader bReader = null;
        PrintWriter fWriter = null;
        try {
             bReader = new BufferedReader(new FileReader(INPUT_FILE));
             fWriter = new PrintWriter(new FileWriter(OUTPUT_FILE));
        }
        catch (FileNotFoundException ex){
            System.out.println("Error: " + ex.getMessage());
            System.exit(1);
        }

        String lineContents;
        String[] words;
        StoneSelector stoneSelector = new StoneSelector();
        ConsoleSort consoleSort = new ConsoleSort();
        ConsoleFind consoleFind = new ConsoleFind();


        bReader.readLine();
        while ((lineContents = bReader.readLine()) != null) {
            words = lineContents.split(" +");
            try {
                Stone stone = stoneSelector.getStone(0, StoneType.valueOf(words[0]), words[1], Double.parseDouble(words[2]), Double.parseDouble(words[3]));
                necklace1.addStone(stone);
            }
            catch(NumberFormatException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        bReader.close();

        consoleSort.startCon(necklace1, fWriter, "name");
        consoleSort.startCon(necklace1, fWriter, "cost");
        consoleSort.startCon(necklace1, fWriter, "weight");

        consoleFind.startCon(necklace1, fWriter, "name");
        consoleFind.startCon(necklace1, fWriter, "cost");
        consoleFind.startCon(necklace1, fWriter, "weight");

        fWriter.println("Total Cost " + necklace1.getCost());
        fWriter.println("Total Weight " + necklace1.getWeight());
        fWriter.close();
    }

}
