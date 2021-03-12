package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		Random randy = new Random();
		int a = randy.nextInt(width);
		int b = randy.nextInt(height);

		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.cells[a][b]);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		Stack<Cell> s = new Stack<Cell>();
		//B. Get an ArrayList of unvisited neighbors using the current cell and the method below
		ArrayList<Cell> cell = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(cell.size()>0) {
			//C1. select one at random.
			Random r = new Random();
			Cell i = cell.get(r.nextInt(cell.size()));
			//C2. push it to the stack
			s.push(i);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, i);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = i;
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		//D. if all neighbors are visited
		}else if(cell.size()>8) {
			//D1. if the stack is not empty
			if(s.size()>0) {
				// D1a. pop a cell from the stack
				// D1b. make that the current cell
				currentCell = s.pop();
				// D1c. call the selectNextPath method with the current cell
				selectNextPath(currentCell);
			}
		}
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.hasNorthWall() && c2.hasSouthWall()) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}else if(c1.hasSouthWall() && c2.hasNorthWall()){
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}else if(c1.hasEastWall() && c2.hasWestWall()) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}else {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell> unvisited = new ArrayList<Cell>(1);
		if(c.hasBeenVisited()==false) {
			unvisited.add(c);
		}
		return unvisited;
	}
}
