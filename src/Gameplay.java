import javax.swing.JPanel;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Gameplay extends JPanel implements KeyListener,ActionListener {
	
	private int[] snakexlength = new int [750];
	private int[] snakeylength = new int [750];
	
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private int moves=0;
	private int lengthofsnake = 3;
	
	private Timer timer;
	private int delay=100;
	
	private ImageIcon snakeimage;
	
	private ImageIcon titleImage;
	
	private ImageIcon enemyimage;
	private ImageIcon obstacleimage;
	private Random random=new Random();
	private int xpos= random.nextInt(33)*25+25;
	private int ypos= random.nextInt(22)*25+75;
	
	private int[] oxpos = new int [10];  
	private int[] oypos = new int [10];
	private int c=0;
	
	
	public Gameplay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
		
		
	}
	public void paint(Graphics g)
	{
		if(moves == 0)
		{
			snakexlength[2]=50;
			snakexlength[1]=75;
			snakexlength[0]=100;
			
			snakeylength[2]=100;
			snakeylength[1]=100;
			snakeylength[0]=100;
			
			if(c<1)
			{
				for(int i=0;i<10;i++)
				{
					obstacleimage=new ImageIcon("obstacle.png");
					oxpos[i]= random.nextInt(33)*25+25;
					oypos[i]= random.nextInt(22)*25+75;
					obstacleimage.paintIcon(this, g, oxpos[i], oypos[i]);
					
				}
				c++;
			}
			
			
			
		}
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 

		//title image border
		g.setColor(Color.white);
		g.drawRect(24,10,851,55);
		
		g.drawString("Snake Game", 400, 40 );
		
		
		//border for gameplay
		g.setColor(Color.white);
		g.drawRect(24,74,851,577);
		
		//background for gameplay
		g.setColor(Color.black);
		g.fillRect(24, 75, 850, 575);
		
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
		
		for(int i=0;i<lengthofsnake;i++)
		{
			if(i==0 && right)
			{
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
			}
			if(i==0 && left)
			{
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
			}
			if(i==0 && up)
			{
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
			}
			if(i==0 && down)
			{
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
			}
			if(i!=0)
			{
		
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this,g,snakexlength[i],snakeylength[i]);
				
			}
		}
		
		enemyimage=new ImageIcon("enemy.png");
		
		
		if((xpos==snakexlength[0] && ypos==snakeylength[0]))
		{
			lengthofsnake++;
			xpos= random.nextInt(33)*25+25;
			ypos= random.nextInt(22)*25+75;
		}
		
		for(int i=0;i<10;i++)
		{
			obstacleimage=new ImageIcon("obstacle.png");
			
			obstacleimage.paintIcon(this, g, oxpos[i], oypos[i]);
			
		}
		
		for(int i=0;i<10;i++)
		{
			if((oxpos[i]==snakexlength[0] && oypos[i]==snakeylength[0]))
			{
				lengthofsnake=3;
				moves=0;
				right=true;
				left=false;
				up=false;
				down=false;
				c=0;
				repaint();
			}
		}
		
		enemyimage.paintIcon(this, g, xpos, ypos);
		g.dispose();
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_RIGHT )
		{
			moves++;
			right=true;
			if(!left)
			{
				right=true;
			}
			else
			{
				right=false;
				left=true;
			}
			
			up=false;
			down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT )
		{
			moves++;
			left=true;
			if(!right)
			{
				left=true;
			}
			else
			{
				left=false;
				right=true;
			}
			
			up=false;
			down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_UP )
		{
			moves++;
			up=true;
			if(!down)
			{
				up=true;
			}
			else
			{
				up=false;
				down=true;
			}
			
			left=false;
			right=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN )
		{
			moves++;
			down=true;
			if(!up)
			{
				down=true;
			}
			else
			{
				down=false;
				up=true;
			}
			
			left=false;
			right=false;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		timer.start();
		if(right)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeylength[r+1]=snakeylength[r];
			}
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				if(r==0)
				{
					snakexlength[r]=snakexlength[r]+25;
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
				}
				
				if(snakexlength[r]>850)
				{
					snakexlength[r]=25;
				}
			}
			repaint();
			
		}
		if(left)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeylength[r+1]=snakeylength[r];
			}
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				if(r==0)
				{
					snakexlength[r]=snakexlength[r]-25;
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
				}
				
				if(snakexlength[r]<25)
				{
					snakexlength[r]=850;
				}
			}
			repaint();
		}
		if(up)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakexlength[r+1]=snakexlength[r];
			}
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				if(r==0)
				{
					snakeylength[r]=snakeylength[r]-25;
				}
				else
				{
					snakeylength[r]=snakeylength[r-1];
				}
				
				if(snakeylength[r]<75)
				{
					snakeylength[r]=625;
				}
			}
			repaint();
			
		}
		if(down)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakexlength[r+1]=snakexlength[r];
			}
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				if(r==0)
				{
					snakeylength[r]=snakeylength[r]+25;
				}
				else
				{
					snakeylength[r]=snakeylength[r-1];
				}
				
				if(snakeylength[r]>625)
				{
					snakeylength[r]=75;
				}
			}
			repaint();
			
		}
		
	}
}
