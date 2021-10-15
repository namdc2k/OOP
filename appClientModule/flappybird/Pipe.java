package flappybird;
import java.awt.Rectangle;

import pkg2dgamesframework.*;
import flappybird.FlappyBird;


public class Pipe extends Objects {
	private Rectangle rect;
	private FlappyBird fl;
	
	private boolean isBehindBird= false;
	
	
	 public boolean isBehindBird() {
		return isBehindBird;
	}
	public void setBehindBird(boolean isBehindBird) {
		this.isBehindBird = isBehindBird;
	}
	public Pipe() {
		 fl = new FlappyBird();
	}
	public Pipe(int x, int y, int w, int h){
		 super(x,y,w,h);
		 
		 rect =new Rectangle(x,y,w,h);
		
		 
		  }
     public void update(){
    	 setPosX(getPosX()-3);
    	 this.rect.setLocation((int)this.getPosX(),(int)this.getPosY());
     }
     public Rectangle getRect(){
    	 return rect;
     }
}































