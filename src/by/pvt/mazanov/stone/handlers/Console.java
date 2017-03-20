package by.pvt.mazanov.stone.handlers;

import by.pvt.mazanov.stone.beans.Necklace;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by mazanov on 18.03.17.
 */
public abstract class Console {
    Scanner scanner = new Scanner(System.in);
    String input, min, max;

    public Console() {
    }

    public abstract void startCon(Necklace nc, PrintWriter fw, String type) throws IOException;
}
