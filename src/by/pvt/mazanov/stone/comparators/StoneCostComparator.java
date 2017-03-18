package by.pvt.mazanov.stone.comparators;

import by.pvt.mazanov.stone.beans.Stone;

import java.util.Comparator;

/**
 * Created by mazanov on 11.03.17.
 */
public class StoneCostComparator implements Comparator<Stone>{
    @Override
    public int compare(Stone s1, Stone s2) {
        return (s1.getCost() > s2.getCost()) ? 1 : ((s1.getCost() < s2.getCost()) ? -1 : 0);
    }
}
