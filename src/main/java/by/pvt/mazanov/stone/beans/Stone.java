package by.pvt.mazanov.stone.beans;

import by.pvt.mazanov.stone.enums.StoneType;

import java.io.IOException;
import java.io.PrintWriter;

public class Stone {
    private int id;
    private double weight;
    private double cost;
    private String name;
    private StoneType type;


    public Stone(Stone stone) {
        this(stone.getId(), stone.getName(), stone.getType(), stone.getWeight(), stone.getCost());
    }

    public Stone(int id, String name, StoneType type, double weight, double cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.weight = weight;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StoneType getType() {
        return type;
    }

    public void setType(StoneType type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void printStone(){
        System.out.println("Weight "+this.weight+" "+ "Cost "+this.cost+" "+ "Name "+this.name+" "+ "Type "+this.type);
    }

    public void writeStone(PrintWriter writer) throws IOException {
        writer.println(this.type+" "+this.name+" "+this.cost +" "+this.weight);

    }

}
