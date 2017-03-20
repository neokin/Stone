package by.pvt.mazanov.stone.handlers;

import by.pvt.mazanov.stone.beans.Necklace;
import by.pvt.mazanov.stone.beans.Stone;
import by.pvt.mazanov.stone.comparators.StoneCostComparator;
import by.pvt.mazanov.stone.comparators.StoneNameComparator;
import by.pvt.mazanov.stone.comparators.StoneWeightComparator;

import java.io.IOException;
import java.io.PrintWriter;
/**
 * Created by mazanov on 14.03.17.
 */
public class ConsoleSort extends Console {

    @Override
    public void startCon(Necklace nc, PrintWriter fw, String type) throws IOException {

        while (true) {
            System.out.println("Would you like to sort the stones by " + type + "?(y/n)");
            input = scanner.nextLine();

            if ("y".equals(input)) {
                fw.write("Stones sorted by " + type +"\n");
                fw.write("Type    name   cost   weight\n");
                switch (type) {
                    case "name":
                        for (Stone s : nc.sort(new StoneNameComparator())) {
                            s.writeStone(fw);
                        }
                        break;

                    case "cost":
                        for (Stone s : nc.sort(new StoneCostComparator())) {
                            s.writeStone(fw);
                        }
                        break;

                    case "weight":
                        for (Stone s : nc.sort(new StoneWeightComparator())) {
                            s.writeStone(fw);
                        }
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

}