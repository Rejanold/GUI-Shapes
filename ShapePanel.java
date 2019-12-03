import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import javax.swing.*;
/**
 * This class is designed to get user input in the form of radio buttons.
 * The user selects a shape. Then based on that selection, the correct
 * panel is added to the GUI to ask the user about specifics for that
 * selected shape.
 * @author AMTHAUHA
 *
 */
public class ShapePanel extends JPanel{

	private JRadioButton circle= new JRadioButton("Circle") ;	//for user to select if they want a circle
	private JRadioButton rectangle= new JRadioButton("Rectangle") ;	//for user to select if they want a rectangle
	private JRadioButton rtTriangle = new JRadioButton("Right Triangle") ;;	//for user to select if they want a right triangle
	private ButtonGroup group = new ButtonGroup();		//for mutual exclusion of shape selection
	private JPanel buttonPanel = new JPanel();//for the radio buttons
	private JPanel selectedShape;//to show the selected shape panel
	public ShapePanel(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));//vertical
		setBorder(new EmptyBorder(10, 10, 10, 10));
		//add each button to the group for mutual exclusion
		group.add(circle);
		group.add(rectangle);
		group.add(rtTriangle);
		//add buttons to panels
		buttonPanel.add(circle);
		buttonPanel.add(rectangle);
		buttonPanel.add(rtTriangle);
		//add button panel to ShapePanel
		add(buttonPanel);
		//create and add each item listener for radio buttons
		ItemListener circleShape = new RadioCircle();
		circle.addItemListener(circleShape);
		ItemListener rectShape = new RadioRect();
		rectangle.addItemListener(rectShape);
		ItemListener triShape = new RadioTri();
		rtTriangle.addItemListener(triShape);

	}
	/**
	 * All of the classes below implement item listeners for shapes.
	 * Each removes the radio button then creates the panel needed
	 * to collect information about that specified shape.
	 *
	 * Calling repaint() after revalidate() ensures that your layout and
	 * screen are up to date. So if you are using any container, like JFrame or JPanel
	 * and keep adding and removing components, always call revalidate() and repaint()
	 * to refresh GUI.
	 *
	 *
	 */
	private class RadioCircle implements ItemListener
	{
		public void itemStateChanged(ItemEvent e) {
			removeAll(); // remove the radio buttons.
			selectedShape = new CirclePanel();//make a circle panel
			add(selectedShape);//add to ShapePanel
			revalidate();
			repaint();


		}
	}
	private class RadioRect implements ItemListener
	{
		public void itemStateChanged(ItemEvent e) {
			removeAll(); // remove the radio buttons.
			selectedShape = new Rectangle();//make a circle panel
			add(selectedShape);//add to ShapePanel
			revalidate();
			repaint();

		}
	}
	private class RadioTri implements ItemListener
	{
		public void itemStateChanged(ItemEvent e) {
			removeAll(); // remove the radio buttons.
			selectedShape = new RightTriangle();//make a circle panel
			add(selectedShape);//add to ShapePanel
			revalidate();
			repaint();


		}
	}
}