package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

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
    DataFrame( ArrayList<Object> strucList, boolean typed ){

        if(typed){
            dataFrame = strucList;
            return;
        }

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

        this.dataFrame = new ArrayList<Object>();

        List<String> AllLine = new ArrayList<String>();

        // récupration du fichier qui porte le nom "fileName"
        final File f = new File("");
        final String completeFileName = f.getAbsolutePath() + File.separator + fileName;
        File file = new File(completeFileName);

        FileReader fr;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            // lecture du fichier ligne par ligne
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                AllLine.add(line);
            }

            br.close();
            fr.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        
        List<String[] > data = new ArrayList<String[] >(AllLine.size());

        String[] type = new String[0];

        // parcours des lignes
        for (int i = 0; i<AllLine.size(); i++) {
            String[] oneData = AllLine.get(i).split(",");
            if(i==0)        
                type = new String[oneData.length];
            
            // parcours des données de la ligne
            for(int j = 0; j<oneData.length; j++){
                // si on parcours les types des colonnes qui ce trouve sur la premiere ligne
                if(i==0){  
                    type[j] = oneData[j];
                    // on crée la collone suivant le typer trouvé
                    switch(oneData[j]){
                        case "Integer":
                            dataFrame.add(new Collumn<Integer>(new ArrayList<Integer>()));
                            break;
                        case "String":
                            dataFrame.add(new Collumn<String>(new ArrayList<String>()));
                            break;
                        case "Double":
                            dataFrame.add(new Collumn<Double>(new ArrayList<Double>()));
                            break;
                    }
                }
                // on lit les labels
                else if(i==1){
                    ((Collumn<?>) dataFrame.get(j)).setLabel(oneData[j]);
                }
                // on lit les données que l'on va enregistrer
                else{
                    // on regarde le type de la collone
                    switch(type[j]){
                        case "Integer":
                            ((Collumn<Integer>) dataFrame.get(j)).addValue(Integer.parseInt(oneData[j]));
                            break;
                        case "String":
                            ((Collumn<String>) dataFrame.get(j)).addValue(oneData[j]);
                            break;
                        case "Double":
                            ((Collumn<Double>) dataFrame.get(j)).addValue(Double.parseDouble(oneData[j]));
                            break;
                    }
                }
            }
        }
        
    }

    public void displayAll(){
        int nb_ligne = ((Collumn<?>) dataFrame.get(0)).getValues().size();
        int nb_col = dataFrame.size();

        for(int i = 0; i < nb_col; i++)
            System.out.printf("%-15s",((Collumn<?>) dataFrame.get(i)).getLabel());
        System.out.println();

        for(int i = 0; i < nb_ligne; i++){
            for (int j = 0; j < nb_col; j++)
                System.out.printf("%-15s", ((Collumn<?>) dataFrame.get(j)).getValues().get(i) );                            
            System.out.println();
        }
    }

    public DataFrame getDataFrameFromIndex(int... indexs){
        ArrayList<Object> newDataFrame = dataFrame;
        
        
        for (int i = 0; i < newDataFrame.size(); i++)
            ((Collumn<?>) newDataFrame.get(i)).leaveOnly(indexs);
                
        return new DataFrame(newDataFrame, true);
    }

    public DataFrame getDataFrameFromCollumns(String... labels){
        ArrayList<Object> newDataFrame = new ArrayList<Object>();
        
        for (String label : labels) 
            for (int i = 0; i < dataFrame.size(); i++) 
                if(((Collumn<?>) dataFrame.get(i)).getLabel().equals(label)){
                    newDataFrame.add(dataFrame.get(i));
                }
                

        return new DataFrame(newDataFrame, true);
    }


    public static void main( String[] args )
    {
        // DataFrame df = new DataFrame("file.csv");
  
        ArrayList<Object> data = new ArrayList<Object>();
        String[] label = {"Manger", "Bouger", "Dormir"};
        String[] manger = {"Pomme", "Poire", "Fraise"};
        Integer[] bouger = {9,89,999};
        Integer[] dormir = {456,788,0};

        data.add(label);
        data.add(manger);
        data.add(bouger);
        data.add(dormir);

        DataFrame dataFrame = new DataFrame(data, false);
        dataFrame.displayAll();

        System.out.println();

        DataFrame dataFrame2 = dataFrame.getDataFrameFromCollumns("Manger", "Dormir");
        dataFrame2.displayAll();

        System.out.println();

        DataFrame dataFrame3 = dataFrame2.getDataFrameFromIndex(1, 0);
        dataFrame3.displayAll();

    }
}
