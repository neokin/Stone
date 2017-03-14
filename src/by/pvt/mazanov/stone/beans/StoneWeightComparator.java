package by.pvt.mazanov.stone.beans;

import java.util.Comparator;

public class StoneWeightComparator implements Comparator<Stone> {
    @Override
    public int compare(Stone s1, Stone s2) {
        return (s1.getWeight() > s2.getWeight()) ? 1 : ((s1.getWeight() < s2.getWeight()) ? -1 : 0);
    }
}

