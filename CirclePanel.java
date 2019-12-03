import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.*;

/**
 * This class has the components to collect information about
 * circles (radius) and then can calculate the area and perimeter. 
 * It also creates a drawing area that can draw a circle.
 *
 */
public class CirclePanel extends JPanel{
	//to collect the radius
	private JPanel radiusPanel = new JPanel();
	private JLabel radiusLabel = new JLabel("Enter circle radius: ");
	private JTextField radiusField = new JTextField(10);
	//area for buttons for area and perimeter
	private JPanel buttonPanel = new JPanel();
	private JButton areaButton = new JButton("Calculate Area");
	private JButton perimeterButton = new JButton("Calculate Perimeter");
	//area to display area information
	private JPanel areaPanel = new JPanel();
	private JLabel areaLabel = new JLabel("Area: ");
	private JTextField areaField = new JTextField(10);
	//area to display perimeter
	private JPanel perimeterPanel = new JPanel();
	private JLabel perimeterLabel = new JLabel("Perimeter: ");
	private JTextField perimeterField = new JTextField(10);
	//button to draw the shape and area of drawing
	private JPanel drawButtonPanel = new JPanel();
	private JButton drawButton = new JButton ("Draw");
	private DrawArea drawArea;
	//to get a layout that is one row, two columns
	GridLayout rectLayout = new GridLayout(1,2);
	GridLayout barLayout	= new GridLayout(1,1);
	NumberFormat numberForm;



	public CirclePanel(){
		//vertical layout
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//radius panel set up
		radiusPanel.setLayout(rectLayout);
		radiusPanel.add(radiusLabel);
		radiusPanel.add(radiusField);
		add(radiusPanel);
		//button panel set up for area and perimeter
		buttonPanel.setLayout(rectLayout);
		buttonPanel.add(areaButton);
		buttonPanel.add(perimeterButton);
		//create listeners and register buttons to listeners
		ActionListener calcArea = new CalcAreaAction();
		areaButton.addActionListener(calcArea);
		ActionListener calcPerimeter = new CalcPerimeterAction();
		perimeterButton.addActionListener(calcPerimeter);
		add(buttonPanel);
		//set up panel to communicate area calculation
		areaPanel.setLayout(rectLayout);
		areaPanel.add(areaLabel);
		areaPanel.add(areaField);
		areaField.setEditable(false);
		add(areaPanel);
		//set up panel to communicate perimeter calculation
		perimeterPanel.setLayout(rectLayout);
		perimeterPanel.add(perimeterLabel);
		perimeterPanel.add(perimeterField);
		perimeterField.setEditable(false);
		add(perimeterPanel);
		//set up panel for draw button
		drawButtonPanel.setLayout(barLayout);
		drawButtonPanel.add(drawButton);
		add(drawButtonPanel);
		//create listener and register button to listener
		ActionListener drawCircle = new DrawCircleAction();
		drawButton.addActionListener(drawCircle);
		//for error checking
		numberForm =NumberFormat.getNumberInstance();
		//for display purposes
		numberForm.setMaximumFractionDigits(2);

	}
	/**
	 * Verifies that radius in numeric - if not it asks for numeric input
	 * @param r
	 * @return
	 */
	public boolean checkValidInput(String r){
		boolean incorrect = true;
		while(incorrect) {
			if (!(r.trim().equals(""))) { //did the user give us something
				try {//good input
					Double.parseDouble(r); //see if it is numeric
					incorrect = false;
				} catch (NumberFormatException nfe) { //user gave us non numeric input
					r = JOptionPane.showInputDialog("Invalid input. Please enter your numeric radius: ");
					radiusField.setText(r);
				}

			} else { //no user input at all
				r = JOptionPane.showInputDialog("Invalid input. Please enter your numeric radius: ");
				radiusField.setText(r);
			}
		}
		return incorrect;
		
	}

	


	/**
	 * 
	 * If radius is numeric, calculate and display area
	 *
	 */
	private class CalcAreaAction implements ActionListener {
		public void actionPerformed(ActionEvent event){
			String r = radiusField.getText();//the users input about radius
			if(!(checkValidInput(r))){
				r = radiusField.getText(); // grab content again if invalid
				double rad = Double.parseDouble(r);// convert String to double
				double a = Math.PI * Math.pow(rad,2);//pi * r^2
				areaField.setText(numberForm.format(a));//display the area in the area field

			}

		}

	}
	/**
	 * 
	 * If radius is numeric, calculate and display perimeter
	 *
	 */
	private class CalcPerimeterAction implements ActionListener
	{
		public void actionPerformed(ActionEvent event){
			String r = radiusField.getText();//the users input about radius
			if(!(checkValidInput(r))){
				r = radiusField.getText(); // grab content again if invalid
				double rad = Double.parseDouble(r);// convert String to double
				double p = 2 * Math.PI * rad;//2pir
				perimeterField.setText(numberForm.format(p));//display the perimeter in the perimeter field

			}


		}
	}
	/**
	 * If numeric input it will create a drawArea to draw a circle
	 * @author AMTHAUHA
	 *
	 */
	private class DrawCircleAction implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String r = radiusField.getText();
			if(!(checkValidInput(r))){
				int rad = (int)Double.parseDouble(r); //converting String into a double then casting to an int
				drawButton.setEnabled(false); //only draw once with correct input
				drawArea = new DrawArea("Circle", rad);
				drawArea.setPreferredSize(new Dimension(500, 500));
				revalidate();
				repaint();
				add(drawArea);

			}
			

		}
		
	}

}
