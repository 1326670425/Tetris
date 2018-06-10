import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import java.lang.Thread;

public class Tetris extends JPanel{
	
	protected Tetromino nextone;		//下一个下落对象
	protected Tetromino current;		//当前下落对象
	
	public static final int ROWS = 20;//最大行
	public static final int COLS = 10;//最大列
	public static final int CELL_SIZE = 26;
	public static final int BGI_COUNT = 4;
	Music bgm = new Music("bgm.wav");
	Thread music = new Thread(bgm);
	
	protected int score = 0;//分数
	protected int lines = 0;//已消除行数
	protected int level = 1;//等级
	protected static int[] result = new int[100];
	protected Cell[][] wall = new Cell[ROWS][COLS];//墙
	protected boolean flag = false;
	protected boolean flag1 = false;
	protected boolean STATE = true;
	protected int BGI = 0;//背景图状态
	

	public static BufferedImage L;
	public static BufferedImage J;
	public static BufferedImage I;
	public static BufferedImage Z;
	public static BufferedImage S;
	public static BufferedImage T;
	public static BufferedImage O;
	public static BufferedImage[] bgi = new BufferedImage[BGI_COUNT];
	public static BufferedImage pause;
	public static BufferedImage tetris;
	public static BufferedImage gameover;
	//加载静态资源
	static{
		try{
			L = ImageIO.read(Tetris.class.getResource("L.png"));
			J = ImageIO.read(Tetris.class.getResource("J.png"));
			Z = ImageIO.read(Tetris.class.getResource("Z.png"));
			O = ImageIO.read(Tetris.class.getResource("O.png"));
			S = ImageIO.read(Tetris.class.getResource("S.png"));
			I = ImageIO.read(Tetris.class.getResource("I.png"));
			T = ImageIO.read(Tetris.class.getResource("T.png"));
			pause = ImageIO.read(Tetris.class.getResource("pause.png"));
			tetris = ImageIO.read(Tetris.class.getResource("tetris.png"));
			gameover = ImageIO.read(Tetris.class.getResource("gameover.png"));
			for(int i = 0; i < bgi.length; i++) {
				String name = "bgi"+i+".jpg";
				bgi[i] = ImageIO.read(Tetris.class.getResource(name));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(bgi[BGI], 0, 0, null);
		g.drawImage(tetris, 0, 0, null);
		g.translate(15, 15);

		paintWall(g);
		paintCurrent(g);
		paintNextone(g);
		paintTabs(g);
		paintGamePause(g);
		paintGameOver(g);
	}
	public void paintWall(Graphics g){
		
		for(int row = 0;row<ROWS;row++){
			for(int col = 0;col<COLS;col++){
				Cell cell = wall[row][col];
				int rows = row*CELL_SIZE;
				int cols = col*CELL_SIZE;
				if(cell == null){
					
				}
				else{
					g.drawImage(cell.getBgImage(), cols, rows, 	null);
				}
			}
		}
	}
	public void paintCurrent(Graphics g){
		if(current == null)
			return;
		Cell[] cells = current.cells;
		for(int i = 0; i<cells.length;i++){
			Cell c = cells[i];
			int col = c.getCol();
			int row = c.getRow();
			int x = col*CELL_SIZE;
			int y = row*CELL_SIZE;
			g.drawImage(c.getBgImage(), x, y, null);
		}
	}
	public void paintNextone(Graphics g){
		if(nextone == null)
			return;
		Cell[] cells = nextone.cells;
		for(int i = 0;i<cells.length;i++){
			Cell c = cells[i];
			int row = c.getRow();
			int col = c.getCol()+9;
			int x = col*CELL_SIZE;
			int y = row*CELL_SIZE;
			g.drawImage(c.getBgImage(), x, y, null);
		}
	}
	public void paintTabs(Graphics g){
		int x = 410;
		int y = 160;
		
		Color color = new Color(240,234,34);
		g.setColor(color);
		
		Font f = new Font(Font.SERIF,Font.BOLD,30);
		g.setFont(f);
		g.drawString(""+score, x, y);
		y += 56;
		g.drawString(""+lines, x, y);
		y += 56;
		g.drawString(""+level, x, y);
		y+=56;x-=85;
		Color color1 = new Color(0,255,64);
		g.setColor(color1);
		g.drawString("RECORD", x, y);
		for(int i = 0;i<3;i++){
			y += 56;
			g.drawString("NO."+(i+1)+"   "+result[i], x, y);
		}
		
	}
	
	public void paintGamePause(Graphics g){
		if(!STATE && !isGameOver()){
			g.drawImage(pause, -15, -15,null);
		}
	}
	public boolean isGameOver(){
		for(int col = 0;col<COLS;col++){
			if(wall[0][col] != null)
				return true;
		}
		return false;
	}
	public void paintGameOver(Graphics g){
		if(isGameOver()){
			current = null;
			g.drawImage(gameover, -15, -15, null);
			Color color = new Color(0,71,157);
			g.setColor(color);
			Font font = new Font(Font.SERIF,Font.BOLD,30);
			g.setFont(font);
			g.drawString(""+score, 260, 207);
			g.drawString(""+lines, 260, 253);
			g.drawString(""+level, 260, 300);
			STATE = false;
			if(!flag){
				(new Record()).write(score);
				flag = true;
			}
		}
	}
	public static void startTetris(){
		JFrame frame = new JFrame();
		Control control = new Control();
		frame.add(control);
		frame.setSize(525, 600);
		frame.setTitle("俄罗斯方块");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		control.action();
	}

}
