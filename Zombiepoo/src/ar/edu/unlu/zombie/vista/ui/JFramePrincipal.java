package ar.edu.unlu.zombie.vista.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class JFramePrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
    private final JPanel contenedor;
    private final CardLayout cardLayout;

	public JFramePrincipal() {
		super("Zombies");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        setContentPane(contenedor);       
	}
	
    public void showPanel(JPanel panel) {
        this.getContentPane().removeAll();
        this.getContentPane().add(panel);
        this.revalidate();
        this.repaint();
    }

    public void showFrame() {
        setVisible(true);
    }

}
