/*
 * FileReader.java
 * 
 * Juan Recinos                                               jrecinos@rollins.edu
 * CMS495.H1X Spring 2015
 * Dr. Anderson
 * 26 March 2015
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Used to read through a given file and create an Example_Class arrayList of
 * the csv lines to be returned from a ReadCsv
 */
public class FileReader {

	public FileReader() {

	}// end default constructor

	public FileReader(String filename) {

	}

	/**
	 * Read the CSV file and creates an Examples object for each line of the
	 * file and puts it into an ArrayList.
	 * 
	 * @param filename
	 *            - a string of the CSV file path to be used
	 * @return an ArrayList of each line as an Example_Class object
	 * @throws IOException
	 *             - thrown if file path does not exist
	 */
	public Examples createExamples(String filename) {
		Examples examples = new Examples();
		try {
			String line;
			BufferedReader file = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename)));
			line = file.readLine(); // Skip over header line
			line = line.replace("Example,", "");
			line = line.replace(",Target", "");

			String[] attributeNames = line.split(",");

			while ((line = file.readLine()) != null) {
				String[] aLine = line.split(",");
				String[] attributeValues = new String[aLine.length - 2];
				for (int i = 1; i < aLine.length - 1; i++) {
					attributeValues[i - 1] = aLine[i];
				}
				boolean wait;
				if (aLine[11].equalsIgnoreCase("T")) {
					wait = true;
				} else {
					wait = false;
				}
				examples.add(aLine[0].toString(), attributeNames,
						attributeValues, wait);
			}
			file.close();
		} catch (IOException ex) {
			System.out.println("File Not Found.");
			System.exit(0);
		}
		return examples;
	}
}// end class
