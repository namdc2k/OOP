package flappybird;

import java.awt.Rectangle;
import java.io.File;

import pkg2dgamesframework.*;

public class Bird extends Objects {
	private float vt =0;
	private boolean isFlying =false;
	private Rectangle rect;
	public SoundPlayer dead,fly,point;
	public Bird(int x,int y ,int w, int h){
		super(x,y,w,h);
		rect =new Rectangle(x,y,w,h);
		dead = new SoundPlayer(new File("asset/Dead.wav"));
		fly = new SoundPlayer(new File("asset/Fly.wav"));
		point = new SoundPlayer(new File("asset/Ping.wav"));
	}
	

	public void update(long deltaTime){
		vt += FlappyBird.g;
		this.setPosY(this.getPosY() +vt);
		this.rect.setLocation((int)this.getPosX(),(int)this.getPosY());
		
		if (vt<0) {
			isFlying = true;		
		}
		else isFlying = false;
	}
	public void fly(){
		vt = -12;
		//fly.play();
		
	}
	public boolean getIsFlying(){
		return isFlying;
	}
	public Rectangle getRect(){
		return rect;
	}
}
