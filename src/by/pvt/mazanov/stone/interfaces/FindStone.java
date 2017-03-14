package by.pvt.mazanov.stone.interfaces;

import by.pvt.mazanov.stone.beans.Stone;

import java.util.List;

/**
 * Created by mazanov on 11.03.17.
 */
public interface FindStone {
     Iterable<Stone> find(Expression exp);
}
