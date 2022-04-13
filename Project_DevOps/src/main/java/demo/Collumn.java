package demo;

import java.util.ArrayList;

public class Collumn<T> {
    
    private String label;
    private ArrayList<T> values;

    Collumn(String lab, ArrayList<T> data){
        this.values = data;
        this.setLabel(lab);
    }

    Collumn(ArrayList<T> data){
        this.values = data;
    }

    public ArrayList<T> getValues() {
        return values;
    }

    public void addValue(T value){
        this.values.add(value);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String lab) {
        this.label = lab;
    }
}
