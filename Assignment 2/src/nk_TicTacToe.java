//Ian Borwick - 250950449
//Assignment 2
//Oct 2019

public class nk_TicTacToe {
	
	//Declare variables
	int board_size;
	int inline;
	int max_levels;
	Dictionary configurations;
	char [] [] gameBoard;
	
	private char COMP= 'O';
	private char HUM = 'X';
	private char BLOCK = 'B';
	private char EMPTY = ' ';
	
	//Constructor that builds the game board and establishes variables to be used
	public nk_TicTacToe (int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;	
		this.gameBoard = new char [board_size][board_size];
		
		//Fills game board with empty spaces 
		for (int i = 0; i<board_size; i++) {
			for (int j = 0; j<board_size; j++) {
				gameBoard[i][j]=' ';
			}
		}
	}
	// Method creates a dictionary of size = board_size 
	public Dictionary createDictionary() {
	    Dictionary dict = new Dictionary(7993);
		return dict;
	}
	
	//Method checks if the string representing the gameboard is in the dictionary or not
	public int repeatedConfig(Dictionary configurations) {
		String config = "";
		int res;
		//iterates through the game board
		for (int i = 0; i<board_size; i++) {
			for (int j = 0; j<board_size; j++) {
				//checks to see whose turn it is and places the appropriate symbol
				if (gameBoard[i][j]== COMP) {
					config+='O';
				}
				if (gameBoard[i][j]== HUM) {
					config+='X';
				}
				if (gameBoard[i][j]== BLOCK) {
					config+='B';
				}
				else {
					config +=' ';
				}
			}
		}
		// checks if the config exists in dictionary
		//System.out.println(config);
		if (configurations.get(config) != -1) {
			//returns score if it is in the dictionary
			res = configurations.get(config);
		}
		else {
			//returns -1 if it is not in the dictionary
			res=-1;
		}
		return res;
	}
	//Method stores the string representing the game board with in the dictionary
	public void insertConfig(Dictionary configurations, int score) {
		String config="";
		Record pair; 

		//iterates through the game board
		for (int i = 0; i<board_size; i++) {
			for (int j = 0; j<board_size; j++) {
				//checks to see whose turn it is and places the appropriate symbol
				if (gameBoard[i][j]== COMP) {
					config+='O';
				}
				if (gameBoard[i][j]== HUM) {
					config+='X';
				}
				if (gameBoard[i][j]== BLOCK) {
					config+='B';
				}
				else {
					config +=' ';
				}
			}
		}
		//creates a new record with the config and score
		pair= new Record(config,score);
		//adds the pair to the configurations dictionary 
		configurations.insert(pair);
	}
	//Stores a symbol in the gameboard at a given position 
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col]=symbol;
	}
	
	//Checks if a square at a given position is empty
	public boolean squareIsEmpty (int row, int col) {
		if (gameBoard[row][col]== ' ') {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean wins (char symbol) {
		//declare variables
		int count = 0;
		int mi = 0; // vertical
		int mj = 0; // horzontal
		
		//iterates through the board
		for (int i=0; i<board_size; i++) {
			for(int j = 0; j<board_size; j++) {
				mi = 0;
				mj=0;
				count=0;
				
				//checks the right side
				boolean boolR = false;
				while(boolR== false) {
					if ((i+mi)>=board_size) {
						break;
					}
					if(gameBoard[i+mi][j]==symbol) {
						count++;
						mi++;
						if(count == inline) {
							return true;
						}
					}
					else {
						break;
					}
					
				}
				
				count = 0;
				//checks downward
				boolean boolD = false;
				while (boolD==false) {
					if ((j+mj)>=board_size) {
						break;
					}
					if(gameBoard[i][mj+j]==symbol) {
						count++;
						mj++;
						if(count == inline) {
							return true;
						}
					}
					else {
						break;
					}
				}
				
				count= 0;
				mi = 0;
				mj=0;
				//check diagonal right-down
				boolean boolDDR = false;
				while (boolDDR==false) {
					if ((j+mj)>=board_size) {
						break;
					}
					if ((i+mi)>=board_size) {
						break;
					}
					if(gameBoard[i+mi][mj+j]==symbol) {
						count++;
						mi++;
						mj++;
						if(count == inline) {
							return true;
						}
					}
					else {
						break;
					}
				}
				count= 0;
				mi = 0;
				mj=0;
				//check diagonal left-down
				boolean boolDDL = false;
				while (boolDDL==false) {
					if ((j-mj)<0) {
						break;
					}
					if ((i+mi)>=board_size) {
						break;
					}
					if(gameBoard[i+mi][j-mj]==symbol) {
						count++;
						mi++;
						mj++;
						if(count == inline) {
							return true;
						}
					}
					else {
						break;
					}
				}
				
			}
					
		}
		return false;
	}
	
	//method checks if there are no empty spaces left and no winner
	public boolean isDraw() {
		//iterates through the gameboard
		for (int i = 0; i <board_size; i++) {
			for (int j = 0; j<board_size; j++) {
				//returns false if there are empty spaces
				if (gameBoard[i][j] == EMPTY) {
					return false;
				}
			}
			//returns false if there is a winner
			if (wins(HUM)| wins(COMP)) {
				return false;
			}
		}
		//returns true if all spaces are filled and there is no winner
		return true;
	}
	
	//Method returns the winner of the game
	public int evalBoard() {
		//returns 3 if Computer won
		if (wins(COMP)) {
			return 3;
		}
		//returns 0 if human won
		if (wins(HUM)) {
			return 0;
		}
		//returns 2 if its a draw
		if (isDraw()) {
			return 2;
		}
		//Returns 1 game is still undecided
		return 1;
	}
	
	public static void main(String[] args) {
		
	}
}
