package by.pvt.mazanov.stone.comparators;

import by.pvt.mazanov.stone.beans.Stone;

import java.util.Comparator;

/**
 * Created by mazanov on 11.03.17.
 */
public class StoneNameComparator implements Comparator<Stone> {
    @Override
    public int compare(Stone s1, Stone s2) {
        return s1.getName().compareTo(s2.getName());
    }
}
