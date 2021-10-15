package flappybird;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Be {
	private BufferedImage begin;
	private int x,y;
	 public Be(){
		
		 try{
		 
		begin =ImageIO.read(new File("asset/begin.png"));
	 }
		 catch(IOException ex){}
		 x =100;
		 y =300;
		 
		 
	}
	 
	 
       public void Paint(Graphics2D g2){
    	   g2.drawImage(begin,x,y,null);
    	   
       }
}



