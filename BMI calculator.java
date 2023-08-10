import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculatorApp extends JFrame implements ActionListener {
    private JTextField weightField;
    private JTextField heightField;
    private JComboBox<String> heightUnitCombo;
    private JLabel resultLabel;

    public BMICalculatorApp() {
        setTitle("BMI Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        panel.add(weightField);

        panel.add(new JLabel("Height:"));
        heightField = new JTextField();
        panel.add(heightField);

        heightUnitCombo = new JComboBox<>(new String[]{"cm", "feet", "m"});
        panel.add(heightUnitCombo);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        panel.add(calculateButton);

        panel.add(new JLabel("Result:"));
        resultLabel = new JLabel();
        panel.add(resultLabel);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Calculate")) {
            try {
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                String heightUnit = (String) heightUnitCombo.getSelectedItem();

                if (heightUnit.equals("feet")) {
                    height *= 30.48; // Convert feet to centimeters
                } else if (heightUnit.equals("m")) {
                    height *= 100; // Convert meters to centimeters
                }

                double bmi = calculateBMI(weight, height / 100); // Convert height to meters
                String category = getBMICategory(bmi);

                resultLabel.setText(String.format("BMI: %.2f (%s)", bmi, category));
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input");
            }
        }
    }

    private double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    private String getBMICategory(double bmi) {
        if (bmi < 16) {
            return "Severely Underweight";
        } else if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BMICalculatorApp().setVisible(true));
    }
}