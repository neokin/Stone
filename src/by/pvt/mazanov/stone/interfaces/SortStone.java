package by.pvt.mazanov.stone.interfaces;

import by.pvt.mazanov.stone.beans.Stone;

import java.util.Comparator;

/**
 * Created by mazanov on 11.03.17.
 */
public interface SortStone {
    Iterable<Stone> sort(Comparator<Stone> comparator);
}
