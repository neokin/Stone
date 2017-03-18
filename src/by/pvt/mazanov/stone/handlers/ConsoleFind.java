package by.pvt.mazanov.stone.handlers;

import by.pvt.mazanov.stone.beans.Necklace;
import by.pvt.mazanov.stone.beans.Stone;
import by.pvt.mazanov.stone.comparators.StoneCostComparator;
import by.pvt.mazanov.stone.comparators.StoneNameComparator;
import by.pvt.mazanov.stone.comparators.StoneWeightComparator;
import by.pvt.mazanov.stone.interfaces.Expression;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mazanov on 18.03.17.
 */
public class ConsoleFind extends Console{
    Expression exp;
    String name;
    public void startcon(Necklace nc, PrintWriter fw, String type) throws IOException {

        while (true) {
            System.out.println("Would you like to find the stones by " + type + "?(y/n)");
            input = scanner.nextLine();

            if ("y".equals(input)) {
                fw.write("Stones found by " + type +"\n");
                fw.write("Type    name   cost   weight\n");
                switch (type) {
                    case "name":
                        System.out.println("Enter begin characters ");
                        name = scanner.nextLine();
                        exp = (Stone stone) -> {
                            //System.out.println("name = "+ name +"    substr = " + stone.getName().substring(0, name.length()));
                            return name.equals(stone.getName().substring(0, name.length()));
                        };
                        find(exp,  nc, fw);
                        break;

                    case "cost":
                        getMinMax("cost");
                        exp = (Stone stone) -> {
                            return stone.getCost() > Integer.parseInt(min) && stone.getCost() < Integer.parseInt(max);
                        };
                        find(exp,  nc, fw);
                        break;

                    case "weight":
                        getMinMax("weight");
                        exp = (Stone stone) -> {
                            return stone.getCost() > Integer.parseInt(min) && stone.getCost() < Integer.parseInt(max);
                        };
                        find(exp,  nc, fw);
                        break;
                }

            } else if ("n".equals(input)) {
                break;


            } else {
                System.out.println("Please, type 'y' to agree or 'n' to disagree");
                continue;
            }
            fw.write('\n');
            break;
        }

    }

    public void find(Expression exp, Necklace nc, PrintWriter fw) throws IOException {
        for (Stone s : nc.find(exp)) {
            //s.printStone();
            s.writeStone(fw);
        }
    }

    public void getMinMax(String str){
        System.out.println("Enter the min " + str);
        min = scanner.nextLine();
        System.out.println("Enter the max " + str);
        max = scanner.nextLine();
    }

}

