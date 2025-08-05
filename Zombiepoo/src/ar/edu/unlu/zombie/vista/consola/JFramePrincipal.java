package ar.edu.unlu.zombie.vista.consola;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JFramePrincipal extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private final JTextArea consoleArea;
    private final JTextField inputField;
    private final JButton enterButton;

    public JFramePrincipal() {
        super("Zombie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Área de salida de texto
        consoleArea = new JTextArea();
        consoleArea.setForeground(new Color(255, 255, 255));
        consoleArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        consoleArea.setEnabled(false);
        consoleArea.setBackground(new Color(0, 0, 0));
        consoleArea.setEditable(false);
        consoleArea.setLineWrap(true);
        consoleArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(
            consoleArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        // Panel de entrada con campo de texto y botón
        inputField  = new JTextField();
        enterButton = new JButton("Enter");
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(inputField,  BorderLayout.CENTER);
        inputPanel.add(enterButton, BorderLayout.EAST);

        // Layout principal
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane,    BorderLayout.CENTER);
        getContentPane().add(inputPanel,    BorderLayout.SOUTH);

        setVisible(true);
    }
    
    public void clearConsole() {
        consoleArea.setText("");
    }

    public void appendLine(String line) {
        consoleArea.append(line + "\n");
        consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
    }

    public void setEnterAction(ActionListener listener) {
        
        for (ActionListener old : inputField.getActionListeners()) {
            inputField.removeActionListener(old);
        }
        
        for (ActionListener old : enterButton.getActionListeners()) {
            enterButton.removeActionListener(old);
        }
        
        inputField.addActionListener(listener);
        enterButton.addActionListener(listener);
    }
    
    public String getInputText() {
        return inputField.getText();
    }

    public void clearInput() {
        inputField.setText("");
    }
    
    public void setEditabledInput(boolean editabled) {
        inputField.setEditable(editabled);
    } 
    
    public void showFrame() {
        setVisible(true);
    }
    
}
