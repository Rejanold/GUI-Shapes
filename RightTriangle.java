import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
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
public class RightTriangle extends JPanel {
    //to collect the length
    private JPanel heightPanel = new JPanel();
    private JLabel heightLabel = new JLabel("Enter Triangle Height: ");
    private JTextField heightField = new JTextField(10);
    //to collect information about the width
    private JPanel basePanel = new JPanel();
    private JLabel baseLabel = new JLabel("Enter Triangle Base: ");
    private JTextField baseField = new JTextField(10);
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



    public RightTriangle(){
        //vertical layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //radius panel set up
        basePanel.setLayout(rectLayout);
        basePanel.add(baseLabel);
        basePanel.add(baseField);
        add(basePanel);

        heightPanel.setLayout(rectLayout);
        heightPanel.add(heightLabel);
        heightPanel.add(heightField);
        add(heightPanel);
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
        ActionListener drawRightTriangle = new DrawRightTriangleAction();
        drawButton.addActionListener(drawRightTriangle);
        //for error checking
        numberForm =NumberFormat.getNumberInstance();
        //for display purposes
        numberForm.setMaximumFractionDigits(2);

    }
    /**
     * Verifies that radius in numeric - if not it asks for numeric input
     * @param w
     * @return
     */
    public boolean checkValidInput(String w){
        boolean incorrect = true;
        while(incorrect) {
            if (!(w.trim().equals(""))) { //did the user give us something
                try {//good input
                    Double.parseDouble(w); //see if it is numeric
                    incorrect = false;
                } catch (NumberFormatException nfe) { //user gave us non numeric input
                    w = JOptionPane.showInputDialog("Invalid input. Please enter your numeric width: ");
                    baseField.setText(w);
                }

            } else { //no user input at all
                w = JOptionPane.showInputDialog("Invalid input. Please enter your numeric width: ");
                baseField.setText(w);
            }
        }
        return incorrect;

    }
    public boolean checkValidInputL(String l){
        boolean incorrect = true;
        while(incorrect) {
            if (!(l.trim().equals(""))) { //did the user give us something
                try {//good input
                    Double.parseDouble(l); //see if it is numeric
                    incorrect = false;
                } catch (NumberFormatException nfe) { //user gave us non numeric input
                    l = JOptionPane.showInputDialog("Invalid input. Please enter your numeric length: ");
                    heightField.setText(l);
                }

            } else { //no user input at all
                l = JOptionPane.showInputDialog("Invalid input. Please enter your numeric length: ");
                heightField.setText(l);
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
            System.out.println("want area");
            String b = baseField.getText();//the users input about radius
            String h = heightField.getText();//Checks if length is valid input
            if(!(checkValidInput(b))) {
                System.out.println("good input");
                b = baseField.getText();// grab content again if invalid
            }
            if(!(checkValidInputL(h))){
                h = heightField.getText();
            }
            double base = Double.parseDouble(b);// convert String to double
            double height = Double.parseDouble(h);
            double a = height*(base/2);// leng* width
            areaField.setText(numberForm.format(a));//display the area in the area field



        }

    }
    /**
     *
     * If radius is numeric, calculate and display perimeter
     *
     */
    private class CalcPerimeterAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String b = baseField.getText();//the users input about radius
            String h = heightField.getText();//Checks if length is valid input
            if (!(checkValidInput(h))) {
                h = heightField.getText();
                if(!(checkValidInputL(b))){
                    b = baseField.getText();// grab content again if invalid
                }

                double base = Double.parseDouble(b);// convert String to double
                double height = Double.parseDouble(h);
                double hypot = Math.sqrt(height*height + base*base);
                double p = height + base + hypot;// leng* width
                perimeterField.setText(numberForm.format(p));//display the area in the area field


            }
        }
    }

    /**
     * If numeric input it will create a drawArea to draw a circle
     *
     * @author AMTHAUHA
     */
    private class DrawRightTriangleAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String h = heightField.getText();
            String b = baseField.getText();
            int height = (int) Double.parseDouble(h); //converting String into a double then casting to an int
            int base = (int) Double.parseDouble(b);
            drawButton.setEnabled(false); //only draw once with correct input
            drawArea = new DrawArea("Right Triangle", base, height);
            drawArea.setPreferredSize(new Dimension(500, 500));
            revalidate();
            repaint();
            add(drawArea);
        }
    }








}
