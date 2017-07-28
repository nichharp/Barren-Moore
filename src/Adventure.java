import java.io.IOException;
//import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Adventure {

	//	static ArrayList<ArrayList<Integer>> Movements = new ArrayList<>();
	
//	static YourPosition p1 = new YourPosition(0,0);
	
//	static int moves;
//	int [][] Map2 = new int[10][10];
	
	public static void main(String[] args) {
		Adventure a = new Adventure();
		
		a.startAdventure();
//		a.checkPosition();
	}

	public void startAdventure() {
		
		System.out.println("Welcome to The Adventure of Barren Moore");
		System.out.println("He has poisoned you. You have 15 moves");
		System.out.println("1 Goal...");
		System.out.println("Survive");
		int [][] Map = makeMap(10);
		int [][] Move = makeMovement();
		int numOfMoves = 0;
		checkPosition(Map);
		for (int i = 0; i<15;i++) {
			askForMovement(Map, Move, numOfMoves);
			whatEncounter(Map,Move, numOfMoves);
			numOfMoves++;
		}
	}
	
	public int[][] askForMovement(int[][] Map, int [][] Move, int numOfMoves) {
		System.out.println("Where do you want to move?");
		int i = 0;
		do {
			try{
				String scan = InputClass.sc.nextLine();
				
				if (scan.equals("North")){
					moveNorth(Map, Move, numOfMoves);
					i++;
				} else if (scan.equals("South")) {
					moveSouth(Map, Move, numOfMoves);
					i++;
				} else if (scan.equals("East")) {
					moveEast(Map, Move, numOfMoves);
					i++;
				} else if (scan.equals("West")) {
					moveSouth(Map, Move, numOfMoves);
					i++;
				} 
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Where do you want to move?");
				}
		} while (i==0);	
		
		return Move;
	}
	
	public void whatEncounter(int[][] Map, int[][] Move, int numOfMoves){
		int x = Move[numOfMoves][0];
		int y = Move[numOfMoves][1];
		int z = Map [y][x];
		int hasKnife = 0;
		if (z==0){
			System.out.println("There is nothing new around you. Just more dark trees that look like they could snap. You need to keep moving");
		} else if (z==2) {
			System.out.println("Ahead of you is a Goblin");
			battleEncounter("Goblin");
		} else if (z == 8) {
			System.out.println("You find him. He's been waiting. He's got the cure in his hands");
			System.out.println("What do you do?");
			//give option to fight or run
			battleEncounter("He", 0);
		}
	}
	
	public void battleEncounter(String opponent){
		Random rand = new Random();
		int random = rand.nextInt(1);
		if (random == 1) {
			System.out.println("The " + opponent +" has seen you");
		} else {
			System.out.println("The " + opponent +" hasn't seen you");
		} 
	}

	public void battleEncounter(String opponent, int him){
		Random rand = new Random();
		int random = rand.nextInt(1);
		if (random == 1) {
			System.out.println(opponent +" has seen you");
		} else {
			System.out.println(opponent +" hasn't seen you");
		} 
	}
	
	public void checkPosition(int[][] Map) {
//		int[][] Map = makeMap(10);
		for (int i =0; i<10 ; i++) {
			for (int j = 0 ; j< 10;j++){
				System.out.print(Map[i][j]);

			} System.out.println("");
		}
	}
	
	public int[][] makeMap(int x) {
		int[][] Map = new int [x][x];
//		ArrayList<ArrayList<String>> Map = new ArrayList<>();
		for (int i=0; i<10;i++){
			for (int j = 0; j<10;j++){
				Map[i][j] = 0;
			}
		}
		
		Map [4][4] =9;
		Map [9][9]= 8;
		Map [2][4] = 2;
		Map [6][4] =2;
		return Map;
	}
	
	public int[][] makeMovement() {
		int[][] Move = new int [15][2];
		Move[0][0] = Move[0][1] = 4;
		return Move;
	}
	
	public int[][] moveNorth(int[][] Map, int [][] Move, int numOfMoves) {
		int x = Move [numOfMoves][0] ;
		int y = Move [numOfMoves][1];
		System.out.println("You are in position: " + x + " & " + y);
		return addPosition(x,y-1, Move, numOfMoves);
	}public int[][] moveSouth(int[][] Map, int [][] Move, int numOfMoves) {
		int x = Move [numOfMoves][0] ;
		int y = Move [numOfMoves][1];
		System.out.println("You are in position: " + x + " & " + y);
		return addPosition(x,y+1, Move, numOfMoves);
		
	}public int[][] moveEast(int[][] Map, int [][] Move, int numOfMoves) {
		int x = Move [numOfMoves][0] ;
		int y = Move [numOfMoves][1];
		System.out.println("You are in position: " + x + " & " + y);
		return addPosition(x+1,y, Move, numOfMoves);
		
	}public int[][] moveWest(int[][] Map, int [][] Move, int numOfMoves) {
		int x = Move [numOfMoves][0] ;
		int y = Move [numOfMoves][1];
		System.out.println("You are in position: " + x + " & " + y);
		return 	addPosition(x-1,y, Move, numOfMoves);

	}
	public int[][] addPosition(int x, int y, int [][] Move, int numOfMoves) {
		Move [numOfMoves+1][0] = x;
		Move [numOfMoves+1][1] = y;
		System.out.println("You are now in position: " + x + " & " + y);
		for (int i =0; i<10 ; i++) {
			for (int j = 0 ; j< 2;j++){
				System.out.print(Move[i][j]);

			} System.out.println("");
		}
		return Move;
	}
	

	
}

