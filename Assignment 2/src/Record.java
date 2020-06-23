//Ian Borwick - 250950449
//Assignment 2
//Oct 2019

public class Record {
	//Declare variables 
	String config;
	int score;
	
	// Method returns a new Record with the specified config and score
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
	};
	
	//Method returns the config string of the new record
	public String getConfig() {
		return(config);
	}

	//Method returns the score of the new record
	public int getScore() {
		return (score);
	}

}
