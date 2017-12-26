/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author vincent
 */
public class PaintCanvas extends JPanel {
	Character character;
	public PaintCanvas(Character character) {
		super();
		this.character = character;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(g instanceof Graphics2D) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.drawImage(character.getImage(), character.getX(), character.getY(), character.getDimension().width, character.getDimension().height, null);
		}
	}
	
}
