package flappybird;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;

public class Back {
	private BufferedImage backImage;
	private int x,y,x1,y1;
	 public Back(){
		
		 try{
		 backImage =ImageIO.read(new File("asset/bg.png"));
	 }
		 catch(IOException ex){}
		 x =0;
		 y =0;
		 x1 = x+480;
		 y1 =0;
	}
	 
	 public void Update(){
	 x-=1;
	 x1 -=1;
	 if(x1<0) x= x1+479;
	 if(x<0)  x1=x+479;
	 }
       public void Paint(Graphics2D g2){
    	   g2.drawImage(backImage,x,y,null);
    	   g2.drawImage(backImage,x1,y1,null);
       }
}
