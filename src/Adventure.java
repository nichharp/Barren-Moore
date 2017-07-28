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
		int health = 10;
		boolean restricted = false;
//		checkPosition(Map);
		for (int i = 0; i<15;i++){
		if (restricted ==false) {
			askForMovement(Map, Move, numOfMoves);
			whatEncounter(Map,Move, numOfMoves, health);
		} else {
			System.out.println(Move[numOfMoves][0] + Move[numOfMoves][1]);
			int x =Move[numOfMoves][0]; int y=Move[numOfMoves][1];
			if (x==0) {
				System.out.println("You cannot move West");
			} else if (x==9) {
				System.out.println("You cannot move East");
			} else if (y == 0) {
				System.out.println("You cannot move North");
			} else if (y == 9) {
				System.out.println("You cannot move South");
			} // restricted askForMovement
			
		}numOfMoves++;
		}
	}
	
	public String compass(int[][] Map, int [][] Move, int numOfMoves){
		double x = Move[numOfMoves][0];
		double y = Move[numOfMoves][1];
		double newx = x-4;
		double newy = y-6;
		String d = "";
		double g = java.lang.Math.sqrt(newx*newx + newy*newy);
		String f = g+"m ";
		if (newx>=0 & newy>=0) {
			double z = java.lang.Math.atan((newx)/(newy));
			d= f+ direction(z);
		} else if (newx<=0 & newy>=0){
			double z = java.lang.Math.atan((newy)/(newx)) +270;
			d= f+direction(z);
		} else if (newx<=0 & newy<=0) {
			double z = java.lang.Math.atan((newx)/(newy)) +180;
			d= f+direction(z);
		} else if (newx>=0 & newy<=0) {
			double z = java.lang.Math.atan((newy)/(newx)) +90;
			d= f+direction(z);
		} return d;
		
	}
	public String direction(double z) {
		String x="";
		if (315<z || z<45){
			x= "North";
		} else if (z> 45 & z<=135) {
			x= "East";
		} else if (z> 135 & z<=225) {
			x= "South";
		} else if (z>225 & z<=315){
			x= "West";
		} return x;
	}
	
	
	public int[][] askForMovement(int[][] Map, int [][] Move, int numOfMoves) {
		System.out.println("Where do you want to move? Or what would you like to do?");
		int i = 0;
		do {
			try{
				String scan = InputClass.sc.nextLine();
				
				if (scan.equals("north")){
					moveNorth(Map, Move, numOfMoves);
					i++;
				} else if (scan.equals("south")) {
					moveSouth(Map, Move, numOfMoves);
					i++;
				} else if (scan.equals("east")) {
					moveEast(Map, Move, numOfMoves);
					i++;
				} else if (scan.equals("west")) {
					moveSouth(Map, Move, numOfMoves);
					i++;
				} else if (scan.equals("compass")) {
					System.out.println("The item you are looking for is " + compass(Map, Move, numOfMoves));
					i++;
				}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Where do you want to move?");
				}
		} while (i==0);	
		
		return Move;
	}
	
	public boolean whatEncounter(int[][] Map, int[][] Move, int numOfMoves, int health){
		int x = Move[numOfMoves][0];
		int y = Move[numOfMoves][1];
		int z = Map [x][y];
		int hasKnife = 0;
		if (z==0){
					System.out.println("There is nothing new around you. Just more dark trees that look like they could snap. You need to keep moving");
		} else if (z==2) {
					System.out.println("Ahead of you is a Goblin");
					battleEncounter("Goblin", hasKnife, health);
		} else if (z == 8) {
					System.out.println("You find him. He's been waiting. He's got the cure in his hands");
					System.out.println("What do you do?");
					//give option to fight or run
					battleEncounter("Man", hasKnife, health);
		} else if (z ==9) {
					System.out.println("Somehow you've found where you woke up. You can see the ditch where it all started");
		} else if (z == 1) {
					System.out.println("You see something on the floor. Pick it up?");
					boolean yn = yesNo();
					if (yn ==true) {
						System.out.println("You find a dull knife");
						hasKnife = 1;
					} else {
						System.out.println("You decide against it. Playing it safe");
					}
		} else if (z ==7) {
			System.out.println("There is a tall, unclimbable fence. Turn back");
			return true;
		} return false;
	}
	
//	public void battleEncounter(String opponent){
//		Random rand = new Random();
//		int random = rand.nextInt(1);
//		if (random == 1) {
//			System.out.println("The " + opponent +" has seen you");
//		} else {
//			System.out.println("The " + opponent +" hasn't seen you");
//		} 
//	}

	public boolean yesNo() {
		int i = 0;
		do {
			try{
				String scan = InputClass.sc.nextLine();
				
				if (scan.equals("yes")){
					return true;
				} else if (scan.equals("no")) {
					return false;
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
		} while (i==0);
		return false;	
	}
	
	public void battleEncounter(String opponent, int hasKnife, int health){
		Random rand = new Random();
		int random = rand.nextInt(1);
		if (random == 1) {
			System.out.println("The " + opponent +" has seen you");
		} else {
			System.out.println("The " + opponent +" hasn't seen you");
			System.out.println("Sneak up on him?");
			boolean yn=yesNo();
			if (yn==true) {
				random = rand.nextInt(1);
				if (random ==1) {
					System.out.println("You successfully sneak up on him");
					System.out.println("Stab him in the back?");
					yn = yesNo();
					if (yn == true) {
						random = rand.nextInt(1);
						if (random ==1) {
							System.out.println("You stab him. Blood pours out of his back. Thicker than most");
							System.out.println("You take the vile he had in his hand");
							System.out.println("Do you drink the vile?");
							yn = yesNo();
							if (yn == true) {
								System.out.println("You chug down the vile. It tastes disgusting.");
								System.out.println("But you know...");
								System.out.println("This is the cure");
								System.out.println("You've won");
								System.exit(0);
							} else {
								
							}
						}
					}
				} else {
					System.out.println("You fail to sneak, he hears you and swings his fist at you");
					System.out.println("What do you do?");
					dodge();
				}
			} else {
				System.out.println("He looks straight at you. He now sees you");
			}
		} 
		
	}
	

	public int dodge(){
		int i = -1;
		do {
			try{
				String scan = InputClass.sc.nextLine();
				
				if (scan.equals("dodge")){
					System.out.println("You dodged!!!");
					i= 0;
				} else {
					System.out.println("You f*ck up");
					i= 5;
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
		} while (i==-1);
		return i;	
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
		Map [4][6]=2;
		Map [4][4]=9;
		Map [8][8]=8;
		Map [2][4]=2;
		Map [6][4]=1;
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
		System.out.println("You were in position: " + x + " & " + y);
		return addPosition(x,y-1, Move, numOfMoves);
	}public int[][] moveSouth(int[][] Map, int [][] Move, int numOfMoves) {
		int x = Move [numOfMoves][0] ;
		int y = Move [numOfMoves][1];
		System.out.println("You were in position: " + x + " & " + y);
		return addPosition(x,y+1, Move, numOfMoves);
		
	}public int[][] moveEast(int[][] Map, int [][] Move, int numOfMoves) {
		int x = Move [numOfMoves][0] ;
		int y = Move [numOfMoves][1];
		System.out.println("You were in position: " + x + " & " + y);
		return addPosition(x+1,y, Move, numOfMoves);
		
	}public int[][] moveWest(int[][] Map, int [][] Move, int numOfMoves) {
		int x = Move [numOfMoves][0] ;
		int y = Move [numOfMoves][1];
		System.out.println("You were in position: " + x + " & " + y);
		return 	addPosition(x-1,y, Move, numOfMoves);

	}
	public int[][] addPosition(int x, int y, int [][] Move, int numOfMoves) {
		Move [numOfMoves+1][0] = x;
		Move [numOfMoves+1][1] = y;
		System.out.println("You are now in position: " + x + " & " + y);
//		for (int i =0; i<10 ; i++) {
//			for (int j = 0 ; j< 2;j++){
//				System.out.print(Move[i][j]);
//
//			} System.out.println("");
//		}
		return Move;
	}
	

	
}

