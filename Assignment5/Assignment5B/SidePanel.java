import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.SwingConstants;


public class SidePanel extends JPanel {
	public Combo[] combo;
	public Integer recursionInput ; 
	public JButton drawButton; 
	public SidePanel(){
		// Get the contentPane of itself
		
		setLayout(new GridLayout(1,1)); // Set the layout
		
		// Add the components
		
		JPanel sidePanel = new JPanel();
		
		JPanel lblAndTextF = new JPanel(new GridLayout(1,2));
			
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setVgap(60);
			lblAndTextF.add(panel_1);
			JLabel lbl = new JLabel("Recursive Depth");
			panel_1.add(lbl);
			JText textField = new JText("0",10) ;
			textField.setEditable(true);
			panel_1.add(textField);
				
		sidePanel.setLayout(new GridLayout(3,1,0,50));
		sidePanel.add(lblAndTextF);
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
		sidePanel.add(comboBoxes);
		
		JPanel panel = new JPanel();
		sidePanel.add(panel);
		// make the draw button
		drawButton = new JButton("Draw!");
		drawButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		panel.add(drawButton);
		
		setSize(295,500);
		setVisible(true);
		add(sidePanel);
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
				if ( recursionInput > 10){
					j.setText("");
				}
				System.out.println("Got it");
			}catch (NumberFormatException ex){
				j.setText("Invalid Input");
				j.setText("");
			}
		}
	}
	public static class Combo extends JComboBox {
		public static String[] option = { "RED","WHITE","BLACK","ORANGE","GREEN"} ;
		public Color comboColor ; 
		public Combo(){
			super(option);
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
				this.comboColor = Color.BLACK;
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
		public void setColor(Color setColor){
			this.comboColor = setColor ; 
		}
		public Color getColor(){
			return this.comboColor;
		}
	}
	

}
