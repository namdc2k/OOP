package flappybird;


import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Base  {
	private BufferedImage baseImage;
	private int x1,x,y1,y;
	
	 public Base(){ 
		 try{
		 baseImage =ImageIO.read(new File("asset/base.png"));
	 }
		 catch(IOException ex){}
		 x=0;
		 y=700;
		 x1=x+719;
		 y1=700;
	 }
	 
	 public void Update(){
	 x-=2;
	 x1 -=2;
	 if(x1<0) x= x1+719;
	 if(x<0)  x1=x+719;
	 }
       public void Paint(Graphics2D g2){
    	   g2.drawImage(baseImage,x,y,null);
    	   g2.drawImage(baseImage,x1,y1,null);
       }
       public int getBaseY() {
    	   return this.y;
       }
       
}















