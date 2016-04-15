package com.common.payments;

/**
 * Created by SemmEs on 13.04.2016.
 */
public class Payment {

    String data;
    double cost;
    int count;
    String lastColumn;

    public Payment() {
    }

    public Payment(String data, double cost, int count, String lastColumn) {
        this.data = data;
        this.cost = cost;
        this.count = count;
        this.lastColumn = lastColumn;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLastColumn() {
        return lastColumn;
    }

    public void setLastColumn(String lastColumn) {
        this.lastColumn = lastColumn;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "data='" + data + '\'' +
                ", cost=" + cost +
                ", count=" + count +
                ", lastColumn='" + lastColumn + '\'' +
                '}';
    }
}
