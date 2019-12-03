import java.awt.*;
import javax.swing.*;


/**
 * This creates the area to draw on and draws the specified shape.
 * 
 * @author AMTHAUHA
 *
 */
public class DrawArea extends JComponent {

	private int radius;
	private int width; //could also be base of right triangle
	private int height;
	private String shape;
	
	/**
	 * constructor for circle
	 * @param shape string "circle"
	 * @param r the radius the user entered
	 */
	public DrawArea(String shape, int r){
		this.shape = shape;
		radius = r;
		
	}
	/**
	 * constructor for rectangle and right triangle
	 * @param shape either the string "rectanlge" or "triangle"
	 * @param w - either the width or the base
	 * @param h - height of rect or tri
	 */
	public DrawArea(String shape, int w, int h){
		this.shape = shape;
		width = w;
		height = h;
		
		
	}
	/**
	 * paint method that draws the selected shape
	 */
	public void paintComponent(Graphics g){
		removeAll();
		if(shape.equals("Circle")){
			//make a green circle
			g.setColor(Color.green);
			g.fillOval(100,100,radius*2, radius*2);
		}
		else if(shape.equals("Rectangle")){
			//make blue rectangle
			g.setColor(Color.blue);
			g.fill3DRect(100,100,width,height,false);
		}
		else if(shape.equals("Right Triangle")){
			g.setColor(Color.red);
			int[] x = {100,100,width+100} ;
			int[] y = {100,height+100,height+100};

			g.fillPolygon(x,y,3);

		}
        
	}
	
}