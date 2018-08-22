package Soduk;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Interface extends Application{

	public static Label[] text = new Label[81];
	private static final int HEIGHT = 643;
	private static final int WIDTH= 603;
	private static final int CellSize= 67;
	public static double mousex=0;
	public static double mousey = 0;
	public static boolean Run  = false;
	public static Solve problem;
	public void run_problem(int [] grid ) {
		problem =  new Solve(grid);
		problem.start();
		Run =true;
		}
	
	
	@Override
	public void start(Stage stage){
		Pane root = new Pane();
		Scene scene = new Scene(root, WIDTH , HEIGHT);

		for(int i = 0; i < 9 ; i++)
			for(int j = 0 ; j < 9 ; j++)
			{
				int z = j + i*(9);
				text[z]=new Label();
				text[z].setText("-");
				text[z].setScaleX(5);
				text[z].setScaleY(5);
				text[z].setTranslateX(j*CellSize);
				text[z].setTranslateY(i*CellSize);
				text[z].setAlignment(Pos.CENTER);
				text[z].setPrefSize(CellSize, CellSize);
				root.getChildren().add(text[z]);
			}
		Rectangle[] x = new Rectangle[9];
		for(int i = 0 ; i < 3 ; i++)
			for(int j = 0; j < 3 ; j++)
			{
				int z = j+(i*3);
				x[z]= new Rectangle();
				x[z].setFill(Color.TRANSPARENT);
				x[z].setX(i*CellSize*3);
				x[z].setY(j*CellSize*3);
				x[z].setStroke(Color.CORAL);
				x[z].setHeight(CellSize*3);
				x[z].setWidth(CellSize*3);
				x[z].setStrokeWidth(3);
				root.getChildren().add(x[z]);
			}
		
		Rectangle[] g = new Rectangle[81];
		for(int i = 0 ; i < 9 ; i++)
			for(int j = 0; j < 9 ; j++)
			{
				int z = j+(i*3);
				g[z]= new Rectangle();
				g[z].setFill(Color.TRANSPARENT);
				g[z].setX(i*CellSize);
				g[z].setY(j*CellSize);
				g[z].setStroke(Color.WHITE);
				g[z].setHeight(CellSize);
				g[z].setWidth(CellSize);
				g[z].setStrokeWidth(1);
				root.getChildren().add(g[z]);
			}
		
		scene.setOnMouseMoved((e)->{
			mousex=e.getX();
			mousey =e.getY();	
			if(Run == true)
				for(int i = 0; i < problem.D ;i++) {
					text[i].setText(Integer.toString(problem.grid[i]));
				}
		});
		

		
		scene.setOnKeyPressed((e)-> {
			int j =(int) Math.floor((double)mousex / (double) CellSize);
			int i =(int) Math.floor((double)mousey / (double) CellSize);
			if(Integer.parseInt(e.getText()) == 0)
				text[j+(i*9)].setText("-");
			else {
				int num=Integer.parseInt(e.getText());
				text[j+(i*9)].setText(Integer.toString(num));
			}
		});
		
		
		Rectangle Bar = new Rectangle();
		Bar.setX(5);
		Bar.setY(HEIGHT-30);
		Bar.setHeight(50);
		Bar.setWidth(WIDTH-10);
		Bar.setArcHeight(30);
		Bar.setArcWidth(60);
		Bar.setFill(Color.GREY);
		root.getChildren().add(Bar);
		
		
		
		
		Button Start = new Button("Begin");
		Start.setTranslateX(WIDTH/2 - 30);
		Start.setTranslateY(HEIGHT-30);
		Start.setPrefSize(60,30);
		root.getChildren().add(Start);
		Start.setOnAction((e)->{
			int[] grid =  new int [81];
			for(int i = 0 ; i < 81 ;i++) {
				if(text[i].getText() != "-")
					grid[i] = Integer.parseInt(text[i].getText());
				else
					grid[i]=0;
			}
			run_problem(grid );
		});
		stage.setScene(scene);
		stage.show();
		
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
}

