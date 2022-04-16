package demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class DataFrameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    String[] label = { "Fruits", "Prix/Kg", "Calories" };
    String[] Fruits = { "Pomme", "Poire", "Fraise", "Orange", "Banane", "Framboise", "Peche", "Abricots" };
    Double[] Prix = { 3.56, 5.78, 10.09, 2.5, 2.5, 15.89, 5.11, 0.99 };
    Double[] Calories = { 200.7, 298.0, 900.7, 98.0, 678.0, 67.9, 987.0, 1999.98 };

    String[] Fruits2 = { "Fraise", "Framboise" };
    Double[] Prix2 = { 10.09, 15.89 };
    Double[] Calories2 = { 900.7, 67.9 };

    String[] label3 = { "Fruits", "Prix/Kg" };
    String[] Fruits3 = { "Pomme", "Poire", "Fraise", "Orange", "Banane", "Framboise", "Peche", "Abricots" };
    Double[] Prix3 = { 3.56, 5.78, 10.09, 2.5, 2.5, 15.89, 5.11, 0.99 };

    String[] Fruits4 = { "Pomme", "Poire", "Orange", "Banane", "Peche", "Abricots" };
    Double[] Prix4 = { 3.56, 5.78, 2.5, 2.5, 5.11, 0.99 };
    Double[] Calories4 = { 200.7, 298.0, 98.0, 678.0, 987.0, 1999.98 };

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testEquals() {

        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label);
        data.add(Fruits);
        data.add(Prix);
        data.add(Calories);

        DataFrame dataFrame = new DataFrame(data, false);
        DataFrame dataFrame2 = new DataFrame(data, false);

        assertTrue(dataFrame.equals(dataFrame2));

    }

    @Test
    public void testDataFromIndex() {
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

        assertTrue(newDataFrame.equals(new DataFrame(data2, false)));
    }

    @Test
    public void testDataFromCollumns() {
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

        assertTrue(newDataFrame.equals(new DataFrame(data3, false)));
    }

    @Test
    public void testDataFromBooleanIndexing() {
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

        assertTrue(newDataFrame.equals(new DataFrame(data4, false)));
    }

    @Test
    public void testDisplayAll() {

        String[] label_v = { "Voiture", "Prix", };
        String[] voitures = { "BMW", "Renault" };
        Double[] Prix_v = { 100_999.99, 30_499.0 };

        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label_v);
        data.add(voitures);
        data.add(Prix_v);

        // String output = "Voiture        Prix           \nBMW            100999.99      \nRenault        30499.0        \n";
        String output = String.format("%-15s%-15s\n%-15s%-15s\n%-15s%-15s\n", "Voiture", "Prix", "BMW", "100999.99", "Renault", "30499.0");


        DataFrame dataFrame = new DataFrame(data, false);
        dataFrame.displayAll();
        assertEquals(output, outContent.toString());
    }

    @Test
    public void testDisplayFirstLines() {

        String[] label_v = { "Voiture", "Prix", };
        String[] voitures = { "BMW", "Renault", "Peugeot", "Honda" };
        Double[] Prix_v = { 100_999.99, 30_499.0, 29_999.59, 45_500.0 };

        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label_v);
        data.add(voitures);
        data.add(Prix_v);

        String output = String.format("%-15s%-15s\n%-15s%-15s\n%-15s%-15s\n%-15s%-15s\n", "Voiture", "Prix", "BMW", "100999.99", "Renault", "30499.0", "Peugeot", "29999.59");


        DataFrame dataFrame = new DataFrame(data, false);
        dataFrame.displayFirstLines();
        assertEquals(output, outContent.toString());
    }

    @Test
    public void testDisplayLastLines() {

        String[] label_v = { "Voiture", "Prix", };
        String[] voitures = { "BMW", "Renault", "Peugeot", "Honda" };
        Double[] Prix_v = { 100_999.99, 30_499.0, 29_999.59, 45_500.0 };

        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label_v);
        data.add(voitures);
        data.add(Prix_v);

        String output = String.format("%-15s%-15s\n%-15s%-15s\n%-15s%-15s\n%-15s%-15s\n", "Voiture", "Prix", "Renault", "30499.0", "Peugeot", "29999.59", "Honda", "45500.0");

        DataFrame dataFrame = new DataFrame(data, false);
        dataFrame.displayLastLines();
        assertEquals(output, outContent.toString());
    }

}
