package main;

public class MyMatrix <T> {
	private T [][] myMatrix;
	
	private int rows;
	private int columns;
	
	public MyMatrix() {};
	
	@SuppressWarnings("unchecked")
	public MyMatrix(int r, int c) {
		this.myMatrix = (T[][]) new Object [r][c];
		this.rows = r;
		this.columns = c;
	}
	
	public void add(int r, int c, T obj) {
		this.myMatrix[r][c] = obj;
	}
	
	public T at(int r, int c) {
		return myMatrix[r][c];
	}
	
	public void setMatrix(T[][] m) {
		this.myMatrix = m;
	}
	
	public T[][] getMatrix() {
		return this.myMatrix;
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getCol() {
		return this.columns;
	}
}
