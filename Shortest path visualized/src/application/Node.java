package application;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
public class Node{
	public Set<Integer> connected;
	public int ID;
	public Circle shape;
	public Label id_label;
	public int Ox;
	public int Oy;
	private int R;
	public Node(int Id,int x, int y,int raduis, Color X){
		Ox =x;
		Oy = y;
		
		ID = Id;
		id_label = new Label();
		R = raduis;
		shape = new Circle(R, X);
		id_label.setText(Integer.toString(ID));
		id_label.setLayoutX(x-4);
		id_label.setLayoutY(y-8);
		shape.setTranslateX(x);
		shape.setTranslateY(y);
		shape.toBack();
		connected = new HashSet<Integer>();
	}
	public void add_content(Pane current , Label prev) {
		current.getChildren().add(shape);
		current.getChildren().add(id_label);
		shape.setOnMousePressed((e)->{
			if(Integer.parseInt(prev.getText()) == -1)
				prev.setText(Integer.toString(ID));
			else {
				connected.add(Integer.parseInt(prev.getText()));
				prev.setText("-1");
			}
		});
	}
	
}
