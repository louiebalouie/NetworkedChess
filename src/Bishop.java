import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public final class Bishop extends Piece{
	
	public Bishop(Square _occupying, String _color){
		super(_occupying, _color);
		
		if(getColor() == "white") {
			this.icon = new ImageIcon("C:\\Users\\mikey\\Desktop\\NetworkedChess\\images\\whitebishop.png");
		}
		else if(getColor() == "black") {
			this.icon = new ImageIcon("C:\\Users\\mikey\\Desktop\\NetworkedChess\\images\\blackbishop.png");
		}
	}

	public List<Point> getPossibleMoves() {
		List<Point> moves = new ArrayList<Point>();
		
		Point moveIterator = occupying.getPosition();
		
		int startx = moveIterator.x;
		int starty = moveIterator.y;
		
		getDiagUpRight(moves, moveIterator, startx, starty);
		getDiagDownRight(moves, moveIterator, startx, starty);
		getDiagUpLeft(moves, moveIterator, startx, starty);
		getDiagDownLeft(moves, moveIterator, startx, starty);
		
		moveIterator.x = startx;
		moveIterator.y = starty;
		return moves;
	}
}
