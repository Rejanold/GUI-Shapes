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
 */
public class Rectangle extends JPanel {
    //to collect the length
    private JPanel lengthPanel = new JPanel();
    private JLabel lengthLabel = new JLabel("Enter Rectangle Length: ");
    private JTextField lengthField = new JTextField(10);
    //to collect information about the width
    private JPanel widthPanel = new JPanel();
    private JLabel widthLabel = new JLabel("Enter Rectangle Width: ");
    private JTextField widthField = new JTextField(10);
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
    private JButton drawButton = new JButton("Draw");
    private DrawArea drawArea;
    //to get a layout that is one row, two columns
    GridLayout rectLayout = new GridLayout(1, 2);
    GridLayout barLayout = new GridLayout(1, 1);
    NumberFormat numberForm;


    public Rectangle() {
        //vertical layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //radius panel set up
        widthPanel.setLayout(rectLayout);
        widthPanel.add(widthLabel);
        widthPanel.add(widthField);
        add(widthPanel);

        lengthPanel.setLayout(rectLayout);
        lengthPanel.add(lengthLabel);
        lengthPanel.add(lengthField);
        add(lengthPanel);
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
        ActionListener drawRectangle = new DrawRectangleAction();
        drawButton.addActionListener(drawRectangle);
        //for error checking
        numberForm = NumberFormat.getNumberInstance();
        //for display purposes
        numberForm.setMaximumFractionDigits(2);

    }

    /**
     * Verifies that radius in numeric - if not it asks for numeric input
     *
     * @param w
     * @return
     */
    public boolean checkValidInput(String w) {
        boolean incorrect = true;
        while (incorrect) {
            if (!(w.trim().equals(""))) { //did the user give us something
                try {//good input
                    Double.parseDouble(w); //see if it is numeric
                    incorrect = false;
                } catch (NumberFormatException nfe) { //user gave us non numeric input
                    w = JOptionPane.showInputDialog("Invalid input. Please enter your numeric width: ");
                    widthField.setText(w);
                }

            } else { //no user input at all
                w = JOptionPane.showInputDialog("Invalid input. Please enter your numeric width: ");
                widthField.setText(w);
            }
        }
        return incorrect;

    }

    public boolean checkValidInputL(String l) {
        boolean incorrect = true;
        while (incorrect) {
            if (!(l.trim().equals(""))) { //did the user give us something
                try {//good input
                    Double.parseDouble(l); //see if it is numeric
                    incorrect = false;
                } catch (NumberFormatException nfe) { //user gave us non numeric input
                    l = JOptionPane.showInputDialog("Invalid input. Please enter your numeric length: ");
                    lengthField.setText(l);
                }

            } else { //no user input at all
                l = JOptionPane.showInputDialog("Invalid input. Please enter your numeric length: ");
                lengthField.setText(l);
            }
        }
        return incorrect;

    }


    /**
     * If radius is numeric, calculate and display area
     */
    private class CalcAreaAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("want area");
            String w = widthField.getText();//the users input about radius
            String l = lengthField.getText();//Checks if length is valid input
            if (!(checkValidInput(w))) {
                System.out.println("good input");
                w = widthField.getText();// grab content again if invalid
            }
            if (!(checkValidInputL(l))) {
                l = lengthField.getText();
            }
            double wid = Double.parseDouble(w);// convert String to double
            double leng = Double.parseDouble(l);
            double a = leng * wid;// leng* width
            areaField.setText(numberForm.format(a));//display the area in the area field


        }

    }

    /**
     * If radius is numeric, calculate and display perimeter
     */
    private class CalcPerimeterAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String w = widthField.getText();//the users input about radius
            String l = lengthField.getText();//Checks if length is valid input
            if (!(checkValidInput(l))) {
                l = lengthField.getText();
                if (!(checkValidInputL(w))) {
                    w = widthField.getText();// grab content again if invalid
                }

                double wid = Double.parseDouble(w);// convert String to double
                double leng = Double.parseDouble(l);
                double p = 2 * (leng + wid);// leng* width
                perimeterField.setText(numberForm.format(p));//display the area in the area field


            }
        }
    }

    /**
     * If numeric input it will create a drawArea to draw a circle
     *
     * @author AMTHAUHA
     */
    private class DrawRectangleAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String l = lengthField.getText();
            String w = widthField.getText();
            if (!(checkValidInput(l))) {
                l = lengthField.getText();
                if (!(checkValidInputL(w))) {
                    w = widthField.getText();// grab content again if invalid
                }
                int leng = (int) Double.parseDouble(l); //converting String into a double then casting to an int
                int wid = (int) Double.parseDouble(w);
                drawButton.setEnabled(false); //only draw once with correct input
                drawArea = new DrawArea("Rectangle", leng, wid);
                drawArea.setPreferredSize(new Dimension(500, 500));
                revalidate();
                repaint();
                add(drawArea);
            }
        }
    }


}
