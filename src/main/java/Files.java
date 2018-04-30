import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Files {

    public Files() {

    }

    public ArrayList<Distribution> readDistributions(String fileName) {
        BufferedReader br = null;
        ArrayList<Distribution> distributions = new ArrayList<Distribution>();
        try {
            br = new BufferedReader(new FileReader(fileName));
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                String [] splits;
                splits = sCurrentLine.split(",");
                String sTime = splits[0];
                String sProbability = splits[1];

                int time = Integer.parseInt(sTime);
                double probability = Double.parseDouble(sProbability);

                distributions.add(new Distribution(time, probability));
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }


        return distributions;
    }

}
