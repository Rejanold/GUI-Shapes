import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Driver to make interactive window
 * @author AMTHAUHA
 *
 */
public class ShapeFrame extends JFrame{
	public static final int FRAME_WIDTH = 500;
	public static final int FRAME_HEIGHT = 500;
	
	public ShapeFrame(){
		JPanel sp = new ShapePanel();
		add(sp);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
	}
	
	public static void main(String[] args){
		
		JFrame frame = new ShapeFrame();
		
		frame.setTitle("Shape Stuff");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	

}