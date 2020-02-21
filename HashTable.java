/*
 * This class uses the node class given in the instructions to
 * initialize a node by given name and GDP per capita. It has 
 * the required methods for the double ended linked list that are
 * implemented in the Hash Table. The required methods for a hash
 * table are present as well. 
 * 
 * @author <Jeremiah Baclig>
 * @version <12/5/2019>
 */

public class HashTable {

	private class Node {
		// initialize local variables
		String name;
		double gdpPerCapita;
		Node nextNode;
		
		/*
		 * Constructor of Node class. Sets node by given parameters.
		 * 
		 * @param name	name of given country.
		 * @param gdpPerCapita	GDP Per Capita of the given country.
		 */
		public Node(String name, double gdpPerCapita) {
			this.name = name;
			this.gdpPerCapita = gdpPerCapita;
		}
		
		/*
		 * Method to print the node using printf.
		 */
		public void printNode() {
			System.out.printf("%-25s%,-20.2f\n", name, gdpPerCapita);
		}
		
		/*
		 * Getter for country name.
		 * 
		 * @return name value
		 */
		String getName() {
			return name;
		}
	}
	
	private class LinkedList {
		private Node first; 
		private Node last;
		
		/*
		 * No-arg constructor for double-ended list
		 */
		public LinkedList() {
			first = null;
			last = null;
		}
		
		/*
		 * Confirms if the list is empty.
		 * 
		 * @return first is empty
		 */
		public boolean isEmpty() { 
			return first==null; 
		}
		
		/*
		 * Inserts into the end of the double-ended list.
		 * 
		 * @param newNode passes in this node to insert
		 */
		public void insertLast(Node newNode) {
			
			if(isEmpty()) {
				first = newNode; 
			}
			else {
				last.nextNode = newNode; 
			}
			last = newNode;
			
		}
		
		/*
		 * Searches for a node with given country name.
		 * 
		 * @param name of country to search for
		 * @return current node
		 */
		public Node findNode(String name) {
			Node current = first; 
		
			try {
				while(!current.name.equals(name)) {
					if(current.nextNode == null) {
						return null;
					}
					else {
						current = current.nextNode; 
					}
				} 
			} catch(NullPointerException e) {
				System.out.print(name + " is not found");
					
				}
			
			return current; 
		}

		/*
		 * Finds and deletes a node.
		 * 
		 * @param
		 * @return current 
		 */
		public Node deleteNode(Node key) {
			Node current = first; 
			Node previous = first;
			
			while(current != key) {
				if(current.nextNode == null) {
					return null; 
				}
				else {
					previous = current;
					current = current.nextNode;
				}
			} 
			if(current == first) {
				first = first.nextNode;
			}
			else {
				previous.nextNode = current.nextNode; 
			}
			return current;
		}
		
		/*
		 * Prints out the current node and iterates through the list.
		 */
		public void displayList() {
			Node current = first; 
			
			for(int i=0; i<arraySize; i++) {
				if(current != null) {
					current.printNode(); // print data
					current = current.nextNode;
				}
			}
			System.out.println();
		}
	}

	private LinkedList[] hashArray; 
	private int arraySize;

	/*
	 * No-arg constructor that creates an empty hash table.
	 */
	public HashTable() {
		arraySize = 311;
		hashArray = new LinkedList[arraySize];
		
		for(int i=0; i<arraySize; i++) {
			hashArray[i] = new LinkedList();
		}
	}
 
	/*
	 * Traverses the table and prints the contents.
	 */
	public void display() {
		System.out.println("Hash table content:\n");
		for(int i=0; i<arraySize; i++) {
			System.out.print((i+1) + ". ");
			hashArray[i].displayList();
		}
	}
	
	/*
	 * Computes hash value based on the node's name.
	 * 
	 * @param theNode is the node to be hashed.
	 * @return keyTotal % arraySize to receive the hash value.
	 */
	public int count = 0;
	double arry[] = new double[155];
	public int hashFunc(Node theNode) {
		String name = theNode.getName();
	
		int key = 0;
		int keyTotal = 0;
		
		for(int i=0; i<name.length(); i++) {
			key = name.charAt(i);
			keyTotal += key;
		}
		
		arry[count] = keyTotal;	
		
		return keyTotal % arraySize;
	}
	
	/*
	 * Passes in a country based on the name and gdpPerCapita. Creates a node using
	 * those parameters and based on the hash value, inserts it in place.
	 * 
	 * @param country country's name
	 * @param gdpPerCapita double gdpPerCapita value of the passed in country
	 */
	public void insert(String country, double gdpPerCapita) {
		Node theNode = new Node(country, gdpPerCapita);
		int hashVal = hashFunc(theNode);
		
		hashArray[hashVal].insertLast(theNode);
		count++;
	} 
	
	/*
	 * Deletes a country based on name. Searches for it and finds the hashed location
	 * to delete it from the linked list.
	 * 
	 * @param country country's name
	 */
	public void delete(String country) {
		int key = 0; 
		int keyTotal = 0;
		
		for(int i=0; i<country.length(); i++) {
			key = country.charAt(i);
			keyTotal += key;
		}
		
		int hashVal = keyTotal % arraySize;
		
		hashArray[hashVal].deleteNode(hashArray[hashVal].findNode(country));
		
		System.out.println(country + " has been deleted from hash table");
	} 
	
	/*
	 * Finds a country based on name and prints if found the GDP Per Capita, if not, then 
	 * not found.
	 * 
	 * @param country country's name
	 * @return node's name
	 */
	public double find(String country) {
		int key = 0; 
		int keyTotal = 0;
		
		for(int i=0; i<country.length(); i++) {
			key = country.charAt(i);
			keyTotal += key;
		}
		
		int hashVal = keyTotal % arraySize;
		if(hashArray[hashVal].findNode(country) == null) {
			// handled in findNode method
			return 0;
		}
		else {
			System.out.print(country + " is found with GDP per capita of ");
			System.out.printf("%.2f", hashArray[hashVal].findNode(country).gdpPerCapita);
			return 0;
		}
	}
	
	/*
	 * Calls the sort method to bubble sort the array with the hash values. 
	 * It then prints the number of empty spaces and collisions.
	 */
	public void printFreeAndCollisions() {
		int x = 0, y = 0;
		
		for(int i=0; i<arraySize; i++) {
			if(hashArray[i].isEmpty()) {
				++x;
			}
		}
		sortAndCollisions();
		y = dupCount;
		
		System.out.println("Hash table has " + x + " empty spaces and " + y + " collisions");
		
	}
	
	/*
	 * Sorts the array with the hash values by using bubble sort. This is so 
	 * we can search through the array and then find values with the same
	 * hash value to see if there are collisions.
	 */
	int dupCount;
	public void sortAndCollisions() {
		int in, out, dupl = 0;
		int prev = -1;
		
		for(out=1; out<arry.length; out++) {
			double temp = arry[out]; 
			in = out; 
			while(in>0 && arry[in-1] >= temp) {
				arry[in] = arry[in-1]; 
				--in; 
			}
		arry[in] = temp; 
		} 
		
		
		for (int i=0; i<arry.length; ++i) {
		    if (arry[i] == prev) {
		        ++dupl;
		        if (dupl == 1) {
		            ++dupCount;
		        }
		    }
		    else {
		        prev = (int)arry[i];
		        dupl = 0;
		    }
		}
	}
	
}