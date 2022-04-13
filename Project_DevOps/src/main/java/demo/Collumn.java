package demo;

import java.util.ArrayList;

public class Collumn<T> {
    
    private String label;
    private ArrayList<T> values;

    Collumn(String lab, ArrayList<T> data){
        this.values = data;
        this.label = lab;
    }

    public ArrayList<T> getValues() {
        return values;
    }

    public String getLabel() {
        return label;
    }
}
