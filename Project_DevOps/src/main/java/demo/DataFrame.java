package demo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class DataFrame
{

    ArrayList<Object> dataFrame;

    /**
     * @param strucList
     * 0    : Lables
     * 1    : collone 1 du type T
     * .
     * .
     * n    : collone n du type T
     */

    // Constructeur à partir d'un tableau
    DataFrame( ArrayList<Object> strucList ){

        ArrayList<Object> data = new ArrayList<Object>();

        // Recuperation des lables -> 1iere ligne
        String[] lab = (String[]) strucList.get(0);

        // Parcour des collones
        for (int col = 1; col < strucList.size(); col++) {

            Object[] array = (Object[])strucList.get(col);

            if(array[0].getClass() == Integer.class){
                data.add(new Collumn<Integer>(lab[col-1], new ArrayList<Integer>(Arrays.asList((Integer[])array))));
            }
            if(array[0].getClass() == String.class){
                data.add(new Collumn<String>(lab[col-1], new ArrayList<String>(Arrays.asList((String[])array))));
            }
            if(array[0].getClass() == Float.class || array[0].getClass() == Double.class){
                data.add(new Collumn<Double>(lab[col-1], new ArrayList<Double>(Arrays.asList((Double[])array))));
            }
        }
        dataFrame = data;
    }

    // Constructeur à partir d'un fichier CSV
    DataFrame( String fileName ){

    }

    public static void main( String[] args )
    {
        ArrayList<Object> data = new ArrayList<Object>();
        String[] label = {"Manger", "Bouger", "Dormir"};
        String[] manger = {"Pomme", "Poire", "Fraise"};
        Integer[] bouger = {9,89,999};
        Integer[] dormir = {456,788,0};

        data.add(label);
        data.add(manger);
        data.add(bouger);
        data.add(dormir);

        DataFrame dataFrame = new DataFrame(data);

    }
}
