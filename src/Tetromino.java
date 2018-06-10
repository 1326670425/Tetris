
import java.util.Random;

public class Tetromino extends Cell{
	
	protected Cell[] cells = new Cell[4];

	public void moveDown(){
		for(int i = 0;i<cells.length;i++){
			cells[i].moveDown();
		}
	}
	public void moveLeft(){
		for(int i = 0;i<cells.length;i++){
			cells[i].moveLeft();
		}
	}
	public void moveRight(){
		for(int i = 0;i<cells.length;i++){
			cells[i].moveRight();
		}
	}
	public void moveUp(){
		for(int i = 0;i<cells.length;i++){
			cells[i].moveUp();
		}
	}
	
	public static Tetromino runShape(){
		Random random = new Random();
		int index = random.nextInt(7);
		switch(index){
		case 0:
			return new L();
		case 1:
			return new J();
		case 2:
			return new I();
		case 3:
			return new Z();
		case 4:
			return new S();
		case 5:
			return new O();
		case 6:
			return new T();
		}
		return new L();
	}
	
	public Cell[] spin(){
		if(this.getClass().equals(new O().getClass()))
			return null;
		Cell[] iCells = new Cell[4];
		int iRow = this.cells[1].getRow();
		int iCol = this.cells[1].getCol();
		for(int i = 0;i<this.cells.length;i++){
			int nRow = this.cells[i].getRow();
			int nCol = this.cells[i].getCol();
			
			iCells[i] = new Cell(iRow-iCol+nCol, iRow+iCol-nRow, this.cells[i].getBgImage());
		}
		return iCells;
	}
}

class L extends Tetromino{
	public L(){
		cells[0] = new Cell(0,5,Tetris.L);
		cells[1] = new Cell(1,5,Tetris.L);
		cells[2] = new Cell(2,5,Tetris.L);
		cells[3] = new Cell(2,6,Tetris.L);
	}
}
class J extends Tetromino{
	public J(){
		cells[0] = new Cell(0,5,Tetris.J);
		cells[1] = new Cell(1,5,Tetris.J);
		cells[2] = new Cell(2,5,Tetris.J);
		cells[3] = new Cell(2,4,Tetris.J);
	}
}
class I extends Tetromino{
	public I(){
		cells[0] = new Cell(0,5,Tetris.I);
		cells[1] = new Cell(1,5,Tetris.I);
		cells[2] = new Cell(2,5,Tetris.I);
		cells[3] = new Cell(3,5,Tetris.I);
	}
}
class Z extends Tetromino{
	public Z(){
		cells[0] = new Cell(0,4,Tetris.Z);
		cells[1] = new Cell(0,5,Tetris.Z);
		cells[2] = new Cell(1,5,Tetris.Z);
		cells[3] = new Cell(1,6,Tetris.Z);
	}
}
class S extends Tetromino{
	public S(){
		cells[0] = new Cell(0,6,Tetris.S);
		cells[1] = new Cell(0,5,Tetris.S);
		cells[2] = new Cell(1,5,Tetris.S);
		cells[3] = new Cell(1,4,Tetris.S);
	}
}
class O extends Tetromino{
	public O(){
		cells[0] = new Cell(0,5,Tetris.O);
		cells[1] = new Cell(0,6,Tetris.O);
		cells[2] = new Cell(1,5,Tetris.O);
		cells[3] = new Cell(1,6,Tetris.O);
	}
}
class T extends Tetromino{
	public T(){
		cells[0] = new Cell(0,4,Tetris.T);
		cells[1] = new Cell(0,5,Tetris.T);
		cells[2] = new Cell(0,6,Tetris.T);
		cells[3] = new Cell(1,5,Tetris.T);
	}
}