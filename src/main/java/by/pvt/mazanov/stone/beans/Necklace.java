package by.pvt.mazanov.stone.beans;

import by.pvt.mazanov.stone.interfaces.Expression;
import by.pvt.mazanov.stone.interfaces.FindStone;
import by.pvt.mazanov.stone.interfaces.SortStone;
import by.pvt.mazanov.stone.interfaces.TotalAmount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mazanov on 11.03.17.
 */
public class Necklace  implements TotalAmount, SortStone, FindStone {
    List<Stone> stonesList;
    int id;
    String name;

    public Necklace(int id, String name) {
        this.id = id; this.name = name;
    }

    public Necklace(List<Stone> stonesList) {
        this.stonesList = stonesList;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public List<Stone> getStonesList() {
        return stonesList;
    }

    public void setStonesList(List<Stone> stonesList) {
        this.stonesList = stonesList;
    }

    public void addStone(Stone stone){
        this.stonesList.add(stone);
    }

    @Override
    public Iterable<Stone> find(Expression exp) {
        List<Stone> findStones = new ArrayList<Stone>();
        for (Stone stone: stonesList) {
            if (exp.inSet(stone)){
                findStones.add(stone);
            }
        }
        return findStones;
    }

    @Override
    public Iterable<Stone> sort(Comparator<Stone> comparator) {
        List<Stone> sortStones = new ArrayList<Stone>(stonesList.size());
        for (Stone stone : stonesList) {
            sortStones.add(new Stone(stone));
        }
        Collections.sort(sortStones, comparator);
        return sortStones;
    }

    @Override
    public double getCost() {
        double  totalCost = 0;
        for(Stone stone: stonesList){
            totalCost += stone.getCost();
        }
        return totalCost;

    }


    @Override
    public double getWeight() {
        double  totalWeight = 0;
        for(Stone stone: stonesList){
            totalWeight += stone.getWeight();
        }
        return totalWeight;
    }
}
