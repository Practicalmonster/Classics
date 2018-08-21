package cop;

public class Solve extends Thread{
	public static int size;
	public static int board[];
	public static boolean iter;
	public static boolean notdone;
	public static int D;
	public static boolean sol;
	
	public Solve(int Size){
		size =Size;
		board = new int[Size];
		iter = true;
		notdone=true;
		D=0;
		sol = false;
	}
	
	public boolean valid(int[] board , int depth, boolean triger) {
		iter = false;
		while(true) {
			if(iter==false && triger == true) {
				try {
					synchronized(this)
					{
						D= depth;
						this.wait(100);
						continue;
					}
				}catch(InterruptedException ignore) {
					System.out.println("Eh da tayeb");
				}
			}
			break;
		}
		if (depth == 0)
			return true;
		for(int i = 0 ; i <= depth-1 ;i++)
		{
			if(board[i] == board[depth]) //check rows
				return false;
			if(i+board[i] == depth + board[depth]) //check diagonal
				return false;
			if(i-board[i]== depth - board[depth]) //check reversed diagonal
				return false;
		}
		return true;
	}

	public boolean solve(int[] board ,int depth , int n) {
		
		if(depth == n-1)
			{
			for(int i = 0; i < n ; i++)
				System.out.print(board[i]+ " ");
			System.out.println(" ");
			return true;
			}
		else {
			for(int i = 0 ; i < n ; i++)
				{
				board[depth+1]=i;
				if(depth+1 == n-1)
					if(valid(board, depth+1 , false) == true)
						sol = true;
				if(valid(board, depth+1, true) == true)
					{
					solve(board, depth+1 , n);
					}
				}
		}
		return false;
	}
	public void run() {
		solve(board , -1 , size);
		notdone=false;
	}
}



