/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author vincent
 */
public class Character {
	public static enum Jumping {
		UP,
		DOWN,
		NONE
	}
	public static enum Moving {
		LEFT,
		RIGHT,
		NONE
	}
	private final Image[] character;
	private int x;
	private int y;
	int index;
	int jindex;
	private Moving moving;
	private Jumping jumping = Jumping.NONE;
	public Character() throws IOException {
		this.character = new Image[24];
		Image bigImage = ImageIO.read(getClass().getClassLoader().getResource("images/MCSpriteSheet1.png"));
		int idx = 0;
		for(int row = 0; row < 2; ++row) {
			for(int col = 0; col < 6; ++col) {
				BufferedImage bi = new BufferedImage(192, 256, BufferedImage.TYPE_4BYTE_ABGR);
				bi.getGraphics().drawImage(bigImage, -(col * 192), -(row * 256), null);
				character[idx++] = bi;
			}
		}
		AffineTransform xx = AffineTransform.getScaleInstance(-1.0, 1.0);
		xx.translate(-192, 0);
		AffineTransformOp x = new AffineTransformOp(xx, null);
		for(int row = 0; row < 2; ++row) {
			for(int col = 0; col < 6; ++col) {
				BufferedImage bi = new BufferedImage(192, 256, BufferedImage.TYPE_4BYTE_ABGR);
				bi.getGraphics().drawImage(bigImage, -(col * 192), -(row * 256), null);
				character[idx++] = x.filter(bi, null);
			}
		}
	}
	public void setX(int value) {
		this.x = value;
	}
	public void setY(int value) {
		this.y = value;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return this.y;
	}
	
	private Moving wasMoving = Moving.NONE;
	
	public Image getImage() {
		if(getMoving() == Moving.RIGHT) {
			++index;
			if(index > 11) {
				index = 6;
			} else if(index < 6) {
				index = 6;
			}
		} else if(getMoving() == Moving.LEFT) {
			++index;
			if(index > 23) {
				index = 18;
			} else if(index < 18) {
				index = 18;
			}
		}
		if(getJumping() == Jumping.UP) {
			index = jindex++;
			if(jindex > 8) {
				jindex = 8;
			}
			if(index > 4) {
				index = 4;
			}
			if(wasMoving == Moving.LEFT) {
				index += 12;
			}
		} else if(getJumping() == Jumping.DOWN) {
			index = jindex--;
			if(jindex < 2) {
				jindex = 2;
			} else if(index > 4) {
				index = 4;
			}
			if (wasMoving == Moving.LEFT) {
				index += 12;
			}
		}
		
		else if (moving == Moving.NONE) {
			index = 0;
			if (wasMoving == Moving.LEFT) {
				index += 12;
			}
		}
		return character[index];
	}
	public Dimension getDimension() {
		return new Dimension(48, 60);
	}
	public Jumping getJumping() {
		return jumping;
	}
	public void setJumping(Jumping value) {
		if(jumping != value) {
			jumping = value;
			if(value == Jumping.UP) {
				jindex = 2;
			} else if(value == Jumping.DOWN) {
				System.out.println("learning.Character.setJumping(DOWN)");
				jindex = 8;
			}
		}
	}
	public Moving getMoving() {
		return moving;
	}
	public void setMoving(Moving value) {
		moving = value;
		if (moving!= Moving.NONE) {
			wasMoving = moving;
		}
	}
}
