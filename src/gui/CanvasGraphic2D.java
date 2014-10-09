package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gamelogic.Gamelogic;
import gui.canvas.graphic2d.FocusedUnit;
import gui.canvas.graphic2d.Gamefield;
import gui.canvas.graphic2d.TextureLib;
import gui.canvas.graphic2d.UnitList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Client;

/**
 * 
 * This class creates a simple 2d Grafik Engine
 * 
 * @author Ludwig Biermann
 *
 */
public class CanvasGraphic2D extends JPanel implements iGraphic,ActionListener {

	private static final long serialVersionUID = -9014567225901557748L;

	/**
	 * Size of the graphic Elements
	 */
	private final static int TILE_SIZE = 40;
	private final static int UNITLIST_WIDTH = 200;
	private final static int FOCUSEDUNIT_HEIGHT = 150;

	/**
	 * Version
	 */
	public static final String VERSION = "CanvasGraphic2D v1.0";

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

	private String player;

	private Client client;

	
	private JFrame login;
	
	private JTextField ip_e;
	private JTextField player_e;
	
	
	private boolean run;
	
	/**
	 * testing function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Gamelogic logic = new Gamelogic();
		logic.initTestState();

		CanvasGraphic2D graphics = new CanvasGraphic2D(logic, 11, 11, "Klaus");
		graphics.initialize();
		graphics.refresh();

	}


	/**
	 * Creates a new Canvas Graphic2D Engine
	 * 
	 * @param logic
	 *            the Logic System
	 * @param size_x
	 *            width of the field
	 * @param size_y
	 *            height of the field
	 * @param player_name
	 *            the player name
	 * @param client the client ... needed to  connect to server and send commands 
	 */	
	public CanvasGraphic2D(Gamelogic logic, int size_x, int size_y, String player_name, Client client) {
		this(logic, size_x, size_y, player_name);
		System.out.println(client == null);
		this.client = client;
	}

	/**
	 * Creates a new Canvas Graphic2D Engine
	 * 
	 * @param logic
	 *            the Logic System
	 * @param size_x
	 *            width of the field
	 * @param size_y
	 *            height of the field
	 * @param player_name
	 *            the player name
	 */
	public CanvasGraphic2D(Gamelogic logic, int size_x, int size_y, String player_name) {

		this.player = player_name;

		this.logic = logic;

		System.out.println("StartUnits: " + logic.getUnits().size());
		
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

		// ask ip and name
		
		

		login = new JFrame("Login@RofL@" + VERSION);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		login.setLayout(new GridLayout(0,2));
		
		

		JTextField player_t = new JTextField("Player Name: ");
		player_t.setEditable(false);
		
		player_e = new JTextField("Klaus");
		player_e.setEditable(true);
		
		JTextField ip_t = new JTextField("IP Adresse: ");
		ip_t.setEditable(false);
		
		ip_e = new JTextField("127.0.0.1");
		ip_e.setEditable(true);

		
		
		
		JButton login_b = new JButton("login");
		login_b.addActionListener(this);
		
		login.add(player_t);
		login.add(player_e);
		login.add(ip_t);
		login.add(ip_e);
		login.add(new JPanel());
		login.add(login_b);
		
		login.setSize(300, 150);
		login.setVisible(true);
		
		run = false;
		
	

	}
	
	/**
	 * initialize with login informaion
	 * 
	 * @param ip
	 * @param player
	 */
	private void initializeGame(String ip, String player){
		// load Texture
		new TextureLib();

		// create Window

		JFrame f = new JFrame("RofL@" + VERSION);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create Components
		unit = new FocusedUnit(max_width, FOCUSEDUNIT_HEIGHT, player);
		field = new Gamefield(size_x, size_y, TILE_SIZE, player, logic, unit, client);
		/** TEST ZWECKE */
		field.setCanvas(this);
		list = new UnitList(UNITLIST_WIDTH, gamefield_height, player);

		field.setBounds(0, 0, gamefield_width, gamefield_height);
		list.setBounds(gamefield_width, 0, UNITLIST_WIDTH, gamefield_height);
		unit.setBounds(0, gamefield_height, max_width, FOCUSEDUNIT_HEIGHT);

		f.add(field);
		f.add(list);
		f.add(unit);
		f.add(new JPanel());

		this.refresh();
		
		f.setSize(max_width, max_height);
		f.setVisible(true);
		
		System.out.println(client == null);
		//testzwecke
		if(client != null){
			client.connect(ip, player);
		}
	}

	@Override
	public void nextTurn() {
		field.refresh(logic.getUnits());
		list.refresh(logic.getUnits());
	}

	@Override
	public void showText(String text) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		if(run){
			field.refresh(logic.getUnits());
			list.refresh(logic.getUnits());
		}

	}

	@Override
	public String getVersion() {
		return VERSION;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("login: " + ip_e.getText() + " | " + player_e.getText());
		
		this.initializeGame(ip_e.getText(), player_e.getText());
		
		login.setVisible(false);
	}

}
