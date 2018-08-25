package application;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SampleController {
	private static List<Node> crcls;
	private int[][] con;
	@FXML
	public Label toolSelected;
	public void Change_to_Node() {
		toolSelected.setText("Node");
		
	}
	public void Change_to_path() {
		toolSelected.setText("Path");
	}
	@FXML
	public Button x;
	public void Change_to_solve() {
		if(toolSelected.getText().compareTo("Start Point") == 0)
		{
			x.setText("Start Point");
			toolSelected.setText("End Point");
		}
		else
		{
			x.setText("End Point");
			toolSelected.setText("Start Point");
	
		}
	}
	@FXML
	public Pane Sketch;
	public Label prev;
	public void Draw(MouseEvent eve) {
		if(con == null)
			con = new int[200][200];
		if(toolSelected.getText().compareTo("Node") == 0) {
			if(crcls == null) {
				crcls = new ArrayList<Node>();
			}
			int x = (int) eve.getX();
			int y = (int) eve.getY();
			Color c = Color.web("0xA5FFD6");
			Node NewNode = new Node((int)crcls.size(),x,y,20,c);
			NewNode.add_content(Sketch,prev);
			crcls.add(NewNode);
		}else
			if(toolSelected.getText().compareTo("Path") == 0) {
				 {
						for(int i = 0 ; i < crcls.size() ; i++) {
							if(crcls.get(i).connected.size() > 0)
							{
								Set<Integer> sz = crcls.get(i).connected;
								for(Integer S : sz) {
									if(con[S][i] == 0 && S!= i) {
//										System.out.println(crcls.get(i).ID + " " +crcls.get(sz.get(j)).ID);
										Line x = new Line();
										con[i][S]=1;
										con[S][i]=1;
										x.setStartX(crcls.get(i).Ox);
										x.setStartY( crcls.get(i).Oy);
										x.setEndX( crcls.get(S).Ox);
										x.setEndY( crcls.get(S).Oy);
										x.setFill(Color.GREEN);
										x.setStroke(Color.BLACK);
										x.setStrokeWidth(2);
										x.toFront();
										Sketch.getChildren().add(x);
									}
								}
							}
						}
					}
			}
	}
	
	
	public void abort() {
		System.exit(0);
	}
}
//scene.setOnMousePressed((e)->{
//xOffset = e.getSceneX();
//yOffset = e.getSceneY();
//});
//scene.setOnMouseDragged((e)->{
//stage.setX(e.getScreenX() - xOffset);
//stage.setY(e.getScreenY() - yOffset );