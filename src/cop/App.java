package cop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application{
	
	public static int n = 6;
	private static final int HEIGHT = n*60+50;
	public static final int WIDTH = n*60;
	public static final int cellSize = WIDTH/n;
	
	public static int prev = 0;
	public void draw_Queens(ImageView[] Queen,Pane root,int[] board , int depth) {
		for(int i = 0; i <= depth ; i++) {
			Queen[i].setX(i*cellSize+10);
			Queen[i].setY(board[i]*cellSize+10);
		}
	}
	

	
	@Override
	public void start(Stage stage){
		Pane root = new Pane();
		Scene scene =new Scene(root , WIDTH , HEIGHT);
		int area = n*n;
		
		Rectangle[] cell = new Rectangle[area];
		Color[] pattern = new Color[2];

		pattern[0] = Color.LEMONCHIFFON;
		pattern[1] = Color.GREY;
		
		for(int i = 0 ; i< n; i++) {
			for(int j = 0; j < n ; j++) {
				int k =j+(i*n);
				cell[k] = new Rectangle();
				cell[k].setHeight(cellSize);
				cell[k].setWidth(cellSize);
				cell[k].setX(cellSize*(i));
				cell[k].setY(cellSize*(j));
				cell[k].setArcWidth(20);
				cell[k].setArcHeight(20);
				cell[k].setFill(pattern[(i+j)%2]);
				cell[k].setCache(true);
				root.getChildren().add(cell[k]);
			}
		}
		ImageView[] Queen = new ImageView[n];
		Image q = new Image("/cop/6-512.png",cellSize-20,cellSize-20,false,false);
		for(int i = 0; i < n ; i++) {
			Queen[i]= new ImageView(q);
			Queen[i].setX(900);
			Queen[i].setY(900);
			root.getChildren().add(Queen[i]);
		}

		
		//problem demonstration
		Solve problem = new Solve(n);
		problem.start();
		
		//label
		Label sol = new Label("Start");
		sol.setTranslateX(WIDTH-160);
		sol.setTranslateY(HEIGHT-50);
		root.getChildren().add(sol);
		
		
		//button creation
		Button Next = new Button();
		Next.setText("Next");
		Next.setMaxHeight(50);
		Next.setMaxWidth(60);
		Next.setPrefSize(50, 30);
		Next.setTranslateX(15);
		Next.setTranslateY(HEIGHT - 45);
		root.getChildren().add(Next);
		//button action
		Next.setOnAction((eve)-> {
			problem.iter = true;
			if(prev > problem.D) {
				Queen[prev].setX(1000);
				Queen[prev].setY(1000);
			}
			prev= problem.D;
			if(problem.sol == true)
				sol.setText("Harraaaay,Found a solution!!");
			else
				sol.setText("Not a solution keep goin or quit");
			problem.sol = false;
			draw_Queens(Queen,root, problem.board , problem.D);
		});
		
		stage.setScene(scene);
		stage.setTitle("N Queens Game");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}