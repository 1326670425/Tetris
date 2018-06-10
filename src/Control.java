import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Control extends Tetris{
	

	public void action(){
		current = Tetromino.runShape();
		nextone = Tetromino.runShape();
		
		KeyListener kl = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int k = e.getKeyCode();
				keyMoveAction(k);
				repaint();
			}
		};
		
		this.addKeyListener(kl);
		this.setFocusable(true);
		this.requestFocus();
		

		music.start();

		Timer timer = new Timer();
		TimerTask task = new TimerTask(){
			int moveIndex = 0;
			int speed = 30 - 5*level;
			public void run(){
				if(STATE){
					if(moveIndex % speed == 0){
						moveDownAction();
						moveIndex = 0;
						repaint();
					}
				}
				moveIndex++;
				if(!flag1){
					(new Record()).read();
					flag1 = true;
				}
			}
		};
		timer.schedule(task, 10, 20);
	}
	
	public void keyMoveAction(int k){
		switch(k){
		case KeyEvent.VK_RIGHT:moveRightAction();break;
		case KeyEvent.VK_LEFT:moveLeftAction();break;
		case KeyEvent.VK_DOWN:moveDownAction();break;
		case KeyEvent.VK_UP:spinCellAction();break;
		case KeyEvent.VK_I:initAction();break;
		case KeyEvent.VK_P:STATE = false;break;
		case KeyEvent.VK_C:STATE = true;break;
		case KeyEvent.VK_E:System.exit(0);break;
		case KeyEvent.VK_SPACE:up();break;
		}
	}
	public void up(){
		current.moveUp();

	}
	public void initAction(){
		STATE = true;
		flag1 = true;
		wall = new Cell[ROWS][COLS];
		current = Tetromino.runShape();
		nextone = Tetromino.runShape();
		score = 0;
		lines = 0;
		level = 1;
	}
	public boolean canRightMove(){
		if(current == null)
			return false;
		Cell[] cells = current.cells;
		for(int i = 0;i<cells.length;i++){
			Cell c = cells[i];
			int row = c.getRow();
			int col = c.getCol();
			if((col+1) == COLS || wall[row][col+1] != null){
				return false;
			}
		}
		return true;
	}
	public boolean canLeftMove(){
		if(current == null)
			return false;
		Cell[] cells = current.cells;
		for(int i = 0;i<cells.length;i++){
			Cell c = cells[i];
			int row = c.getRow();
			int col = c.getCol();
			if(col == 0 || wall[row][col-1] != null){
				return false;
			}
		}
		return true;
	}
	public boolean isBottom(){
		if(current == null)
			return false;
		Cell[] cells = current.cells;
		for(int i = 0;i<cells.length;i++){
			Cell c = cells[i];
			int col = c.getCol();
			int row = c.getRow();
			if((row+1) == ROWS || wall[row+1][col] != null){
				for(int j = 0;j<cells.length;j++){
					Cell cell = cells[j];
					int col1 = cell.getCol();
					int row1 = cell.getRow();
					wall[row1][col1] = cell;
				}
				removeLine();

				current = nextone;
				nextone = Tetromino.runShape();
				return true;
			}
		}
		return false;
	}
	public void removeLine(){
		boolean flag = true;
		int rowStart = 20;
		for(int row = 0;row<ROWS;row++){
			
			for(int col=0;col<COLS;col++){
				if(wall[row][col] == null){
					flag = false;
					break;
				}
			}
			
			if(flag){
				for(int col = 0;col<COLS;col++){
					wall[row][col] = null;
				}
				rowStart = row;
				score += 10;
				lines += 1;
				level = lines%10 == 0?(level == 5?level:level+1):level;
				for(int row1 = rowStart;row1>0;row1--){
					for(int col1 = 0;col1<COLS;col1++){
						wall[row1][col1] = wall[row1-1][col1];
					}
				}
				BGI = (BGI == 3)?0:BGI+1;
			}
			else{
				flag = true;
			}
		}
	}
	public void moveRightAction(){
		if(canRightMove() && !isBottom()){
			current.moveRight();
		}
	}
	public void moveLeftAction(){
		if(canLeftMove() && !isBottom()){
			current.moveLeft();
		}
	}
	public void moveDownAction(){
		if(current == null)
			return;
		if(!isBottom()){
			current.moveDown();
		}
	}
	public void spinCellAction(){
		Cell[] nCells = current.spin();
		if(nCells == null)
			return;
		for(int i = 0;i<nCells.length;i++){
			int nRow = nCells[i].getRow();
			int nCol = nCells[i].getCol();
			if(nRow<0 || nRow>=ROWS || nCol<0 || nCol>=COLS || wall[nRow][nCol] != null)
				return;
			
		}
		current.cells = nCells;
	}
	
}
