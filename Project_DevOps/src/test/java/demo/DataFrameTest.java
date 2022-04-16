package demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class DataFrameTest {

    String[] label = {"Fruits", "Prix/Kg", "Calories"};
    String[] Fruits = {"Pomme", "Poire", "Fraise", "Orange", "Banane", "Framboise", "Peche", "Abricots"};
    Double[] Prix = {3.56, 5.78, 10.09, 2.5, 2.5, 15.89, 5.11, 0.99};
    Double[] Calories = {200.7, 298.0, 900.7, 98.0, 678.0, 67.9, 987.0, 1999.98};

    
    String[] Fruits2 = {"Fraise", "Framboise"};
    Double[] Prix2 = {10.09, 15.89};
    Double[] Calories2 = {900.7, 67.9};

    String[] label3 = {"Fruits", "Prix/Kg"};
    String[] Fruits3 = {"Pomme", "Poire", "Fraise", "Orange", "Banane", "Framboise", "Peche", "Abricots"};
    Double[] Prix3 = {3.56, 5.78, 10.09, 2.5, 2.5, 15.89, 5.11, 0.99};


    String[] Fruits4 = {"Pomme", "Poire", "Orange", "Banane", "Peche", "Abricots"};
    Double[] Prix4 = {3.56, 5.78, 2.5, 2.5, 5.11, 0.99};
    Double[] Calories4 = {200.7, 298.0, 98.0, 678.0, 987.0, 1999.98};

        
    @Test
    public void testDataFromIndex(){
        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label);
        data.add(Fruits);
        data.add(Prix);
        data.add(Calories);

        DataFrame dataFrame = new DataFrame(data, false);

        DataFrame newDataFrame = dataFrame.getDataFrameFromIndex(2, 5);

        ArrayList<Object> data2 = new ArrayList<Object>();
        data2.add(label);
        data2.add(Fruits2);
        data2.add(Prix2);
        data2.add(Calories2);

        assertTrue(newDataFrame.equals( new DataFrame(data2, false)));
    }

    @Test
    public void testDataFromCollumns(){
        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label);
        data.add(Fruits);
        data.add(Prix);
        data.add(Calories);

        DataFrame dataFrame = new DataFrame(data, false);

        DataFrame newDataFrame = dataFrame.getDataFrameFromCollumns("Fruits", "Prix/Kg");

        ArrayList<Object> data3 = new ArrayList<Object>();
        data3.add(label3);
        data3.add(Fruits3);
        data3.add(Prix3);

        assertTrue(newDataFrame.equals( new DataFrame(data3, false)));
    }


    @Test
    public void testDataFromBooleanIndexing(){
        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label);
        data.add(Fruits);
        data.add(Prix);
        data.add(Calories);

        DataFrame dataFrame = new DataFrame(data, false);

        DataFrame newDataFrame = dataFrame.getDataFrameFromBooleanIndexing("Prix/Kg", "<", 10.0);

        ArrayList<Object> data4 = new ArrayList<Object>();
        data4.add(label);
        data4.add(Fruits4);
        data4.add(Prix4);
        data4.add(Calories4);


        assertTrue(newDataFrame.equals( new DataFrame(data4, false)));
    }
    
}
