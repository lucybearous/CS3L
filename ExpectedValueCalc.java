package GR7_Discrete_pkg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ExpectedValueCalc extends JFrame {
// Colors
private static final Color BACKGROUND = new Color(10, 14, 35);
private static final Color PANEL = new Color(25, 30, 60);
private static final Color FIELD = new Color(16, 21, 45);
private static final Color PURPLE = new Color(145, 85, 255);
private static final Color LIGHT_PURPLE = new Color(205, 180, 255);
private static final Color WHITE = new Color(240, 238, 250);
private static final Color GREEN = new Color(80, 220, 160);
private static final Color WARNING = new Color(255, 190, 90);
// Input components
private JTextField outcomeCountField;
private JPanel outcomePanel;
// Result components
private JLabel expectedValueLabel;
private JLabel totalProbabilityLabel;
private JLabel statusLabel;
private JTextArea calculationArea;
// Stores the input fields
private ArrayList<JTextField> outcomeFields = new ArrayList<JTextField>();
private ArrayList<JTextField> probabilityFields = new ArrayList<JTextField>();
// ==========================================
// CONSTRUCTOR
// ==========================================
public ExpectedValueCalc() {
setTitle("Mathematical Expectation Calculator");
setSize(1050, 750);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
createInterface();
}
// ==========================================
// CREATE INTERFACE
// ==========================================
private void createInterface() {
JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
mainPanel.setBackground(BACKGROUND);
mainPanel.setBorder(new EmptyBorder(25, 30, 25, 30));
// ======================================
// HEADER
// ======================================
JPanel header = new JPanel();
header.setBackground(BACKGROUND);
header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
JLabel title = new JLabel("Mathematical Expectation of a Discrete Random Variable");
title.setForeground(LIGHT_PURPLE);
title.setFont(new Font("SansSerif", Font.BOLD, 27));
JLabel subtitle = new JLabel("Enter the outcomes and their probabilities to compute the expected value.");
subtitle.setForeground(WHITE);
subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
header.add(title);
header.add(Box.createVerticalStrut(8));
header.add(subtitle);
mainPanel.add(header, BorderLayout.NORTH);
// ======================================
// CENTER
// ======================================
JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
centerPanel.setBackground(BACKGROUND);
// ======================================
// INPUT PANEL
// ======================================
JPanel inputPanel = createPanel();
inputPanel.setLayout(new BorderLayout(10, 15));
JLabel inputTitle = createSectionTitle("Input");
inputPanel.add(inputTitle, BorderLayout.NORTH);
JPanel inputContent = new JPanel(new BorderLayout(10, 15));
inputContent.setBackground(PANEL);
// Number of outcomes
JPanel numberPanel = new JPanel(new BorderLayout(8, 8));
numberPanel.setBackground(PANEL);
JLabel numberLabel = new JLabel("Number of outcomes (n):");
numberLabel.setForeground(WHITE);
numberLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
outcomeCountField = createTextField("3");
numberPanel.add(numberLabel, BorderLayout.NORTH);
numberPanel.add(outcomeCountField, BorderLayout.CENTER);
inputContent.add(numberPanel, BorderLayout.NORTH);
// ======================================
// OUTCOME TABLE
// ======================================
outcomePanel = new JPanel();
outcomePanel.setBackground(PANEL);
outcomePanel.setLayout(new BoxLayout(outcomePanel,
BoxLayout.Y_AXIS));
JScrollPane scrollPane = new JScrollPane(outcomePanel);
scrollPane.setBorder(null);
scrollPane.setBackground(PANEL);
scrollPane.getViewport().setBackground(PANEL);
scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
 @Override
 protected void configureScrollBarColors() {
 this.thumbColor = PURPLE;
 this.trackColor = FIELD;
 }
});
scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
inputContent.add(scrollPane, BorderLayout.CENTER);
// ======================================
// BUTTONS
// ======================================
JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 12, 0));
buttonPanel.setBackground(PANEL);
JButton calculateButton = createButton("Calculate");
getRootPane().setDefaultButton(calculateButton);
JButton resetButton = createButton("Reset");
calculateButton.addMouseListener(new java.awt.event.MouseAdapter() {
 @Override
 public void mouseEntered(java.awt.event.MouseEvent e) {
 calculateButton.setBackground(LIGHT_PURPLE);
 calculateButton.setForeground(BACKGROUND);
 }
 @Override
 public void mouseExited(java.awt.event.MouseEvent e) {
 calculateButton.setBackground(PURPLE);
 calculateButton.setForeground(WHITE);
 }
});
resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
 @Override
 public void mouseEntered(java.awt.event.MouseEvent e) {
 resetButton.setBackground(LIGHT_PURPLE);
 resetButton.setForeground(BACKGROUND);
 }
 @Override
 public void mouseExited(java.awt.event.MouseEvent e) {
 resetButton.setBackground(PURPLE);
 resetButton.setForeground(WHITE);
 }
});
calculateButton.addActionListener(e -> calculateExpectedValue());
resetButton.addActionListener(e -> resetFields());
buttonPanel.add(calculateButton);
buttonPanel.add(resetButton);
inputContent.add(buttonPanel, BorderLayout.SOUTH);
inputPanel.add(inputContent, BorderLayout.CENTER);
// ======================================
// RESULT PANEL
// ======================================
JPanel resultPanel = createPanel();
resultPanel.setLayout(new BorderLayout(10, 15));
JLabel resultTitle = createSectionTitle("Result");
resultPanel.add(resultTitle, BorderLayout.NORTH);
JPanel resultContent = new JPanel();
resultContent.setBackground(PANEL);
resultContent.setLayout(new BoxLayout(resultContent,
BoxLayout.Y_AXIS));
// ======================================
// EXPECTED VALUE BOX
// ======================================
JPanel valueBox = new JPanel();
valueBox.setBackground(new Color(43, 37, 82));
valueBox.setLayout(new BoxLayout(valueBox, BoxLayout.Y_AXIS));
valueBox.setBorder(BorderFactory.createCompoundBorder(new
LineBorder(PURPLE, 2, true),
new EmptyBorder(15, 20, 15, 20)));
JLabel valueTitle = new JLabel("Expected Value E(X)");
valueTitle.setForeground(WHITE);
valueTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
expectedValueLabel = new JLabel("0.0000");
expectedValueLabel.setForeground(LIGHT_PURPLE);
expectedValueLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
valueBox.add(valueTitle);
valueBox.add(Box.createVerticalStrut(5));
valueBox.add(expectedValueLabel);
resultContent.add(valueBox);
resultContent.add(Box.createVerticalStrut(20));
// ======================================
// CALCULATION STEPS
// ======================================
JLabel calculationTitle = new JLabel("Calculation Steps");
calculationTitle.setForeground(LIGHT_PURPLE);
calculationTitle.setFont(new Font("SansSerif", Font.BOLD, 19));
resultContent.add(calculationTitle);
resultContent.add(Box.createVerticalStrut(8));
JLabel formulaLabel = new JLabel("E(X) = Σ xP(x)");
formulaLabel.setForeground(LIGHT_PURPLE);
formulaLabel.setFont(new Font("Serif", Font.ITALIC, 16));
formulaLabel.setHorizontalAlignment(SwingConstants.CENTER);
formulaLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE,
30));
resultContent.add(formulaLabel);
resultContent.add(Box.createVerticalStrut(8));
calculationArea = new JTextArea();
calculationArea.setEditable(false);
calculationArea.setBackground(PANEL);
calculationArea.setForeground(WHITE);
calculationArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
calculationArea.setText(" ");
calculationArea.setBorder(new EmptyBorder(5, 5, 5, 5));
JScrollPane calculationScrollPane = new JScrollPane(calculationArea);
calculationScrollPane.setBorder(null);
calculationScrollPane.setBackground(PANEL);
calculationScrollPane.getViewport().setBackground(PANEL);
calculationScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI()
{
 @Override
 protected void configureScrollBarColors() {
 this.thumbColor = PURPLE;
 this.trackColor = FIELD;
 }
});
calculationScrollPane.getVerticalScrollBar().setPreferredSize(new
Dimension(10, 0));
resultContent.add(calculationScrollPane);
resultContent.add(Box.createVerticalStrut(15));
// ======================================
// TOTAL PROBABILITY
// ======================================
totalProbabilityLabel = new JLabel("Total Probability 0.0000");
totalProbabilityLabel.setForeground(WHITE);
totalProbabilityLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
totalProbabilityLabel.setHorizontalAlignment(SwingConstants.CENTER);
totalProbabilityLabel.setMaximumSize(new
Dimension(Integer.MAX_VALUE, 30));
resultContent.add(totalProbabilityLabel);
resultContent.add(Box.createVerticalStrut(12));
// ======================================
// STATUS
// ======================================
statusLabel = new JLabel("Ready to calculate");
statusLabel.setForeground(LIGHT_PURPLE);
statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
statusLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
statusLabel.setBorder(BorderFactory.createCompoundBorder(
 new LineBorder(new Color(70, 80, 130), 1, true),
 new EmptyBorder(12, 12, 12, 12)));
resultContent.add(statusLabel);
resultPanel.add(resultContent, BorderLayout.CENTER);
// ======================================
// ADD PANELS
// ======================================
centerPanel.add(inputPanel);
centerPanel.add(resultPanel);
mainPanel.add(centerPanel, BorderLayout.CENTER);
setContentPane(mainPanel);
// Start with 3 outcomes
generateOutcomeFields(3);
// Press Enter to update number of outcomes
outcomeCountField.addActionListener(e -> updateOutcomeRows());
}
// ==========================================
// GENERATE INPUT ROWS
// ==========================================
private void generateOutcomeFields(int count) {
outcomePanel.removeAll();
outcomeFields.clear();
probabilityFields.clear();
// Table header
JPanel header = new JPanel(new GridLayout(1, 3));
header.setBackground(new Color(35, 40, 75));
header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
JLabel blank = createTableLabel("");
JLabel outcomeHeader = createTableLabel("Outcome (x)");
JLabel probabilityHeader = createTableLabel("Probability (p)");
header.add(blank);
header.add(outcomeHeader);
header.add(probabilityHeader);
outcomePanel.add(header);
// Create rows
for (int i = 0; i < count; i++) {
JPanel row = new JPanel(new GridLayout(1, 3, 10, 0));
row.setBackground(PANEL);
row.setBorder(new EmptyBorder(7, 5, 7, 5));
JLabel number = new JLabel(String.valueOf(i + 1));
number.setHorizontalAlignment(SwingConstants.CENTER);
number.setForeground(WHITE);
number.setFont(new Font("SansSerif", Font.BOLD, 15));
JTextField outcomeField = createTextField("");
JTextField probabilityField = createTextField("");
outcomeField.setHorizontalAlignment(SwingConstants.CENTER);
probabilityField.setHorizontalAlignment(SwingConstants.CENTER);
outcomeFields.add(outcomeField);
probabilityFields.add(probabilityField);
final int index = i;
outcomeField.addKeyListener(new KeyAdapter() {
 @Override
 public void keyPressed(KeyEvent e) {
 if (e.getKeyCode() == KeyEvent.VK_ENTER ||
 e.getKeyCode() == KeyEvent.VK_RIGHT) {
 probabilityField.requestFocusInWindow();
 e.consume();
 } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
 if (index + 1 < outcomeFields.size()) {
 outcomeFields.get(index + 1).requestFocusInWindow();
 }
 e.consume();
 } else if (e.getKeyCode() == KeyEvent.VK_UP) {
 if (index - 1 >= 0) {
 outcomeFields.get(index - 1).requestFocusInWindow();
 }
 e.consume();
 }
 }
});
probabilityField.addKeyListener(new KeyAdapter() {
 @Override
 public void keyPressed(KeyEvent e) {
 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
 if (index + 1 < probabilityFields.size()) {
 outcomeFields.get(index + 1).requestFocusInWindow();
 } else {
 calculateExpectedValue();
 }
 e.consume();
 } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
 outcomeField.requestFocusInWindow();
 e.consume();
 } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
 if (index + 1 < probabilityFields.size()) {
 probabilityFields.get(index + 1).requestFocusInWindow();
 }
 e.consume();
 } else if (e.getKeyCode() == KeyEvent.VK_UP) {
 if (index - 1 >= 0) {
 probabilityFields.get(index - 1).requestFocusInWindow();
 }
 e.consume();
 }
 }
});
row.add(number);
row.add(outcomeField);
row.add(probabilityField);
outcomePanel.add(row);
}
outcomePanel.revalidate();
outcomePanel.repaint();
}
// ==========================================
// CALCULATE EXPECTED VALUE
// ==========================================
private void calculateExpectedValue() {
try {
double expectedValue = 0.0;
double totalProb = 0.0;
StringBuilder steps = new StringBuilder();
// ----------------------------------
// LOOP THROUGH EACH OUTCOME
// ----------------------------------
String[] numbers = {"❶", "❷", "❸", "❹", "❺",
 "❻", "❼", "❽", "❾", "❿"};
for (int i = 0; i < outcomeFields.size(); i++) {
double x =
Double.parseDouble(outcomeFields.get(i).getText().trim());
double p =
Double.parseDouble(probabilityFields.get(i).getText().trim());
// Probability must be 0 to 1
if (p < 0.0 || p > 1.0) {
JOptionPane.showMessageDialog(this, "Probability must be between 0 and 1.",
"Invalid Probability",
JOptionPane.WARNING_MESSAGE);
return;
}
// ----------------------------------
// MAIN FORMULA
// E(X) = SUM x * P(x)
// ----------------------------------
double product = x * p;
expectedValue += product;
totalProb += p;
// Show calculation step
steps.append(String.format("[%d] ", i + 1)
 + formatNumber(x) + " x " + formatNumber(p)
 + " = " + String.format("%.4f", product) +
"\n\n");
}
// ----------------------------------
// VALIDATE TOTAL PROBABILITY
// ----------------------------------
if (Math.abs(totalProb - 1.0) > 0.00001) {
JLabel warningMessage = new JLabel(
 "<html>Total probability must equal 1.<br>"
 + "Current total: " + String.format("%.4f",
totalProb)
 + "</html>");
JOptionPane.showMessageDialog(
 this,
 warningMessage,
 "Invalid Total Probability",
 JOptionPane.WARNING_MESSAGE
);
return;
}
// ----------------------------------
// DISPLAY SUM
// ----------------------------------
steps.append("------------------------------\n");
steps.append("Sum = " + String.format("%.4f",
expectedValue));
calculationArea.setText(steps.toString());
// ----------------------------------
// DISPLAY EXPECTED VALUE
// ----------------------------------
expectedValueLabel.setText(String.format("%.4f", expectedValue));
// ----------------------------------
// DISPLAY TOTAL PROBABILITY
// ----------------------------------
totalProbabilityLabel.setText("Total Probability: " +
String.format("%.4f", totalProb));
statusLabel.setText("✓ Probabilities sum to 1.");
statusLabel.setForeground(GREEN);
} catch (NumberFormatException e) {
JOptionPane.showMessageDialog(this, "Please enter valid numbers for all outcomes and probabilities.",
"Invalid Input", JOptionPane.ERROR_MESSAGE);
}
}
// ==========================================
// CHANGE NUMBER OF OUTCOMES
// ==========================================
private void updateOutcomeRows() {
try {
int n = Integer.parseInt(outcomeCountField.getText().trim());
if (n <= 0 || n > 20) {
JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 20.", "Invalid Number",
JOptionPane.WARNING_MESSAGE);
return;
}
generateOutcomeFields(n);
} catch (NumberFormatException e) {
JOptionPane.showMessageDialog(this, "Please enter a valid whole number.", "Invalid Input",
JOptionPane.WARNING_MESSAGE);
}
}
// ==========================================
// RESET
// ==========================================
private void resetFields() {
outcomeCountField.setText("3");
generateOutcomeFields(3);
expectedValueLabel.setText("0.0000");
totalProbabilityLabel.setText("Total Probability: 0.0000");
calculationArea.setText(" ");
statusLabel.setText("Ready to calculate");
statusLabel.setForeground(LIGHT_PURPLE);
}
// ==========================================
// CREATE PANEL
// ==========================================
private JPanel createPanel() {
JPanel panel = new JPanel();
panel.setBackground(PANEL);
panel.setBorder(BorderFactory.createCompoundBorder(new
LineBorder(new Color(65, 70, 130), 1, true),
new EmptyBorder(15, 15, 15, 15)));
return panel;
}
// ==========================================
// CREATE SECTION TITLE
// ==========================================
private JLabel createSectionTitle(String text) {
JLabel label = new JLabel(text);
label.setForeground(LIGHT_PURPLE);
label.setFont(new Font("SansSerif", Font.BOLD, 22));
return label;
}
// ==========================================
// CREATE TABLE LABEL
// ==========================================
private JLabel createTableLabel(String text) {
JLabel label = new JLabel(text);
label.setHorizontalAlignment(SwingConstants.CENTER);
label.setForeground(LIGHT_PURPLE);
label.setFont(new Font("SansSerif", Font.BOLD, 14));
return label;
}
// ==========================================
// CREATE TEXT FIELD
// ==========================================
private JTextField createTextField(String text) {
JTextField field = new JTextField(text);
field.setBackground(FIELD);
field.setForeground(WHITE);
field.setCaretColor(WHITE);
field.setFont(new Font("SansSerif", Font.PLAIN, 15));
field.setBorder(BorderFactory.createCompoundBorder(new
LineBorder(new Color(75, 80, 150), 1, true),
new EmptyBorder(8, 10, 8, 10)));
return field;
}
// ==========================================
// CREATE BUTTON
// ==========================================
private JButton createButton(String text) {
JButton button = new JButton(text);
button.setBackground(PURPLE);
button.setForeground(Color.WHITE);
button.setFont(new Font("SansSerif", Font.BOLD, 15));
button.setFocusPainted(false);
button.setBorder(new EmptyBorder(12, 15, 12, 15));
return button;
}
// ==========================================
// FORMAT NUMBERS
// ==========================================
private String formatNumber(double number) {
if (number == (long) number) {
return String.format("%d", (long) number);
}
return String.format("%.4f", number);
}
// ==========================================
// MAIN METHOD
// ==========================================
public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
@Override
public void run() {
ExpectedValueCalc app = new ExpectedValueCalc();
app.setVisible
(true
); }
}); }
}