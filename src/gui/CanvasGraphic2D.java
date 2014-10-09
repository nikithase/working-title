package gui;

import gamelogic.Gamelogic;

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

	private int size_x;
	private int size_y;

	private int gamefield_width;
	private int gamefield_height;

	private int max_width;
	private int max_height;

	private Gamelogic logic;
	
	private Gamefield field;
	private UnitList list;
	private FocusedUnit unit;
	
	private String player_name;
	
	/**
	 * testing funktion
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Gamelogic logic = new Gamelogic();
		logic.initTestState();
		
		CanvasGraphic2D graphics = new CanvasGraphic2D(logic, 10, 10, "Klaus");
		graphics.initialize();
		graphics.refresh();
		
	}

	/**
	 * Creates a new Canvas Graphic2D Engine
	 * 
	 * @param logic
	 * @param size_x
	 * @param size_y
	 */
	public CanvasGraphic2D(Gamelogic logic, int size_x, int size_y, String player_name) {
		
		this.player_name = player_name;
		
		this.logic = logic;
		
		// compute sizes
		this.size_x = size_x;
		this.size_y = size_y;

		this.gamefield_width = size_x * TILE_SIZE;
		this.gamefield_height = size_y * TILE_SIZE;

		this.max_width = gamefield_width + UNITLIST_WIDTH;
		this.max_height = gamefield_height + FOCUSEDUNIT_HEIGHT;
	}

	@Override
	public void initialize() {
		
		// load Texture
		new TextureLib();

		// create Window
		
		JFrame f = new JFrame("RofL@" + VERSION);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create Components
		field = new Gamefield(gamefield_width, gamefield_height, size_x, size_y, TILE_SIZE, player_name);
		list = new UnitList(UNITLIST_WIDTH, gamefield_height);
		unit = new FocusedUnit(max_width, FOCUSEDUNIT_HEIGHT);

		field.setBounds(0, 0, gamefield_width, gamefield_height);
		list.setBounds(gamefield_width, 0, UNITLIST_WIDTH, gamefield_height);
		unit.setBounds(0, gamefield_height, max_width, FOCUSEDUNIT_HEIGHT);

		f.add(field);
		f.add(list);
		f.add(unit);
		f.add(new JPanel());

		f.setSize(max_width, max_height);
		f.setVisible(true);

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
		field.refresh(logic.getUnits());
		list.refresh(logic.getUnits());
		

	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

}
