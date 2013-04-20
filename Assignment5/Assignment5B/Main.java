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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * 
 * @author kizzle
 *
 */
public class MainPanel extends JPanel{
	// Constraints for the GribBag Layout
	GridBagConstraints gbc = new GridBagConstraints();
	
	
	private Integer recursionInput ;  // Value that is inside textFieldBox
	private Color[] comboSelection ;  // ComboBox selections
	private TriCanvas triCanvas ;
	public MainPanel(){
		
		recursionInput = new Integer(0);
		
		setLayout(new GridBagLayout());
		triCanvas = new TriCanvas();
		
		
		gbc.gridx = 0 ;
		gbc.gridy = 0 ;
		add(triCanvas,gbc);
		
		
		SidePanel sidepan = new SidePanel();
		gbc.gridx = 1 ;
		gbc.gridy = 0 ;
		add(sidepan,gbc);
		
	}
	/**
	 * Sets up the SidePanel objects
	 * @author kizzle
	 *
	 */
	private class SidePanel extends JPanel {
		// Holds all the comboBox instances
		public Combo[] combo;
		// DrawButton
		public JButton drawButton; 
		public Boolean ranonce ; 
		public SidePanel(){
			// Get the contentPane of itself
			ranonce = false;
			setLayout(new GridLayout(1,1)); // Set the layout
			
			// Add the components
			JPanel sidePanItems = new JPanel(); // holds all the sidePanel items
			sidePanItems.setLayout(new GridLayout(3,1,0,50)); //
			
			JPanel lblAndTextF = new JPanel(new GridLayout(1,2)); // parent panel for label and text

			FlowLayout flowLayout = new FlowLayout(); // define the layout for the panel_1
			flowLayout.setVgap(60);
			JPanel panel_1 = new JPanel(flowLayout); // child panel of label and text
			
			JLabel lbl = new JLabel("Recursive Depth"); // Make label
			panel_1.add(lbl); // add it
			JText textField = new JText("0",10) ; // make text field 
			textField.setEditable(true);
			panel_1.add(textField); // add it to child paen
					
			lblAndTextF.add(panel_1); // add it to the parent panel
			
			sidePanItems.add(lblAndTextF); // and it to parent parent panel
			//sidePanel.setSize(150,490);
			
			JPanel comboBoxes = new JPanel(new GridLayout(5,2));
			combo = new Combo[5]; 
			for ( int i = 1 ; i < 6 ; i++){
				JLabel label = new JLabel("Color "+i);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				combo[i-1] = new Combo();
				comboBoxes.add(label);
				comboBoxes.add(combo[i-1]);
			}
			// add the comboBoxes
			sidePanItems.add(comboBoxes);
			
			JPanel drawPanel = new JPanel();
			// make the draw button
			drawButton = new JButton("Draw!");
			drawButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					triCanvas.run();
				}
			});
			
			drawPanel.add(drawButton);
			sidePanItems.add(drawPanel);
			
			setSize(295,500);
			setVisible(true);
			add(sidePanItems);
			// Frame settings
			
		}
		public class JText extends JTextField implements KeyListener{
			public JText(String str,Integer width){
				super(str,width);
				addKeyListener(this);
			}
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {
				JText j = (JText) e.getSource();
				try {
					recursionInput = Integer.parseInt(j.getText());
					if ( recursionInput > 10 | recursionInput.equals(0)){
						j.setText("1");
						recursionInput = 1 ; 
					}
					System.out.println("Got it");
				}catch (NumberFormatException ex){
					j.setText("Invalid Input");
					j.setText("");
				}
			}
		}
		public class Combo extends JComboBox {
			public String[] option = { "RED","WHITE","BLUE","ORANGE","GREEN"} ;
			public Color comboColor ; 
			public Combo(){
				for ( String i : option) this.addItem(i);
				Random randint = new Random();
				this.setSelectedIndex(randint.nextInt(option.length));
				addActionListener(this);
			}
			public void actionPerformed(ActionEvent e) {
				JComboBox tmp = (JComboBox)e.getSource();
				int comboColor = tmp.getSelectedIndex();
				if (comboColor == 0){
					this.comboColor = Color.RED;
				}
				else if (comboColor == 1){
					this.comboColor = Color.WHITE;
				}
				else if (comboColor == 2){
					this.comboColor = Color.BLUE;
				}
				else if (comboColor == 3){
					this.comboColor = Color.ORANGE;
				}
				else if (comboColor == 4){
					this.comboColor = Color.GREEN;
				}
				else{
					this.comboColor = Color.RED;
				}
				System.out.println(comboColor);
		    }
		}
	}
	private class TriCanvas extends Canvas implements Runnable{
		private Polygon poly;
		
		//private JFrame frame ;
		private ArrayList<Color> aList ;
		public TriCanvas(){
			aList = new ArrayList<Color>();
			setSize(512,512);
			setBackground(Color.BLACK);
		}
		public void draw(int d, int x, int y, int S) { // Done drawing, stop.
			if ( d == 0 ){
				return;
			}
			int[] X = {x,x+S,x+(S/2)};
			int[] Y = {y+S,y+S,y};
			
			poly = new Polygon(X,Y,X.length);
			try{
				paint(getGraphics());
			
			}catch(NullPointerException ex){
				System.out.println("NullPointer Exception");
			}
			draw(d-1, x+(S/4), y      , (S/2));
			draw(d-1, x      , y+(S/2), (S/2));
			draw(d-1, x+(S/2), y+(S/2), (S/2)); 
		}
		
		public void paint(Graphics g){		
			//g.setColor(comboSelection[colorIterateNum]);
			g.setColor(Color.RED);
			
			g.drawPolygon(poly);
		}
		@Override
		public void run() {
			super.paint(getGraphics()); // Clear the Canvas
			if ( recursionInput > comboSelection.length){
				for (int i = 0 ; i < comboSelection.length ; i++){
					aList.add();
				}
			}
			draw(recursionInput,0,0,this.getWidth()); // Draw one on the canvas recursively
		}
	}

	public static void main(String[] args){
		JFrame frame = new JFrame(); // Main frame
		frame.getContentPane().add(new MainPanel());
		frame.setSize(800,600);
		frame.setVisible(true);
	}
}

