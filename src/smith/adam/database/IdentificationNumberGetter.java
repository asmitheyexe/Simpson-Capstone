package smith.adam.database;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class IdentificationNumberGetter {

    public static int nextID() throws Exception{
        String filePath = "/storage/currentID.txt";
        FileReader IDFile = new FileReader(filePath);



        Scanner reader = new Scanner(IDFile);
         int id = -1;

        while(reader.hasNextInt()){
            id = reader.nextInt();
        }

        int newID = id++;

        reader.close();
        IDFile.close();

        FileWriter IdFile = new FileWriter(filePath);
        IdFile.write(newID);

        IdFile.close();

        return newID;
    }
}
