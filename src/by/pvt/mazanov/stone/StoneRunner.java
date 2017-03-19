package by.pvt.mazanov.stone;

import by.pvt.mazanov.stone.beans.*;
import by.pvt.mazanov.stone.enums.StoneType;
import by.pvt.mazanov.stone.handlers.*;


import java.io.*;
import java.util.ArrayList;

public class StoneRunner {

    public static void main(String[] args) throws IOException {
        Necklace necklace1 = new Necklace(new ArrayList<>());
        BufferedReader bReader = null;
        PrintWriter fWriter = null;
        try {
             bReader = new BufferedReader(new FileReader("src/by/pvt/mazanov/stone/files/InputData"));
             fWriter = new PrintWriter(new FileWriter("src/by/pvt/mazanov/stone/files/OutputData"));
        }
        catch (FileNotFoundException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        String lineContents;
        String[] tok;
        StoneSelector stoneSelector = new StoneSelector();
        ConsoleSort consoleSort = new ConsoleSort();
        ConsoleFind consoleFind = new ConsoleFind();


        bReader.readLine();
        while ((lineContents = bReader.readLine()) != null) {
            tok = lineContents.split(" +");
            try {
                Stone stone = stoneSelector.getStone(StoneType.valueOf(tok[0]), tok[1], Double.parseDouble(tok[2]), Double.parseDouble(tok[3]));
                necklace1.addStone(stone);
            }
            catch(NumberFormatException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
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
