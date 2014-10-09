package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.Graphics;

import javax.swing.*;

public class CanvasGraphic2D extends JPanel implements iGraphic {

	public final static int TILE_SIZE = 40;
	public final static int UNITLIST_WIDTH = 200;
	public final static int FOCUSEDUNIT_HEIGHT = 150;
	public final static int DELTA = 0;
	
	
	public static void main(String[] args) {
		
		int size_x = 10;
		int size_y = 10;
		

		int gamefield_width = size_x * TILE_SIZE;
		int gamefield_height = size_y * TILE_SIZE;
		
		int max_width =  gamefield_width + UNITLIST_WIDTH;
		int max_height =  gamefield_height + FOCUSEDUNIT_HEIGHT;
		
		
		JFrame f = new JFrame("RofL");
		
		
	    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    
	    
	    

	    Gamefield field = new Gamefield(gamefield_width, gamefield_height, size_x, size_y, TILE_SIZE);
	    UnitList list = new UnitList(UNITLIST_WIDTH, gamefield_height);
	    FocusedUnit unit = new FocusedUnit(max_width, FOCUSEDUNIT_HEIGHT);

	    field.setBounds(0, 0, gamefield_width, gamefield_height);
	    list.setBounds(gamefield_width, 0, UNITLIST_WIDTH, gamefield_height);
	    unit.setBounds(0, gamefield_height, max_width, FOCUSEDUNIT_HEIGHT);
	    
	    
	    f.add(field);
	    f.add(list);
	    f.add(unit);
	    f.add(new JPanel());
	    
	    

/*	    
	    BoxLayout boxLayout = new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS); 
	    
	    f.setLayout(boxLayout);
	    
	    
	    Box top = Box.createHorizontalBox();
	    top.setBounds(0, 0, max_width, gamefield_height);
//	    top.setAlignmentY(Component.TOP_ALIGNMENT);
	    Gamefield field = new Gamefield(gamefield_width, gamefield_height);
	    field.setSize(gamefield_width, gamefield_height);
	    UnitList list = new UnitList(UNITLIST_WIDTH, gamefield_height);
//	    list.setSize(UNITLIST_WIDTH, gamefield_height);
	    list.setBounds(0, 0, UNITLIST_WIDTH, gamefield_height);
	    
	    
//	    JButton button = new JButton("test");
//	    JButton button2 = new JButton("test2");
//	    button.setAlignmentY(Component.TOP_ALIGNMENT);
//	    button2.setAlignmentY(Component.TOP_ALIGNMENT);
//		top.add( button);
//		top.add( button2);

	   top.add( field);
	   top.add( list);

	    f.add(top);
	    
	    Box bot = Box.createVerticalBox();
	    bot.setBounds(gamefield_width, gamefield_height, max_width, FOCUSEDUNIT_HEIGHT);

	    bot.add( new FocusedUnit(max_width, FOCUSEDUNIT_HEIGHT) );

	    f.add(bot);

	    System.out.println(max_width + "x" + max_height);
	    
	    */
	    f.setSize( max_width, max_height);
	    f.setVisible( true );
	    
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showText(String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

}
