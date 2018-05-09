import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Files Reader
 */
public class FilesReader {

    FilesReader() {}
    /** Read a file that contains the data of the time
     *  distribution between calls or the distribution
     *  of the duration of the call
     * @param fileName file name.
     * @return A ArrayList
     */
    public ArrayList<Tuple> readDistributions(String fileName) {
        BufferedReader br = null;
        ArrayList<Tuple> distributions = new ArrayList<Tuple>();
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

                distributions.add(new Tuple(time, probability));
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
