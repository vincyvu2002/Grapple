/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

/**
 *
 * @author vincent
 */
public class MovingCharacter extends javax.swing.JFrame {
	
	private final Image charImg;
	private final Character ourCharacter;
	private final PaintCanvas ourCanvas;
	private final int testCount = 20;
	private final int speed = 5;
	private int speedJump = 15;
	private boolean movingLeft;
	private boolean movingRight;
	private boolean jumping;
	private boolean Djump;
	private final Object keyPressWait = new Object();
	
	/**
	 * Creates new form MovingCharacter
	 *
	 * @throws java.io.IOException
	 */
	@SuppressWarnings({"OverridableMethodCallInConstructor", "CallToThreadStartDuringObjectConstruction"})
	public MovingCharacter() throws IOException {
		charImg = ImageIO.read(getClass().getClassLoader().getResource("images/BlackCircle.png"));
		ourCharacter = new Character();
		ourCharacter.setY(400);
		ourCanvas = new PaintCanvas(ourCharacter);
		initComponents();
		add(ourCanvas, BorderLayout.CENTER);
		ourCanvas.setPreferredSize(new Dimension(700, 500));
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					movingLeft = true;
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					movingRight = true;
				} else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					if(!jumping) {
						jumping = true;
						speedJump = 20;
						ourCharacter.setJumping(Character.Jumping.UP);
					} else if(!Djump) {
						speedJump = 20;
						ourCharacter.setJumping(Character.Jumping.UP);
						Djump = true;
					}
				}
				synchronized(keyPressWait) {
					keyPressWait.notify();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A) {
					movingLeft = false;
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					movingRight = false;
				}
				synchronized(keyPressWait) {
					keyPressWait.notify();
				}
			}
		});
		pack();
		
		new Thread(new Runnable() {
			@Override
			@SuppressWarnings("SleepWhileInLoop")
			public void run() {
				boolean idle;
				while (true) {
					try {
						if (movingLeft) {
							idle = false;
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									int newX = ourCharacter.getX() - speed;
									if (newX < 0) {
										newX = 0;
									}
									ourCharacter.setX(newX);
									ourCharacter.setMoving(Character.Moving.LEFT);
									repaint();
								}
							});
						} else if (movingRight) {
							idle = false;
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									int newX = ourCharacter.getX() + speed;
									int rightEdge = (700 - (ourCharacter.getDimension().width));
									if (newX > rightEdge) {
										newX = rightEdge;
									}
									ourCharacter.setX(newX);
									ourCharacter.setMoving(Character.Moving.RIGHT);
									repaint();
								}
							});
						} else {
							idle = true;
							ourCharacter.setMoving(Character.Moving.NONE);
						}
						
						if (jumping) {
							idle = false;
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									int newY = ourCharacter.getY() - speedJump;
									if(newY >= 400) {
										jumping = false;
										Djump = false;
										newY = 400;
										ourCharacter.setJumping(Character.Jumping.NONE);
									} else {
										speedJump -= 2;
										if(speedJump < 0) {
											ourCharacter.setJumping(Character.Jumping.DOWN);
										}
									}
									ourCharacter.setY(newY);
									repaint();
								}
							});
						} 
						if(idle) {
							synchronized(keyPressWait) {
								repaint();
								keyPressWait.wait();
							}
						} else {
							Thread.sleep(50);
						}
					} catch (InterruptedException ex) {
						Logger.getLogger(MovingCharacter.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}).start();

		/*
		// This connects key presses to actions
		InputMap imap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "moveLeft");
		imap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "moveRight");
		getRootPane().getActionMap().put("moveLeft", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int newX = ourCharacter.getX() - speed;
				if (newX < 0) {
					newX = 0;
				}
				ourCharacter.setX(newX);
				repaint();
			}
		});
		getRootPane().getActionMap().put("moveRight", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int newX = ourCharacter.getX() + speed;
				int rightEdge = (700 - (ourCharacter.getDimension().width));
				if (newX > rightEdge) {
					newX = rightEdge;
				}
				ourCharacter.setX(newX);
				repaint();
			}
		});
		 */
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MovingCharacter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MovingCharacter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MovingCharacter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MovingCharacter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			try {
				new MovingCharacter().setVisible(true);
			} catch (IOException ex) {
				Logger.getLogger(MovingCharacter.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
