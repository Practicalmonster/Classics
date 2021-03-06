package application;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SampleController 
{
	@FXML
	private int Money;
	public Pane Fk;
	public Label Coin_label;
	public Label Money_label;
	public TextField Money_text;
	public TextField Coin_text;

	
	public static List<Integer> Coins;
	public void Add() {
		if(Coins == null)
			Coins = new ArrayList<Integer>();
		Money_label.setText(Money_text.getText());
		Money = Integer.parseInt(Money_text.getText());
		Coins.add(Integer.parseInt(Coin_text.getText()));
		Coin_label.setText(Coin_label.getText()+","+Coin_text.getText());
	}
	
	
	

	static int offsety;
	private static int[] sol;
	
	
	public boolean solve(int changes , int money) {
		if(changes == money)
		{
			Label Res = new Label();
			for(int i= 0 ; i < sol.length ; i++) {
				if(sol[i]!= 0)
					for(int j = 0; j < sol[i] ; j++) {
						Res.setText(Res.getText() + " " + Integer.toString(i));
					}
			}
			Res.setLayoutX(0);
			Res.setLayoutY(offsety);
			Fk.getChildren().add(Res);
			offsety+=15;
			return true;
		}else {
			for(int i = 0 ; i < Coins.size() ; i++) {
				sol[Coins.get(i)]+=1;
				if(changes + Coins.get(i) <= money)
					{
					if(Coins.get(i) != null)
						solve(changes + Coins.get(i) , money);
					}
				sol[Coins.get(i)]-=1;
			}
		}
		return false;
	}
	
	public void sol() {
		sol = new int[300];
		for(int i = 0 ; i < 300 ; i++)
			sol[i]=0;
		solve(0,Money);

		
	}
}
