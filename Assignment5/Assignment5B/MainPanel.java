import java.awt.Canvas;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainPanel extends JPanel{
	GridBagConstraints gbc = new GridBagConstraints();
	public MainPanel(){
		setLayout(new GridBagLayout());
		Canvas triCanvas = new Canvas();
		triCanvas.setSize(512, 512);
		triCanvas.setBackground(Color.BLACK);
		gbc.gridx = 0 ;
		gbc.gridy = 0 ;
		add(triCanvas,gbc);
		
		
		SidePanel sidepan = new SidePanel();
		gbc.gridx = 1 ;
		gbc.gridy = 0 ;
		add(sidepan,gbc);
		
	}


	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.getContentPane().add(new MainPanel());
		frame.setSize(800,800);
		frame.setVisible(true);
	}
}
