import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JFrame;




public class TriCanvas extends Canvas implements Runnable{
	private Polygon poly;
	
	//private JFrame frame ;
	private Graphics g ; 
	public TriCanvas(){
		JFrame frame = new JFrame();
		frame.setSize(600,600);
		
		
		frame.getContentPane().add(this);
		frame.setVisible(true);
		setSize(512,512);
		setBackground(Color.BLACK);
		
		run();
	}
	public void draw(int d, int x, int y, int S) { // Done drawing, stop.
		if ( d == 0 ){
			return;
		}
		int[] X = {x,x+S,x+(S/2)};
		int[] Y = {y+S,y+S,y};
		poly = new Polygon(X,Y,X.length);
		paint(getGraphics());
		draw(d-1, x+(S/4), y, (S/2));
		draw(d-1, x, y+(S/2), (S/2));
		draw(d-1, x+(S/2), y+(S/2), (S/2)); 
	}
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.drawPolygon(poly);
		
	}
	
	public static void main(String[] args){
		new TriCanvas();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		draw(8,0,0,512);
	}
}
