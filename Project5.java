/**
 * COP 3538: Project 5 - Hash Tables
 * <p>
 * This class reads in a .csv file of a list of countries. There are different
 * parameters within the list. The class takes these, and inserts them into
 * a hash table based on the hash function.
 * <p>
 * The program does some deletion and finding that displays the node as well as
 * the hash table as a whole.
 * <p>
 * For organization there are some print methods, and a method to read the file
 * while inserting it into the hash table using the insert method in the HashTable class.
 * 
 * @author <Jeremiah Baclig>
 * @version <12/5/2019>
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Project5 {

	// create hash table using constructor
	private static HashTable hTable = new HashTable();
	
	/*  
	 *  Method that reads in the file parameters using a buffered reader.
	 *  It reads the first line and then after, inserts the following name and GDP per capita
	 *  into the hash table using the insert method.
	 */
	private static void readFile() throws NumberFormatException, IOException {
		Scanner fileScan = new Scanner(System.in);
		String fileName;
		System.out.println("COP 3990 Project 5\n\nHash Tables\nEnter the file:");
		fileName = fileScan.next();
		
		System.out.println("\nThere were 155 country records read into the hash table.\n");

		File file = new File(fileName);
		fileScan.close();
		
		// Creates a way to read in the file while not storing the first line.
		BufferedReader reader = new BufferedReader(new FileReader(file));
		reader.readLine();

		String line;
		
		while ((line = reader.readLine()) != null) {

			String read[] = line.split("[,\n]");
			String name = read[0];
			Long population = Long.parseLong(read[3]);
			Double GDP = Double.parseDouble(read[4]);

			double gdpPerCapita = (GDP / population);
		
			hTable.insert(name, gdpPerCapita);
		}
		
		reader.close();
	}
	
	/*
	 *  Main method of the class. Reads in the file using the readFile() method.
	 *  It then prints the table using the display() method.
	 *  <p>
	 *  Main continues to delete, display, and find the countries by name. Finally,
	 *  it calls a method to print the number of empty cells and the number of cells with collisions.
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		readFile();
		hTable.display();
		System.out.println();
		
		hTable.delete("Australia");
		hTable.delete("Tunisia");
		hTable.delete("Norway");
		System.out.println();
		
		hTable.find("Sri Lanka");
		System.out.println();
		hTable.find("North Cyprus");
		System.out.println();
		hTable.find("Tunisia");
		System.out.println();
		
		System.out.println();
		hTable.delete("Malawi");
		hTable.delete("Germany");
		hTable.delete("Ireland");
		hTable.delete("Greece");
		hTable.delete("Bolivia");
		System.out.println();
		
		hTable.display();
		System.out.println();
		
		hTable.printFreeAndCollisions();
	}
}
