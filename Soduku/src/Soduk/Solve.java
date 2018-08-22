package Soduk;


public class Solve extends Thread{
	public static boolean check;
	public static boolean notdone;
	public static int[] grid;
	public static int D;

public  boolean valid(int[] grid , int depth) {
	//Check rows
	int row = depth/9;
	for(int i =row*9 ; i < row*9+9;i++)
		if(grid[i] == grid[depth] &&  i != depth)
			return false;
	
	
	//Check Columns
	int col = depth %9;
	for(int k = col ; k < 81 ; k=k+9)
		if(grid[k] == grid[depth] && depth != k)
			return false;
	
	
	int Row = row/3;
	int Col = col/3;
	for(int r = Row*3 ; r < Row*3+3 ; r++)
		for(int c = Col*3 ; c < Col*3+3 ;c++)
			if(depth != r*9 + c && grid[depth] == grid[r*9+c])
				return false;
	return true;
}
	
public  boolean run1(int []grid , int depth) {
	D=depth+1;
	if(depth ==80) {
		notdone = false;
		for(int i = 0 ; i < 9 ; i++) {
			for(int j = 0 ; j < 9 ; j++)
				System.out.print(grid[i*9+j]);
			System.out.println(" ");
			
		}
		return true;
	}
	else {
		if(grid[depth+1] != 0){
			if(run1(grid,depth+1))
				return true;
		}
		else
		{
			for(int i = 1 ; i <= 9 ; i++) {
				grid[depth+1]=i;
				

				try {
					synchronized(this) {
					this.wait(1);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				if(valid(grid,depth+1) == true){
					if(run1(grid ,depth+1))
						return true;
				}
				
				grid[depth+1]=0;
				}
		}
	}
	return false;
}

public Solve(int[] G ) {
	grid = new int[81];
	grid = G;
	notdone = true;
}
public void run() {
	notdone = true;
	
	run1(grid,-1);
}
}
