import java.util.LinkedList;

//Ian Borwick - 250950449
//Assignment 2
//Oct 2019

public class Dictionary implements DictionaryADT {
	
    // Declare variables.
    private Record pair;
    private String config;
    private int size;
    private int count = 0;
    private LinkedList<Record> [] hTable;
    
 // Constructor method returns an empty dictionary of the specified size.
    public Dictionary(int size) {
        LinkedList<Record> [] hTable = new LinkedList [size];
        this.size = size;
        this.hTable = hTable;
    }
    
	//Method inserts a pair into the dictionary if it is not already there
	public int insert(Record pair) throws DictionaryException{
		//checks to see if the config is not in the dictionary already
		if (get(pair.getConfig())==-1) {
			int hashIndex = hash(pair.getConfig());
			//checks to see if the hashtable is not empty
			if (hTable[hashIndex] == null) {

				//increases the count,creates a new linked list the adds the pair and returns 0 to show no collisions 
				count++;
				hTable[hashIndex] = new LinkedList<Record>();
				hTable[hashIndex].add(pair);
				return 0; 
			}
			else {
				//increases count, adds the pair to the hashtable and returns 1 to show collisions
				count++;
				hTable[hashIndex].add(pair);
				return 1;	
			}
		}
		//throws new exception if the config is already present
		else {
			throw new DictionaryException();
		}
	}
	//removes the entry with the given config from the dictionary
	public void remove(String config) throws DictionaryException{
		//checks if the key is not empty
		if (get(config) != -1) {
			//Creates position array with 2 elements
			int [] position = new int [2];
			//loops through the length of the hash table
			for (int i=0; i<hTable.length; i++ ) {
				//checks if the element of the hashtable is not empty
				if (hTable[i]!= null) {
					//loop through size of the hashtable
					for (int j = 0; j<hTable[i].size(); j++) {
						//checks if the config is equal to the value stored in the hash table
						if(config == hTable[i].get(j).getConfig()) {
							position[0]= i;
							position[1] = j;
							// removes config and decreases count	
							hTable[position[0]].remove(position[1]);
							count--;
						}
					}
				}
				
			}
			
		}
		else {
			//throws exception if the config is not within the dictionary
			throw new DictionaryException();
		}
	}
	
	//Method returns the score stored in the dictionary or -1 if it is not it the dictionary
	public int get(String config) {
		//loops for the hashtable length
		for (int i=0; i< hTable.length; i++) {
			//checks if the element is not empty
			if (hTable[i]!= null) {
				//loop for the size of hashtables size
				for (int j=0; j < hTable[i].size(); j++) {
					//checks if the config is equal to the value in the hashtable
					if (config.equals(hTable[i].get(j).getConfig())) {
						//returns the score of the pair if found 
						return hTable[i].get(j).getScore()%size;
					}
				}
			}
		}
		//returns -1 if config is not in the hashtable 
		return (-1);
	}
	//Method returns the number of Record objects stored in the dictionary
	public int numElements() {
		return count;
		
	}
	//Method that take in a string and 
    private int hash(String config) {
        //establishes variables 
        int hashVal = 0;
        final int prime = 11;
         
        //determines the value of hash value and reassigned it 
        for (int i = config.length() - 1; i >= 0; i--) {
            hashVal = (hashVal * prime + config.charAt(i)) % size;
        }
        //System.out.println(hashVal);
        return hashVal;
 
    }

}