package proj;
import java.util.*;
public class Main{
	
	public static List<Integer>x;
	
	public static void dooo() {
		x.add(5);
		do2();
	}
	
	
	public static void do2() {
		System.out.println(x.get(0));
	}
	
	public static void main(String [] args0) {
		x = new ArrayList<Integer>();
		dooo();
	}
}

//
//scene.setOnMousePressed((e)->{
//	xOffset = e.getSceneX();
//	yOffset = e.getSceneY();
//});
//scene.setOnMouseDragged((e)->{
//	stage.setX(e.getScreenX() - xOffset);
//	stage.setY(e.getScreenY() - yOffset );
//});
