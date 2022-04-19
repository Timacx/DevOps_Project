package demo;

import java.util.ArrayList;

public class Collumn<T extends Comparable<T>> {

    private String label;
    private ArrayList<T> values;

    Collumn(String lab, ArrayList<T> data) {
        this.values = data;
        this.setLabel(lab);
    }

    Collumn(ArrayList<T> data) {
        this.values = data;
    }

    public ArrayList<T> getValues() {
        return values;
    }

    public void addValue(T value) {
        this.values.add(value);
    }

    public Collumn<T> leaveOnly(int... indexs) {
        ArrayList<T> newValues = new ArrayList<T>();

        for (int ind : indexs)
            newValues.add(values.get(ind));

        return new Collumn<T>(label, newValues);

    }

    public int[] getIndex(String comp, T value) {
        ArrayList<Integer> indexs = new ArrayList<Integer>();

        if (comp.equals(">")) {
            for (int i = 0; i < values.size(); i++)
                if (values.get(i).compareTo(value) > 0)
                    indexs.add(i);
        }
        if (comp.equals(">=")) {
            for (int i = 0; i < values.size(); i++)
                if (values.get(i).compareTo(value) >= 0)
                    indexs.add(i);
        }
        if (comp.equals("<")) {
            for (int i = 0; i < values.size(); i++)
                if (values.get(i).compareTo(value) < 0)
                    indexs.add(i);
        }
        if (comp.equals("<=")) {
            for (int i = 0; i < values.size(); i++)
                if (values.get(i).compareTo(value) <= 0)
                    indexs.add(i);

        }
        if (comp.equals("=")) {
            for (int i = 0; i < values.size(); i++)
                if (values.get(i).compareTo(value) == 0)
                    indexs.add(i);
        }

        return convertIntegers(indexs);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String lab) {
        this.label = lab;
    }

    public static int[] convertIntegers(ArrayList<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    public boolean equals(Collumn<?> collumn){
        if(! this.label.equals(collumn.getLabel()))
            return false;

        if(! this.values.equals(collumn.getValues()))
            return false;

        return true;
    }
}
