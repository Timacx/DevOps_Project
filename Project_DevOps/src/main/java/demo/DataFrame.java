package demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DataFrame
{
    public ArrayList<Object> dataFrame;

    // Constructeur à partir d'un tableau
    DataFrame( ArrayList<Object[]> strucList ){

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



    public void displayValues(){
        for(Object data : this.dataFrame){
            System.out.print(((Collumn<?>)data).getLabel() + " : ");
            System.out.println(((Collumn<?>)data).getValues());
        }
    }


    public static void main( String[] args )
    {
        DataFrame df = new DataFrame("file.csv");
        df.displayValues();
        System.out.println("DevOps Project");
    }
}
