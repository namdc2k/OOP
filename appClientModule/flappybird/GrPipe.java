package flappybird;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import pkg2dgamesframework.*;
public class GrPipe {
  private QueueList<Pipe> pipes;
  private BufferedImage pp,pp2;
  
  public static int SIZE =4;
  public int getRandomY(){
	  Random random = new Random();
	  int a;
	  a =random.nextInt(10);
	  return a*20;
  }
  
	 public GrPipe(){
		 pipes = new QueueList<>();
		 
		 try {
				
				pp =ImageIO.read(new File("asset/tree1.png"));
				pp2 =ImageIO.read(new File("asset/tree.png"));
			} catch (IOException  ex) {}
		 Pipe pp;
		 for (int i = 0; i < SIZE/2; i++) {
			int dt = getRandomY(); 
			 
		pp =new Pipe(800+i*320, 430+dt, 89, 401);
		pipes.push(pp);
		
		pp = new Pipe(800+i*320, -250+dt, 89, 401);
		pipes.push(pp);
		}
	 }
	 
	 public Pipe getPipe(int i){
		  return pipes.get(i);
	  }
	 
	 public void resetPipes(){
		 pipes =new QueueList<Pipe>();
		 Pipe pp;
		 for (int i = 0; i < SIZE/2; i++) {
			int dt = getRandomY(); 
			 
		pp =new Pipe(1000+i*320, 430+dt, 89, 401);
		pipes.push(pp);
		
		pp = new Pipe(1000+i*320, -250+dt, 89, 401);
		pipes.push(pp);
		}
	 }
	 public void update(){
		 for (int i = 0; i < SIZE; i++) {
			 pipes.get(i).update();
		}
			if(pipes.get(0).getPosX()<-89){
				int dt =getRandomY();
				Pipe pi;
				pi = pipes.pop();
				pi.setPosX(pipes.get(2).getPosX()+300);
				pi.setPosY(dt+430);
				pi.setBehindBird(false);
				pipes.push(pi);
				
				pi = pipes.pop();
				pi.setPosX(pipes.get(2).getPosX());
				pi.setPosY(dt-250);
				pipes.push(pi);
			
				
			
		}
	 }
	 public void paint(Graphics2D g2){
		 for (int i = 0; i < 4; i++) {
			 if(i%2==0)
			g2.drawImage(pp, (int) pipes.get(i).getPosX(), (int) pipes.get(i).getPosY(), null);
			 else
				 g2.drawImage(pp2, (int) pipes.get(i).getPosX(), (int) pipes.get(i).getPosY(), null);
		 }
	 }
}
























