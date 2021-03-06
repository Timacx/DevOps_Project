package demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    Double[] Prix = { 3.56, 5.78, 10.0, 2.5, 2.5, 15.89, 5.11, 0.99 };
    Double[] Calories = { 200.7, 298.0, 900.7, 98.0, 678.0, 67.9, 987.0, 1999.98 };

    String[] Fruits2 = { "Fraise", "Framboise" };
    Double[] Prix2 = { 10.0, 15.89 };
    Double[] Calories2 = { 900.7, 67.9 };

    String[] label3 = { "Fruits", "Prix/Kg" };
    String[] Fruits3 = { "Pomme", "Poire", "Fraise", "Orange", "Banane", "Framboise", "Peche", "Abricots" };
    Double[] Prix3 = { 3.56, 5.78, 10.0, 2.5, 2.5, 15.89, 5.11, 0.99 };

    String[] Fruits4 = { "Pomme", "Poire", "Orange", "Banane", "Peche", "Abricots" };
    Double[] Prix4 = { 3.56, 5.78, 2.5, 2.5, 5.11, 0.99 };
    Double[] Calories4 = { 200.7, 298.0, 98.0, 678.0, 987.0, 1999.98 };

    String[] Fruits5 = { "Pomme", "Poire", "Fraise", "Orange", "Banane", "Peche", "Abricots" };
    Double[] Prix5 = { 3.56, 5.78, 10.0, 2.5, 2.5, 5.11, 0.99 };
    Double[] Calories5 = { 200.7, 298.0, 900.7, 98.0, 678.0, 987.0, 1999.98 };

    String[] Fruits6 = {"Framboise"};
    Double[] Prix6 = {15.89};
    Double[] Calories6 = {67.9};

    String[] Fruits7 = {"Pomme"};
    Double[] Prix7 = {3.56};
    Double[] Calories7 = {200.7};

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
    public void testEqualsCSV() {

        DataFrame dataFrame = new DataFrame("file.csv");
        DataFrame dataFrame2 = new DataFrame("file.csv");

        assertTrue(dataFrame.equals(dataFrame2));
    }

    @Test
    public void testNotEquals() {

        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label);
        data.add(Fruits);
        data.add(Prix);
        DataFrame dataFrame = new DataFrame(data, false);
        
        data.add(Calories);
        DataFrame dataFrame2 = new DataFrame(data, false);

        assertFalse(dataFrame.equals(dataFrame2));
    }

    @Test
    public void testNotEquals2() {

        ArrayList<Object> data = new ArrayList<Object>();
        ArrayList<Object> data2 = new ArrayList<Object>();


        data.add(label);
        data.add(Fruits);
        data.add(Prix);

        data2.add(label);
        data2.add(Fruits);
        data2.add(Calories);


        DataFrame dataFrame = new DataFrame(data, false);
        DataFrame dataFrame2 = new DataFrame(data2, false);

        assertFalse(dataFrame.equals(dataFrame2));
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
    public void testDataFromBooleanIndexing2() {
        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label);
        data.add(Fruits);
        data.add(Prix);
        data.add(Calories);

        DataFrame dataFrame = new DataFrame(data, false);

        DataFrame newDataFrame = dataFrame.getDataFrameFromBooleanIndexing("Prix/Kg", "<=", 10.0);

        ArrayList<Object> data5 = new ArrayList<Object>();
        data5.add(label);
        data5.add(Fruits5);
        data5.add(Prix5);
        data5.add(Calories5);

        assertTrue(newDataFrame.equals(new DataFrame(data5, false)));
    }
    
    @Test
    public void testDataFromBooleanIndexing3() {
        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label);
        data.add(Fruits);
        data.add(Prix);
        data.add(Calories);

        DataFrame dataFrame = new DataFrame(data, false);

        DataFrame newDataFrame = dataFrame.getDataFrameFromBooleanIndexing("Prix/Kg", ">", 15.0);

        ArrayList<Object> data6 = new ArrayList<Object>();
        data6.add(label);
        data6.add(Fruits6);
        data6.add(Prix6);
        data6.add(Calories6);

        assertTrue(newDataFrame.equals(new DataFrame(data6, false)));
    }

    @Test
    public void testDataFromBooleanIndexing4() {
        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label);
        data.add(Fruits);
        data.add(Prix);
        data.add(Calories);

        DataFrame dataFrame = new DataFrame(data, false);

        DataFrame newDataFrame = dataFrame.getDataFrameFromBooleanIndexing("Prix/Kg", ">=", 15.89);

        ArrayList<Object> data7 = new ArrayList<Object>();
        data7.add(label);
        data7.add(Fruits6);
        data7.add(Prix6);
        data7.add(Calories6);

        assertTrue(newDataFrame.equals(new DataFrame(data7, false)));
    }

    @Test
    public void testDataFromBooleanIndexing5() {
        ArrayList<Object> data = new ArrayList<Object>();

        data.add(label);
        data.add(Fruits);
        data.add(Prix);
        data.add(Calories);

        DataFrame dataFrame = new DataFrame(data, false);

        DataFrame newDataFrame = dataFrame.getDataFrameFromBooleanIndexing("Calories", "=", 200.7);

        ArrayList<Object> data7 = new ArrayList<Object>();
        data7.add(label);
        data7.add(Fruits7);
        data7.add(Prix7);
        data7.add(Calories7);

        assertTrue(newDataFrame.equals(new DataFrame(data7, false)));
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

    @Test
    public void testStatAverage(){
        DataFrame dataFrame = new DataFrame("file.csv");

        try {
            assertTrue(dataFrame.average("Age")==8.27);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void testStatAverageException(){
        DataFrame dataFrame = new DataFrame("file.csv");

        assertThrows(Exception.class, ()->{
            dataFrame.average("");
        });

        assertThrows(Exception.class, ()->{
            dataFrame.average("Couleur");
        });
    }

    @Test
    public void testStatMin(){
        DataFrame dataFrame = new DataFrame("file.csv");

        try {
            assertTrue((Double)dataFrame.minVal("Age")==1.6);
            assertTrue(((String)dataFrame.minVal("Couleur")).equals("Blanc"));
            assertTrue(((String)dataFrame.minVal("Prenom")).equals("Chocolat"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testStatMinException(){
        DataFrame dataFrame = new DataFrame("file.csv");

        assertThrows(Exception.class, ()->{
            dataFrame.minVal("");
        });
    }

    @Test
    public void testStatMax(){
        DataFrame dataFrame = new DataFrame("file.csv");

        try {
            assertTrue((Double)dataFrame.maxVal("Age")==17.7);
            assertTrue(((String)dataFrame.maxVal("Couleur")).equals("Roux"));
            assertTrue(((String)dataFrame.maxVal("Prenom")).equals("Vanille"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testStatMaxException(){
        DataFrame dataFrame = new DataFrame("file.csv");

        assertThrows(Exception.class, ()->{
            dataFrame.maxVal("");
        });
    }

}