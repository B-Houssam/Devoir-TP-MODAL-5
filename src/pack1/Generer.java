package pack1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Generer extends JPanel{
	
	/**
	 * author Houssam Bousri
	 */
	private static final long serialVersionUID = 1L;
	
	List<String> classes = new ArrayList<String>();
	
	public Generer(List<String> lc) {
		// TODO Auto-generated constructor stub
		this.classes = lc;
	}
	
	public Generer() {
		// TODO Auto-generated constructor stub
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    
		Graphics2D dessin = (Graphics2D) g; // La classe Graphics2D est une classe dérivée de Graphics
		dessin.setColor(Color.red); // Changement de la couleur en rouge
		dessin.fillRect(240, 210, 10, 10); // Dessin d'un rectange plein avec couleur rouge en position (x,y, longX, longY)
		dessin.setColor(Color.black); // Changement de la couleur en noir
		dessin.drawString(classes.get(0), 215, 205); // Ecriture de la chaine Add dans la position (x,y)
		dessin.setColor(Color.green); // Changement de la couleur en vert
		dessin.fillRect(250, 195, 140, 90);
		dessin.setColor(Color.black); // Changement de la couleur en noir
		dessin.drawLine(240, 212, 20, 195); // Dessin d'un rectangle position (x1,y1) à (x2,y2)
		dessin.drawLine(240, 212, 20, 225);
		dessin.drawString("Formule.java", 270, 220);
		dessin.drawRect(250, 300, 150, 100); // Rectangle Vide
		dessin.drawOval(280, 420, 120, 100); // Oval vide
	}

}
