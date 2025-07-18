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
	
    public void addPanel(String name, JPanel panel) {
        contenedor.add(panel, name);
    }

    public void showPanel(String name) {
        cardLayout.show(contenedor, name);
        contenedor.revalidate();
        contenedor.repaint();
    }

    public void showFrame() {
        setVisible(true);
    }

}
