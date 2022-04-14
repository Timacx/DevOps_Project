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


    public void displayFirstLignes(){
        if(this.dataFrame.size() > 1){
            int size = 3;
            int nbLignes = ((Collumn<?>) this.dataFrame.get(0)).getValues().size();
            int nb_col = dataFrame.size();
            if(nbLignes < 3)
                size = nbLignes;
            
            // affiche les label en haut
            for(int i = 0; i<nb_col; i++)
                System.out.printf("%-15s",((Collumn<?>) dataFrame.get(i)).getLabel());
            System.out.println();
            
            // affiche le contenu des données
            for(int i = 0; i < size; i++){
                for (int j = 0; j < nb_col; j++)
                    System.out.printf("%-15s", ((Collumn<?>) dataFrame.get(j)).getValues().get(i) );                            
                System.out.println();
            }
            
        }
    }


    public void displayLastLignes(){
        if(this.dataFrame.size() > 1){
            int nbLignes = ((Collumn<?>) this.dataFrame.get(0)).getValues().size();
            int size = nbLignes-3;
            int nb_col = dataFrame.size();
            if(nbLignes < 3)
                size = 0;
            
            // affiche les label en haut
            for(int i = 0; i<nb_col; i++)
                System.out.printf("%-15s",((Collumn<?>) dataFrame.get(i)).getLabel());
            System.out.println();
            
            // affiche le contenu des données
            for(int i = size; i < nbLignes; i++){
                for (int j = 0; j < nb_col; j++)
                    System.out.printf("%-15s", ((Collumn<?>) dataFrame.get(j)).getValues().get(i) );                            
                System.out.println();
            }
            
        }
    }


    public static void main( String[] args )
    {
        DataFrame df = new DataFrame("file.csv");
  
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

        df.displayFirstLignes();
        System.out.println();
        df.displayLastLignes();


        System.out.println();
        dataFrame.displayFirstLignes();
        System.out.println();
        dataFrame.displayLastLignes();

    }
}
