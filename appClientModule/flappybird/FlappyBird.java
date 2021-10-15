package flappybird;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import pkg2dgamesframework.*;

public class FlappyBird extends GameScreen {
	private BufferedImage birds,bird4,bird2,birds1,bird21,bird41,mute,unmute;
	private Animation bird_ani;

	
	public static float g =0.6f;
	private Bird bird;
	private int colorbird=1,abs;
	public int getAbs() {
		return abs;
	}
	public void setAbs(int abs) {
		this.abs = abs;
	}

	private Base base;
	private Back back;
	
	private GrPipe grpipe;
	private GameOver gameover;
	private Be begin;
	private ready ready;
	private int READY=3;
	private int BEGIN_SCREEN =0;
	private int GAME_PLAY =1;
	private int GAMEOVER_SCREEN=2;
	private int CHOOSE=4;
	private int LEVEL=6;

	private int CurrentScreen = READY;
	

	private int point =0,Hp;
	private int sound=1;
	
	public int getColorbird() {
		return colorbird;
	}
	public void setColorbird(int colorbird) {
		this.colorbird = colorbird;
	}
	
	public int getSound() {
		return sound;
	}
	public void setSound(int sound) {
		this.sound = sound;
	}
	public FlappyBird(){
		super(480,800);
		try {
			birds = ImageIO.read(new File("asset/bird_0.png"));
			bird2 = ImageIO.read(new File("asset/bird_2.png"));
			bird4 = ImageIO.read(new File("asset/bird_4.png"));
			birds1 = ImageIO.read(new File("asset/bird_01.png"));
			bird21 = ImageIO.read(new File("asset/bird_21.png"));
			bird41 = ImageIO.read(new File("asset/bird_41.png"));
			mute = ImageIO.read(new File("asset/mute.jpg"));
			unmute = ImageIO.read(new File("asset/unmute.png"));
		} catch (IOException  ex) {}
		bird_ani = new Animation(100);
		AFrameOnImage f;
		f =new AFrameOnImage(0,0, 81, 62);
		bird_ani.AddFrame(f);
		f =new AFrameOnImage(81,0, 81, 62);
		bird_ani.AddFrame(f);
		f =new AFrameOnImage(162,0, 81,62);
		bird_ani.AddFrame(f);
		f =new AFrameOnImage(81,0, 81,62);
		bird_ani.AddFrame(f);
		
		bird =new Bird(220, 250, 50, 50);
		base =new Base();
	    back =new Back();
	    grpipe =new GrPipe();
	    gameover =new GameOver();
		begin  = new Be();
		ready = new ready();
		BeginGame();
		
		
	}
	public static void main(String[] args) {
		new FlappyBird();
		
		
	}
    private void resetgame(){
    	//CurrentScreen=CHOOSE;
    	bird.setPos(220, 250);
    	grpipe.resetPipes();
    	point=0;
    }
	

	public void GAME_PAINT(Graphics2D g2) {
	
	 back.Paint(g2);
	 grpipe.paint(g2);
	 base.Paint(g2);
	 g2.setColor(Color.black);
	 g2.setFont(new Font("calibri",1,20));
	 g2.drawString("POINT :"+point, 20,70);
	 g2.drawString("HIGH POINT :"+ Hp ,340 , 70);
	 
	 if(CurrentScreen==READY) {
		 ready.Paint(g2);
	 }
	 if(CurrentScreen==CHOOSE) {
		 
		 g2.drawString("press A",320,280);
		 g2.drawString("press S",320,380);
		 g2.drawImage(bird2,220,250,null);
		 g2.drawImage(bird21,220,350,null);
	 }
     
	 if(colorbird==1) {
		 if(CurrentScreen==BEGIN_SCREEN){
			 	g2.drawString("ESC",10,10);
	
				begin.Paint(g2);
				g2.drawImage(bird2,(int)bird.getPosX(),(int)bird.getPosY(),null);
				{if(sound==1)g2.drawImage(unmute,420,10,null);
				else g2.drawImage(mute,420,10,null);
				}
			}
		 if(CurrentScreen==GAME_PLAY) {	 
			if(bird.getIsFlying()) 
				bird_ani.PaintAnims((int) bird.getPosX(),(int) bird.getPosY(), birds, g2, 0, -0.4f);
			else	
				bird_ani.PaintAnims((int) bird.getPosX(),(int) bird.getPosY(), birds, g2, 0, 0.4f);
			}
		 	
		 if(CurrentScreen==GAMEOVER_SCREEN){
			g2.drawImage(bird4,(int)bird.getPosX(),(int)bird.getPosY(),null);
			gameover.Paint(g2);
		 }
	 }else if(colorbird==2) {
		 if(CurrentScreen==BEGIN_SCREEN){
			 	g2.drawString("ESC",10,10);
				begin.Paint(g2);
				g2.drawImage(bird21,(int)bird.getPosX(),(int)bird.getPosY(),null);
				{if(sound==1)g2.drawImage(unmute,420,10,null);
				else g2.drawImage(mute,420,10,null);
				}
			}
		 if(CurrentScreen==GAME_PLAY) {	 
				if(bird.getIsFlying()) 
					bird_ani.PaintAnims((int) bird.getPosX(),(int) bird.getPosY(), birds1, g2, 0, -0.4f);
				else	
					bird_ani.PaintAnims((int) bird.getPosX(),(int) bird.getPosY(), birds1, g2, 0, 0.4f);
				
				}
		 if(CurrentScreen==GAMEOVER_SCREEN){
				g2.drawImage(bird41,(int)bird.getPosX(),(int)bird.getPosY(),null);
				gameover.Paint(g2);
			}
		}
	}
     
	public void GAME_UPDATE(long deltaTime) {	
		if(CurrentScreen == BEGIN_SCREEN){
			resetgame();
		}
		else if(CurrentScreen == CHOOSE) {
			resetgame();
		}
		if(CurrentScreen == LEVEL) {
			resetgame();
		}
		else{
			if(CurrentScreen == GAME_PLAY){
					bird_ani.Update_Me(deltaTime);
					bird.update(deltaTime);
					base.Update();
					back.Update();
					grpipe.update();
						
				if(bird.getPosY()+bird.getH() > base.getBaseY()-8 || bird.getPosY()<-40) {
					if(sound==1) bird.dead.play();
					CurrentScreen = GAMEOVER_SCREEN;
				}
				for (int i = 0; i < GrPipe.SIZE; i++) {
					if(grpipe.getPipe(i).getRect().intersects(bird.getRect())) {
						if(sound==1) bird.dead.play();
						CurrentScreen=GAMEOVER_SCREEN; 
					}
				}
				for (int i = 0; i < GrPipe.SIZE; i++){
					if(i%2==0) {
					if(bird.getPosX()>grpipe.getPipe(i).getPosX() && grpipe.getPipe(i).isBehindBird()==false) {
						point++;
					if(sound==1) bird.point.play();
						grpipe.getPipe(i).setBehindBird(true);
					}
					if(Hp < point)
						Hp = point;
					}	
				}			
			}
		}	
	}

	public void KEY_ACTION(KeyEvent e,int Event) {
		if(Event==KEY_PRESSED) { 
			
			if(CurrentScreen == BEGIN_SCREEN){
				CurrentScreen = GAME_PLAY;
				
			} else if(CurrentScreen == GAME_PLAY){
				bird.fly();
			}else if(CurrentScreen==READY){
				CurrentScreen = CHOOSE;	
			} 
			else if(CurrentScreen==CHOOSE){
				CurrentScreen = LEVEL;	
			} 
			else 
			{
				CurrentScreen =BEGIN_SCREEN;
			}	
				bird.fly();
				if(sound==1) bird.fly.play();
		}
		 if(Event==3) {
			
			 setColorbird(1);
			 CurrentScreen=BEGIN_SCREEN;
		 }
		 if(Event==2) {
		
			 setColorbird(2);
			 CurrentScreen=BEGIN_SCREEN;
		 }
		 if(Event==4) {
			 CurrentScreen=CHOOSE;
		 }
		 if(Event==5) {
			 if(sound==1) setSound(0);
			 else setSound(1);
		 }
		 if(Event==6) {
			 setAbs(2);
			 CurrentScreen=BEGIN_SCREEN;
		 }
		 if(Event==7) {
			 setAbs(4);
			 CurrentScreen=BEGIN_SCREEN;
		 }
	}
}
