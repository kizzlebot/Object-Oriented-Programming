/*
 *       Name: James Choi
 * 	   Course: COP3330 (Mon/Wed/Fri)
 * Assignment: Assignment #5B
 * 		 Date: April 21, 2012
 *        PID: J3373134
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Contains all the necessary components and logic for rendering the Sierpinski
 * @author kizzle
 */
@SuppressWarnings("All")
public class SierpinskiVisualizer {

	private static final int SIZE = 512; // should be a power of 2
	
	/**
	 * enum of Colors to display. Contains methods to get String representation and Color object
	 * @author kizzle
	 *
	 */
	private static enum COLOR {
		//List all colors
		BLUE(Color.BLUE,"Blue"),
		GREEN(Color.GREEN,"Green"),
		YELLOW(Color.YELLOW,"Yellow"),
		WHITE(Color.WHITE,"White"),
		RED(Color.RED,"Red"),
		ORANGE(Color.ORANGE,"Orange"),
		CYAN(Color.CYAN,"Cyan"),
		MAGENTA(Color.MAGENTA,"Magenta"),
		PINK(Color.PINK,"Pink"),
		GRAY(Color.GRAY,"Gray"),
		DARKGREY(Color.DARK_GRAY,"Dark Grey");
		private Color color;
		private String name;
		
		COLOR(Color c, String n) {
			this.color = c;
			this.name = n;
		}
		private Color getColor() {
			return this.color;
		}
		public String toString() {	
			return this.name;
		}
	}
	
	/*
	 * All the GUI components
	 */
	private JFrame frame; // Main Frame
	private myCanvas canvas; // my modified Canvas, myCanvas, with identifier canvas
	private JPanel sidePanel; // The sidepanel holds the textfield, the comboboxes, the checkbox, and the draw button
	private JTextField recDepthTextField; // Holds the textField input 
	private JComboBox[] colorComboBox; // Holds all the comboBoxes as an array
	private JCheckBox randomizeCheckBox; // The Checkbox object
	private JPanel mainPanel ; // the mainpanel holds the canvas and the sidepanel 
	private GridBagConstraints gbc = new GridBagConstraints(); ; // The constraints used in the mainPanel
	// Used to keep track of coloring the triangles correctly.
	private Color[] colorScheme,recurseColor;
	private Integer depth;
	

	
	
	
	// Generate the GUI
	public SierpinskiVisualizer() {
		colorScheme = new Color[5] ; 
//***********************************************************************************************
//	I.	Build the frame.
//***********************************************************************************************
		frame = new JFrame();
		frame.setSize(750,540);
//***********************************************************************************************
//		A. Build mainPanel 
//***********************************************************************************************
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
//***********************************************************************************************
//			a. Build the canvas and set its params
//***********************************************************************************************		
		canvas = new myCanvas();
		canvas.setSize(512,512); 
		canvas.setBackground(Color.BLACK);
			
//***********************************************************************************************
//			b. Build sidePanel
//***********************************************************************************************		
		// Group all the user input together.
		sidePanel = new JPanel(); //  container for all the user input
		sidePanel.setLayout(new GridLayout(4,1,0,0));
		sidePanel.setSize(500,frame.getHeight());
//**************************************************************************************************************
//		   		1. Build panel that holds Label and text field 
//**************************************************************************************************************	
		// Set up Label and recursivetextField
		FlowLayout flow = new FlowLayout(); // Layout manager set up
		flow.setVgap(50); // Set the vertical gap
		JPanel lblAndText= new JPanel(flow); // This is what holds the two objects (lbl and textfield) <Child>
		JPanel lblAndText1 = new JPanel(new GridLayout(1,2)); // This is what holds the lblTextField panel <Parent>
		//*****************************************************************************************************************************
		//	    	1a. Build + Add JLabel 	
		//*****************************************************************************************************************************
		lblAndText.add(new JLabel("Recursive Depth")); // add the JLabel 
		//*****************************************************************************************************************************
		//	    	1b. Build + Add JTextField 
		//*****************************************************************************************************************************
		recDepthTextField = new JTextField("1",5); // make Textfield with initial value and
		depth = 1 ; // set the depth to the initial value

		// add the textField to its panel
		lblAndText.add(recDepthTextField);
		// add textFieldPanel to its parent
		lblAndText1.add(lblAndText); 		
		// add the parent to sidePanel
		sidePanel.add(lblAndText1);
		
		
		
//**************************************************************************************************************
//	    		2. Build panel that holds the comboBoxes 
//**************************************************************************************************************		
		// Add color selection input.
		JPanel comboPanel = new JPanel();
		comboPanel.setLayout(new GridLayout(5,2)); // 5x2 GridLayout
		colorComboBox = new JComboBox[5]; // Hold the comboBoxes
		Random rand = new Random();
	//**************************************************************************************************************
	//	    	2a. Build the comboBoxes and store into array
	//			- Make a new instance of JComboBox and add to index of colorcomboBox
	//			- Add the list of color choices to the JComboBox instance
	//			- Make the
	//**************************************************************************************************************
		COLOR[] col = COLOR.values();
		for (int i=0; i<5; ++i) {
			// Make a new instance of a comboBox and add to index of colorComboBox array
			colorComboBox[i] = new JComboBox();
			// Add all the possible color choices to it
			colorComboBox[i].setModel(new DefaultComboBoxModel(COLOR.values()));
			colorComboBox[i].setSelectedIndex(rand.nextInt(col.length));
			// Add a label and comboBox like "Color # <JComboBox>"
			JLabel label = new JLabel("Color "+(i+1));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			comboPanel.add(label);
			comboPanel.add(colorComboBox[i]);
		}
		sidePanel.add(comboPanel);
//**************************************************************************************************************
//	   		3. Build random Checkbox
//**************************************************************************************************************				
		// Add randomize input. Put a listener on the check box to control whether the colors are enabled
		randomizeCheckBox = new JCheckBox("Randomize colors at each level");
		randomizeCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// if the randomizeCheckBox is checked then disable all the comboBoxes
				if ( randomizeCheckBox.isSelected() ){
					for ( JComboBox comboB : colorComboBox){
						comboB.setEnabled(false);
					}
				}
				// If not checked, then enable all the comboBoxes
				else{
					for ( JComboBox comboB : colorComboBox){
						comboB.setEnabled(true);
					}
				}
			}
		});
		// Add it to the sidePanel
		sidePanel.add(randomizeCheckBox);
		
//**************************************************************************************************************
//	   		 4. Build panel that holds the drawButton
//**************************************************************************************************************		
		JPanel drawPanel = new JPanel(new FlowLayout());
		//**************************************************************************************************************
		//    	4a Build button and describe behavior
		//************************************************************************************************************** 
		JButton drawButton = new JButton("Draw!") ;
		// Define what to do when the drawButton is clicked
		drawButton.addActionListener(new ActionListener() {
			// When the draw button is clicked, gather and verify all the inputs by calling verifyInputs.
			// If verifyInputs returns true, we execute canvas's draw method. Otherwise, nothing. 
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifyInputs()){
					canvas.run();					
				}
			}
		});
		// Add drawButton to the drawPanel
		drawPanel.add(drawButton);
		// Add drawPanel to the sidePanel
		sidePanel.add(drawPanel);
		
//**************************************************************************************************************
//**************************************************************************************************************
//		Add canvas to MainPanel 
//		Add SidePanel to MainPanel
//		Add MainPanel to frame
//**************************************************************************************************************
//**************************************************************************************************************
		// Set gridbag constraints to (0,0)
		gbc.gridx = 0 ;
		gbc.gridy = 0 ;
		// Set gridbag constraints to (0,0)
		// Add canvas to the mainPanel inside the frame
		mainPanel.add(canvas,gbc);
		// Change gridbagconstraints for adding sidepanel
		gbc.gridx = 2 ;
		gbc.gridy = 0 ;
		// Add sidePanel to mainPanel
		mainPanel.add(sidePanel,gbc);
		// add mainPanel to frame
		frame.add(mainPanel);
		frame.setVisible(true);
		
		/*  Get the graphics object of the canvas. It's important to do this AFTER the frame is
	   	 *	visible, since before this there is no graphics object associated with the canvas.
		 */
		
		canvas.getGraphics();
	}
	/**
	 * This methods verifies all the inputs from the JTextField, and the five JComboBoxes.  
	 * It also sets the values of the colorScheme[] based on if the checkbox is selected or not.
	 * If the checkbox is selected, it automatically generates random colors from the COLOR enum
	 * and places them into the index of 
	 * Therefore, in the draw() function we do not need to validate anything, because for draw() to 
	 * be called, the inputs must be valid. 
	 * @return Boolean, true if we are good to draw. false if inputfield was invalid somehow
	 */
	private Boolean verifyInputs(){
		// Validate everything.
		// Get the textfield input and make sure it is valid
		try {
			// try to assign input to depth
			depth = Integer.parseInt(recDepthTextField.getText());
			// if input was an integer but not between 1 and 10
			if (depth < 1 | depth > 10){
				// reset the field text to 1
				// set depth back to the default 1
				depth = 1 ; 
				// set the tooltip
				recDepthTextField.setToolTipText("Input must be a value between 1 and 10");
				JOptionPane.showMessageDialog(null, "Input must be between 1 and 10");
				return false;
			}
			COLOR[] co = COLOR.values() ;  
			// and then determine the colorscheme based on the checkbox
			if (!randomizeCheckBox.isSelected()){
				// Gather the selected colorschemes from ComboBoxes
				colorScheme = new Color[colorComboBox.length] ; 
				depth = Integer.parseInt(recDepthTextField.getText());
				for (int i = 0 ; i < colorComboBox.length ; i++){
					colorScheme[i] = co[colorComboBox[i].getSelectedIndex()].getColor();
				}
			}
			else{
				// Random object for random number generation
				Random rand = new Random();
				// We don't necessarily need to have exactly five random colors repeated for a random color recursion 
				colorScheme = new Color[depth+1];
				for ( int i = 0 ; i < colorScheme.length ; i++){
					colorScheme[i] = co[rand.nextInt(colorComboBox.length)].getColor();
				}
			}
			return true;
			
		}
		// If it isn't an integer
		catch(NumberFormatException ex){
			// reset the field text to 1
			recDepthTextField.setText("1");
			// set depth back to the default 1
			depth = 1 ; 
			// show the tooltip
			recDepthTextField.setToolTipText("Input must be an Integer!");
			JOptionPane.showMessageDialog(null, "Input must be an Integer!");
			return false;
		}


	}
	/**
	 * myCanvas extends Canvas.  It is different from Canvas in that, the draw methods that draws the triangles 
	 * are defined within itself.  It sets its own size and background color on instantiation.  
	 * @author kizzle
	 *
	 */
	private class myCanvas extends Canvas implements Runnable{
		private static final long serialVersionUID = 1L; // No idea what this is but i get an error otherwise
		private Polygon poly;
		public myCanvas(){
			setSize(SIZE,SIZE);
			setBackground(Color.BLACK);
			setVisible(true);
			poly = new Polygon();
		}
		
		/**
		 *  
		 */
		private void draw() {
			// Erase the old image.
			clear(); // custom method like a custom whip.. oh yea

			// Draw the base triangle over hurr first before executing recursion
			Graphics g = getGraphics();
			g.setColor(Color.WHITE); // set the color for the base triangle
			g.drawLine(     0, SIZE, SIZE, SIZE);
			g.drawLine(SIZE/2,    0, SIZE, SIZE);
			g.drawLine(SIZE/2,    0,    0, SIZE);
			
			// Now draw the rest of the inner triangles with the recursive function!
			draw(depth,0,0,SIZE);
		}
		
		/**
		 * This is a overrided draw method (with params) that gets called from the draw method (without params)  
		 * @param d depth
		 * @param x starts from 0
		 * @param y starts from 0 
		 * @param S Size that decreases throughout recursion
		 */
		private void draw(int d, int x, int y, int S) {
			if (d==0) return; // Base Case

			recurseColor = new Color[depth-d]; // Aids in looping back to the choice1 if recursion is greater than then number of comboBoxes
			Graphics g = getGraphics(); // Get this comboBox's graphics object

			/* Otherwise, draw big triangle at this level, between the points
			 * shown in the figure. You can use the fillPolygon() method of
			 * the Graphics object of your Canvas. Make sure you get the color
			 * correct!
			 */
			int[] X = {x+(S/4), x+(S/2),  x+3*(S/4) };  // Coords of the upsidedown Triangle
			int[] Y = {y+(S/2), y+S    ,  y+(S/2)   }; // Coords of the upsidedown Triangle
			poly = new Polygon(X,Y,X.length);

			g.setColor(colorScheme[recurseColor.length%colorScheme.length]);
			paint(g);
			
			/* Draw the subtriangles. The self-similarity of fractals means
			 * that they are themselves Sierpinski triangles of depth d-1.
			 */
			draw(d-1, x+S/4, y    , S/2);
			draw(d-1, x    , y+S/2, S/2);
			draw(d-1, x+S/2, y+S/2, S/2);			
		}
		/**
		 * This method simply fills a polygon that is created from the method that calls this method
		 */
		public void paint(Graphics g){		
			g.fillPolygon(poly);
		}
		/**
		 * This method clears the canvas
		 * We can setColor black and then draw a huge rectangle to clear the canvas, but Canvas's paint method's default
		 * behavior is to clear the canvas. So, why waste a method that is already provided?
		 */
		private void clear(){
			super.paint(getGraphics());
		}
		/**
		 * Overloaded method, that is overloaded because we implement runnable.  
		 * We implememnt runnable because this class's instance is executed by a thread.  
		 */
		@Override
		public void run() {
			draw(); // Just call the draw method
		}
	}
	
	public static void main(String[] args) {
		// Just make an instance. 
		new SierpinskiVisualizer();
	}
}

