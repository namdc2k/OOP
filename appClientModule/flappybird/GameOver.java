package flappybird;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;

public class GameOver {
	private BufferedImage gameover;
	private int x,y;
	 public GameOver(){
		
		 try{
		 gameover =ImageIO.read(new File("asset/gameover.png"));
	 }
		 catch(IOException ex){}
		 x =46;
		 y =300;
		 
		 
	}
	 
	 
       public void Paint(Graphics2D g2){
    	   g2.drawImage(gameover,x,y,null);
    	   
       }
}
