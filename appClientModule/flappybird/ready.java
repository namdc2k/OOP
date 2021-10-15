package flappybird;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ready {
	private BufferedImage ready;
	private int x,y;
	
	public ready(){
		
		 try{
		 
		ready =ImageIO.read(new File("asset/ready.png"));
	 }
		 catch(IOException ex){}
		 x =100;
		 y =100;
	}
	public void Paint(Graphics2D g2){
 	   g2.drawImage(ready,x,y,null);   
	}
}