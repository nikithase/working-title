package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Ludwig Biermann
 *
 */
public class CanvasGraphic2D extends JPanel implements iGraphic {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9014567225901557748L;
	
	public final static int TILE_SIZE = 40;
	public final static int UNITLIST_WIDTH = 200;
	public final static int FOCUSEDUNIT_HEIGHT = 150;
    private static final String VERSION = "CanvasGraphic2D v0.1";
	
	/**
	 * testing funktion
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		// compute sizes
		int size_x = 10;
		int size_y = 10;
		

		int gamefield_width = size_x * TILE_SIZE;
		int gamefield_height = size_y * TILE_SIZE;
		
		int max_width =  gamefield_width + UNITLIST_WIDTH;
		int max_height =  gamefield_height + FOCUSEDUNIT_HEIGHT;
		
		// create Window
		JFrame f = new JFrame("RofL");
	    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    
	    
	    // create Components 
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
