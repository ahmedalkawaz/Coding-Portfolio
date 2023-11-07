import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class Checkmate {
	
	private static Piece [] [] board;
	private static Spot [] [] physicalBoard;
	private static ArrayList<Point> checks= new ArrayList <Point>();
	private static ArrayList<Point> blockables = new ArrayList <Point>();
	
	
	public Checkmate(Piece[][]board, Spot[][] pBoard) {
		this.board = board;
		physicalBoard = pBoard;
	}

	
	
	
	
	
	public static Spot[][] getPhysicalBoard() {
		return physicalBoard;
	}






	public static void setPhysicalBoard(Spot[][] physicalBoard) {
		Checkmate.physicalBoard = physicalBoard;
	}






	public static Piece[][] getBoard() {
		return board;
	}






	public static void setBoard(Piece[][] board) {
		Checkmate.board = board;
	}






	public ArrayList<Point> getChecks() {
		return checks;
	}






	public void setChecks(ArrayList<Point> checks) {
		this.checks = checks;
	}






	public static boolean isCheckMate(boolean color) { //color--> color of the king
		int x=  findPiece(color, 100).x; //finds position of the king
		int y= findPiece(color,100).y;
		isAttacked(color,x,y,false,false);
		for (int j=0;j<checks.size();j++) {
			//logically a checkmate is when the king can't move and there is at least one piece that is putting the king in check with that piece being unremovable and unblockable
			//if the king cannot move and it is currently in check and the current piece cannot be blocked
			//work on canblock and kingAttack
		// && noMove(x,y)  && canRemove(j)==false 
			
			if (currentCheck(x,y) && noMove(x,y)  && canRemove(j)==false &&  canBlock(color,j)==false ) {  
				checks.clear();
				return true;
			}	
		}
	return false;
	}
	
	public static boolean inCheck(boolean color) { //returns if the king is in check or not
		int x=  findPiece(color, 100).x; //finds position of the king
		int y= findPiece(color,100).y;
		if( currentCheck(x,y)) {
			physicalBoard[x][y].setBackground(new Color(231, 133, 135));
			return true;
		}
		return false;
	}
	
	
	public static ArrayList<Point> restrictedSpots(boolean isWhite) { //searches through each piece in the array and adds the positions of the spots they threaten into an arraylist
		ArrayList<Point> restricted = new ArrayList <Point>();
		for(int i = 0; i <8;i++) {
			for (int j = 0; j < 8; j ++) {
				if(board[i][j].getValue()==1&&board[i][j].isWhite()!=isWhite) {
					restricted.addAll(physicalBoard[i][j].getPiece().threatenMoves());
				}
				else if(board[i][j].getValue()!=0&&board[i][j].isWhite()!=isWhite) {
					restricted.addAll(physicalBoard[i][j].getPiece().threatenMoves());
				}
			}
		}
		
		
		return restricted;
		
	}
	

	
	

	
	private static boolean canBlock(boolean color, int i) {
		int kingX=findPiece(color,100).x;
		int kingY=findPiece(color,100).y;
	
			if (board[(int)(checks.get(i).x)][(int) (checks.get(i).y)].getValue()==5) { //rook code
				return rookBlocked(i,kingX,kingY);


			}
			if (board[(int)(checks.get(i).x)][(int)(checks.get(i).y)].getValue()==4) { //knight cannot be blocked only removed so we need to see if canRemove is true
				if (canRemove(i)) {
					return true;
				}
			}

			if (board[(int)(checks.get(i).x)][(int)(checks.get(i).y)].getValue()==3) {
				//figure out which diagonal to move through
				//when we do that just see are any of the points blockable
			 
					return bishopBlocked(i,kingX,kingY);
			  }

				//queen 
				
				
				if (board[(int)(checks.get(i).x)][(int) (checks.get(i).y)].getValue()==9) {

					return bishopBlocked(i,kingX,kingY) || rookBlocked(i,kingX,kingY);

					
					
				}
				
				//pawns
				if (board[(int)(checks.get(i).x)][(int) (checks.get(i).y)].getValue()==1) {
					if (canRemove(i)) {
						return true;
					}
				}
				
				
				
				//
			
 
		return false;
		
	}
	
			private static boolean bishopBlocked(int i, int kingX, int kingY) {
				if (Math.abs((int)(checks.get(i).x)-kingX) ==1 && Math.abs((int)(checks.get(i).y)-kingY) ==1){
					if (canRemove(i)) {
						  return true;
					  }
				  }else {
						//top right
						if ( ((int)(checks.get(i).y)< kingY) && ((int)(checks.get(i).x)>kingX) && blockDiagonal1(kingX,kingY,(int)(checks.get(i).x),(int)(checks.get(i).y))) {
							return true;
						}
						//top left
						if ((int)(checks.get(i).x)>kingX && (int)(checks.get(i).y)>kingY && blockDiagonal2(kingX,kingY,(int)(checks.get(i).x),(int)(checks.get(i).y))) {
							return true;
						}
						//bottom right
						if ((int)(checks.get(i).x)<kingX && (int)(checks.get(i).y)<kingY && blockDiagonal3(kingX,kingY,(int)(checks.get(i).x),(int)(checks.get(i).y))) {
							return true;
						}
						//bottom left
						if ((int)(checks.get(i).x)<kingX && (int)(checks.get(i).y)>kingY && blockDiagonal4(kingX,kingY,(int)(checks.get(i).x),(int)(checks.get(i).y))) {
							return true;
						}
					}
				return false;
			}
			private static boolean rookBlocked(int i, int kingX, int kingY) {
				if (kingX==(int)(checks.get(i).x) && blockRookX(kingX,kingY,i)) { 
					return true;

				
				}else if (kingY==(int)(checks.get(i).y) && blockRookY(kingX,kingY,i) ) {
		
					return true;
				
				}
				return false;
			}
	
	
	
	
	//returns true if the piece can be blocked
			//this method checks to see if the rook in the same row as the king can be blocked
			//it pretends to move the rook from its current position to one less than where the king is and if the rook can be blocked at any of these positions then it means that the rook's attack is able to be blocked
			public static boolean blockRookX(int xKing, int yKing, int index) {
				
				if (Math.abs(yKing-(int) checks.get(index).y)==1 && canRemove(index)) {
					return true;
				}
				
				if (yKing>(int) checks.get(index).y) { 
					for (int j=(int) checks.get(index).y+1; j<yKing;j++) { //start one position after the rook's location because this method is needed to see if the rook can be blocked, not attacked at its current position. Seen as the rook can never "take" the opposing king, we will iterate up until the king's y value
						if (isAttacked(board[(int) checks.get(index).x][(int) checks.get(index).y].isWhite(), (int) checks.get(index).x,j,false,true)) {
							return true;
						}
					}
				}
				if (yKing<(int) checks.get(index).y) 
				{
					for (int j=yKing+1;j<checks.get(index).y;j++) { //same as the for loop above, we start from the kings y+1 (king can never be taken
						if (isAttacked(board[(int) checks.get(index).x][(int) checks.get(index).y].isWhite(), (int) checks.get(index).x, j,false,true)) {
							return true;
						}
					}
					
				
				}

			
				
				return false;
			}
			//look at documentation above (this method deals with rooks in the same column
			private static boolean blockRookY(int xKing, int yKing, int index) {
				if (xKing>(int)(checks.get(index).x)) { 
					for (int j=(int)(checks.get(index).x)+1;j<xKing;j++){
						if (isAttacked(board[(int) checks.get(index).x][(int) checks.get(index).y].isWhite(), j, (int)(checks.get(index).y),false,true)) {
							return true;
						}
					}
				}
				if (xKing<(int) checks.get(index).x) {
					for (int j=xKing+1; j<(int) checks.get(index).y;j++) {
						if (isAttacked(board[(int) checks.get(index).x][(int) checks.get(index).y].isWhite(),j, (int ) (checks.get(index).y),false,true)) {
							return true;
						}
					}
				}
				if (Math.abs(xKing-(int) checks.get(index).x)==1 && canRemove(index)) {
					return true;
				}

				return false;
			}
			

	
	
	
	
	
	
	public static boolean blockDiagonal1(int xKing, int yKing, int bishopX, int bishopY) {
		if (xKing==bishopX && yKing==bishopY) {
			return false;
		}
		
		if (isAttacked(!board[xKing][yKing].isWhite(),bishopX,bishopY,false,true)) {
			
				return true;
			}
		
		

		bishopX--; bishopY++;
		return blockDiagonal1(xKing, yKing, bishopX,bishopY);
		
		
		
	}
	

	
	
	public static boolean blockDiagonal2(int xKing, int yKing, int bishopX, int bishopY) {
		if (xKing==bishopX && yKing==bishopY) {
			return false;
		}
		
		


			if (isAttacked(!board[xKing][yKing].isWhite(),bishopX,bishopY,false,true)) {
			
			return true;
			}
	
		

		bishopX--; bishopY--;
		return blockDiagonal2(xKing, yKing,bishopX,bishopY);
	}
	
	
	
	
	
	
	public static boolean blockDiagonal3(int xKing, int yKing, int bishopX, int bishopY) {
		if (xKing==bishopX && yKing==bishopY) {
			return false;
		}

	
	
	
		if (isAttacked(!board[xKing][yKing].isWhite(),bishopX,bishopY,false,true)){
		
			return true;
		}
	
	
	
		bishopX++; bishopY++;
		return blockDiagonal3(xKing, yKing,bishopX,bishopY);
	}
	
	
	
	public static boolean blockDiagonal4(int xKing, int yKing, int bishopX, int bishopY) {
		if (xKing==bishopX && yKing==bishopY) {
			return false;
		}

			if (isAttacked(!board[xKing][yKing].isWhite(),bishopX,bishopY,false,true)){
		
				return true;
			
			}
		

		bishopX++; bishopY--;
		return blockDiagonal4(xKing, yKing,bishopX,bishopY);	
	}
	
	
	//A king can never be right next to the enemey's king; they would both be in check so we need to account for that in this method to ensure that the king does not move invalidly into the vicinity of the opposing king
	//return true if king is next to another king
	private static boolean kingVicinity(int xKing, int yKing) {
		int otherX= findPiece(!board[xKing][yKing].isWhite(),100).x;
		int otherY= findPiece(!board[xKing][yKing].isWhite(),100).y;
		if (xKing-1==otherX && yKing==otherY || (xKing+1==otherX && yKing==otherY) ) {
			return true;
		}
		if ((xKing==otherX && yKing+1==otherY) ||  (xKing==otherX && yKing-1==otherY)){
			return true;
		}
		if ( (xKing-1==otherX && yKing+1==otherY) || (xKing-1==otherX && yKing-1==otherY)){
			return true;
		}
		
		if ( (xKing+1==otherX && yKing-1==otherY ) || ( xKing+1==otherX && yKing+1==otherY) ) {
			return true;
		}
		return false;
		
		
	}
	
	public static boolean currentCheck (int xLoc, int yLoc) {
		if( isAttacked(board[xLoc][yLoc].isWhite(),xLoc,yLoc,true,false)) {
			blockables = new ArrayList <Point>();
			return true;
		}
		return false;
	}
	
	private static void reset(int x1,int y1, int x2, int y2, boolean color, int v) {
		board[x2][y2]= new Piece (x2,y2,100,board[x1][y1].isWhite()); //track color and value
		board[x1][y1]= new Piece(x1,y1,v,color);
	}
	
	private static boolean noMove(int x, int y) {
		return !(moveLeft(x,y) || moveRight(x,y) || moveUp(x,y) || moveDown(x,y) || movetLeft(x,y) || movetRight(x,y) || movebLeft(x,y) || movebRight(x,y));
	}
	
	
	public static boolean moveLeft (int xLoc, int yLoc) {
	
			if (yLoc-1>=0 && (board[xLoc][yLoc-1].getValue()==0 || (board[xLoc][yLoc-1].getValue() !=0 && board[xLoc][yLoc-1].isWhite() !=board[xLoc][yLoc].isWhite() ))) {
				boolean c= board[xLoc][yLoc-1].isWhite(); int value= board[xLoc][yLoc-1].getValue();					
				
				board[xLoc][yLoc-1]=new Piece(xLoc,yLoc-1,100,board[xLoc][yLoc].isWhite());
				board[xLoc][yLoc]= new Piece(0,0,0,false);
				
				boolean attacked=isAttacked(board[xLoc][yLoc-1].isWhite(),xLoc,yLoc-1,true,false)==false && kingVicinity(xLoc,yLoc-1)==false; 
				
				reset(xLoc,yLoc-1,xLoc,yLoc,c,value);
				
				return attacked;
			}
			return false;
	
	}
	public static boolean moveRight (int xLoc, int yLoc) {
		
		if (yLoc+1<=7 && (board[xLoc][yLoc+1].getValue()==0 || (board[xLoc][yLoc+1].getValue() !=0 && board[xLoc][yLoc+1].isWhite() !=board[xLoc][yLoc].isWhite() ))) {
			boolean c=board[xLoc][yLoc+1].isWhite(); int value=board[xLoc][yLoc+1].getValue(); 
		
			board[xLoc][yLoc+1]=new Piece(xLoc,yLoc+1,100,board[xLoc][yLoc].isWhite());
			board[xLoc][yLoc]= new Piece(0,0,0,false);
			
			boolean attacked= isAttacked(board[xLoc][yLoc+1].isWhite(),xLoc,yLoc+1,true,false)==false && kingVicinity(xLoc,yLoc+1)==false; 
			reset(xLoc,yLoc+1,xLoc,yLoc,c,value);
			
			return attacked;
		}
		return false;

}

	public static boolean moveUp(int xLoc, int yLoc) {
		if (xLoc-1>=0 && (board[xLoc-1][yLoc].getValue()==0 || (board[xLoc-1][yLoc].getValue() !=0 && board[xLoc-1][yLoc].isWhite() !=board[xLoc][yLoc].isWhite() ))) {
			boolean c= board[xLoc-1][yLoc].isWhite(); int value=board[xLoc-1][yLoc].getValue();
			
			board[xLoc-1][yLoc]=new Piece(xLoc-1,yLoc,100,board[xLoc][yLoc].isWhite()); 
			board[xLoc][yLoc]= new Piece(0,0,0,false);
			
			boolean attacked=isAttacked(board[xLoc-1][yLoc].isWhite(),xLoc-1,yLoc,true,false)==false && kingVicinity(xLoc-1,yLoc)==false; 
			reset(xLoc-1,yLoc,xLoc,yLoc,c,value);
			
			return attacked;
		}
		return false;
		
	}
	public static boolean moveDown(int xLoc, int yLoc) {
		if (xLoc+1<=7 && (board[xLoc+1][yLoc].getValue()==0 || (board[xLoc+1][yLoc].getValue() !=0 && board[xLoc+1][yLoc].isWhite() !=board[xLoc][yLoc].isWhite() ))) {
			boolean c= board[xLoc+1][yLoc].isWhite(); int value=board[xLoc+1][yLoc].getValue();
			
			board[xLoc+1][yLoc]=new Piece(xLoc+1,yLoc,100,board[xLoc][yLoc].isWhite());
			board[xLoc][yLoc]= new Piece(0,0,0,true);
			
			boolean attacked= isAttacked(board[xLoc+1][yLoc].isWhite(),xLoc+1,yLoc,true,false)==false && kingVicinity(xLoc+1,yLoc)==false; 
			reset(xLoc+1,yLoc,xLoc,yLoc,c,value);
			
			return attacked;
		
		}
		return false;
		
	}
	
	public static boolean movetLeft(int xLoc, int yLoc) {
		
		if ((xLoc-1>=0 && yLoc-1>=0) &&  (board[xLoc-1][yLoc-1].getValue()==0 || (board[xLoc-1][yLoc-1].getValue() !=0 && board[xLoc-1][yLoc-1].isWhite() !=board[xLoc][yLoc].isWhite()))){
			boolean c=board[xLoc-1][yLoc-1].isWhite(); int value= board[xLoc-1][yLoc-1].getValue();
			
			board[xLoc-1][yLoc-1]= new Piece (xLoc-1,yLoc-1,100,board[xLoc][yLoc].isWhite()); board[xLoc][yLoc]= new Piece(0,0,0,false);
		
			boolean attacked=isAttacked(board[xLoc-1][yLoc-1].isWhite(),xLoc-1,yLoc-1,true,false)==false && kingVicinity(xLoc-1,yLoc-1)==false ;
			
			reset(xLoc-1,yLoc-1,xLoc,yLoc,c,value);
			
			return attacked;
		}
		return false;
	}
	public static boolean movetRight(int xLoc, int yLoc) {
		if ((xLoc-1>=0 && yLoc+1<=7) &&  (board[xLoc-1][yLoc+1].getValue()==0 || (board[xLoc-1][yLoc+1].getValue() !=0 && board[xLoc-1][yLoc+1].isWhite() !=board[xLoc][yLoc].isWhite()))){
			boolean c=board[xLoc-1][yLoc+1].isWhite(); int value= board[xLoc-1][yLoc+1].getValue();
			board[xLoc-1][yLoc+1]= new Piece (xLoc-1,yLoc+1,100,board[xLoc][yLoc].isWhite());
			
			board[xLoc][yLoc]= new Piece(0,0,0,false);
		
			boolean attacked= isAttacked(board[xLoc-1][yLoc+1].isWhite(),xLoc-1,yLoc+1,true,false)==false && kingVicinity(xLoc-1,yLoc+1)==false;
			reset(xLoc-1,yLoc+1,xLoc,yLoc,c,value);
			
			return attacked;
			
		}
		return false;
	}
	public static boolean movebLeft(int xLoc, int yLoc) {

		if ((yLoc-1>=0 && xLoc+1<=7) &&  (board[xLoc+1][yLoc-1].getValue()==0 || (board[xLoc+1][yLoc-1].getValue() !=0 && board[xLoc+1][yLoc-1].isWhite() !=board[xLoc][yLoc].isWhite()))) {
			
			
			boolean c=board[xLoc+1][yLoc-1].isWhite();
			
			int value= board[xLoc+1][yLoc-1].getValue();
			
			board[xLoc+1][yLoc-1]= new Piece (xLoc+1,yLoc-1,100,board[xLoc][yLoc].isWhite());
			board[xLoc][yLoc]= new Piece(0,0,0,false);
		
			boolean attacked=isAttacked(board[xLoc+1][yLoc-1].isWhite(),xLoc+1,yLoc-1,true,false)==false && kingVicinity(xLoc+1,yLoc-1)==false;
			reset(xLoc+1,yLoc-1,xLoc,yLoc,c,value);
			
			return attacked;
		} 
		return false;
	}
	public static boolean movebRight(int xLoc, int yLoc) {
		
		if ((xLoc+1<=7 && yLoc+1<=7) &&  (board[xLoc+1][yLoc+1].getValue()==0 || (board[xLoc+1][yLoc+1].getValue() !=0 && board[xLoc+1][yLoc+1].isWhite() !=board[xLoc][yLoc].isWhite()))){
			boolean c=board[xLoc+1][yLoc+1].isWhite(); int value=board[xLoc+1][yLoc+1].getValue();
			
			board[xLoc+1][yLoc+1]= new Piece (xLoc+1,yLoc+1,100,board[xLoc][yLoc].isWhite());
			board[xLoc][yLoc]= new Piece(0,0,0,false);
		
			boolean attacked=isAttacked(board[xLoc+1][yLoc+1].isWhite(),xLoc+1,yLoc+1,true,false)==false && kingVicinity(xLoc+1,yLoc+1)==false;
			reset(xLoc+1,yLoc+1,xLoc,yLoc,c,value);
			return attacked;
		}
		return false;
	}


	
	
	
	private static boolean canRemove(int i) {
		return isAttacked(board[checks.get(i).x][checks.get(i).y].isWhite(),checks.get(i).x,checks.get(i).y,true,false);
	}
	

	public static boolean isAttacked(boolean c, int x, int y, boolean remove, boolean block) {
		
		//c--> color x--> x position of piece y--> y position of piece remove and block are used to account for pawns blocking/moving diagonally
		for (int i=0;i<board.length;i++) {
			for (int j=0;j<board[i].length;j++) {
				if (board[i][j].getValue() !=0 && c !=board[i][j].isWhite()) {
					if (board[i][j].getValue()==5 && i==x && inBetweenX(j,y,x)) {
						if (block || remove) {
						return true;
						}
						if (!(block || remove)) {
							checks.add(new Point(i,j));
						}
					}
					if (board[i][j].getValue()==5 && j==y && inBetweenY(i,x,y)) {
						
						if (block || remove) {
						return true;
						}
						if (!(block || remove)) {
							checks.add(new Point(i,j));
						}
					}
					if (board[i][j].getValue()==4 && knightAttack(i,j,board[x][y])) {
						if (block || remove) {
							return true;
						}
						if (!(block || remove)) {
							checks.add(new Point(i,j));
						}
					}
					if (board[i][j].getValue()==3 && isInDiagonal(i,j,board[x][y])) {
						if (block || remove) {
							
						return true;
						}
						if (!(block || remove)) {
							checks.add(new Point(i,j));
						}
					}
					if (board[i][j].getValue()==9 && queenAttack(i,j,x,y)) {
						if (block || remove) {
							
						return true;
						}
						if (!(block || remove)) {
							checks.add(new Point(i,j));
						}
					}
					if (remove==true || !(block || remove)) {
						if (c==false && board[i][j].getValue()==1  && whitePawnAttack(i,j,board[x][y])) {
							
							if (!(block || remove)) {
								checks.add(new Point(i,j));
							}
							if (block || remove) {
								return true;
							}
							
						}
						if (c && board[i][j].getValue()==1  && blackPawnAttack(i,j,board[x][y])) {
							if (!(block || remove)) {
								checks.add(new Point(i,j));
								}
							if (block || remove) {
								return true;
							}
						}
						}
						if (block) {
							//write code for the pawns here
							if (c && board[i][j].getValue()==1) {
								if (i+1==x && j==y) {
								
									return true;
								}
							}
							 if ( !c && board[i][j].getValue()==1) {
								if (i-1==x && j==y) {
									return true;
								}
							}
							
						}
					

				}
				

				}
			
			
		}
		if (checks.size()>0 && (remove==false && block==false)) {
			return true;
		}
		return false;
		
	}
	
	
	//this method checks to see if the king is on a square where the knight can attack
	//it works in two parts. because there are 8 squares that a knight could be placed on (maximum) we have to first check if each square is in bounds. After we can confirm it is, then we can check to see if the king is there
	//returns true if the king is on a square that the knight can attack (meaning the king would therefore be in check)

	
	//finds a specific type of piece based on color and value (just a simple iteration through the 2d array)
	//usually for finding a king
	public static Point findPiece(boolean color, int value) { //x and y are not parameters because that is what we are looking for
		for (int i=0;i<board.length;i++) {
			for (int j=0;j<board[i].length;j++) {
				if (board[i][j].isWhite()==color && board[i][j].getValue()==value) {
					return new Point (i,j);
				}
			}
		}
		return new Point(-1,-1);
	}
	
	

	
	public static boolean queenAttack(int qX, int qY, int kX, int kY) {
		if (kX>=0 && qX>=0 && qY>=0 && kY>=0) {
		return (kX==qX && inBetweenX(qY,kY,kX)) || (kY==qY && inBetweenY(qX,kX,kY)) || isInDiagonal(qX,qY,board[kX][kY]);
		}
		return false;
	}
	
	
	

	
	
	
	
	
	//checks to see if there is anything between rook and king assuming it is in the same row
	//the if statements are used so that we can make sure that the for loops do not go out of bounds when looking for any possible pieces which may block a check
	//the for loops work by going from the rookY value (or the King y value) and if there is a piece blocking it then it will return false meaning no check by the rook can occur
	//if none of this happens then the method will just return true;
	//can also be used for the queen too
	private static boolean inBetweenX (int rookY,int yLoc, int xLoc) { //see documentation below
		if (Math.abs(rookY-yLoc)==1) { //means pieces are right next to each other so there can be no possible block
			return true;
		}
		
		if (rookY<yLoc) { //two different for loops are used so that it is easier to iterate without going out of bounds
			for (int i=rookY;i<=yLoc;i++) { 
				if (rookBlocked(xLoc,i,board[xLoc][rookY],board[xLoc][yLoc])) { 
					return false;
				}
			}
		}else if (rookY>yLoc) {
			for (int i=yLoc;i<=rookY;i++) {
				if (rookBlocked(xLoc,i,board[xLoc][rookY],board[xLoc][yLoc])) {
					return false;
				}
			}
		}
		
		for(int i = 0; i < 8; i ++) {
			if(board[i][rookY].getValue()==0) {
				blockables.add(new Point(i,rookY));
			}
		}
	
	return true; //return true if the rook is attacking the piece without any obstacles
	
	}
	
	//if the rooks are in the same column as the opposing king it checks to see if the king could possibly be put in check by this 
	//similar to the method above except the method above deals if the rook and king are in the same row, this is the same row
	//the if statements are used to make sure that there is no OOB error and so we can properly iterate through the array 
	private static boolean inBetweenY(int rookX, int xLoc, int yLoc) { //parameters are the rookX, and the coordinates of the piece we are trying to see that is under attack (mostly the king)
		
		if (Math.abs(rookX-xLoc)==1) { //means pieces are right next to each other so there can be no possible block
			return true;
		}
		if (rookX<xLoc) {			
			for (int i=rookX; i<=xLoc;i++) { //to iterate down a column, we increment through the x values not the y values
				if (rookBlocked(i,yLoc,board[rookX][yLoc], board[xLoc][yLoc] )) { //this method call checks to see if the rook is being blocked at all
					return false;
				}
			}
		}else if (rookX>xLoc) {
			for (int i=xLoc;i<=rookX;i++) {
				if (rookBlocked(i,yLoc,board[rookX][yLoc],board[xLoc][yLoc])) {
					return false;
				}
			}
		}

		return true; //true if rook is attacking without any obstacles 
	}
	
	
	
	
	
	
	//returns true if the rook is blocked and false if it is not
	private static boolean rookBlocked(int a, int b, Piece rook, Piece other) {
		 //if this position is not empty, nor does is it equal to the current rook or the king we are trying to find then the rook is being blocked
		if (board[a][b].getValue() !=0 && board[a][b].equal(rook)==false && board[a][b].equal(other)==false ) {
			return true;
		}
		
		
		return false;
	}
	
	
	
	
	
	
	
	//finds this piece in a diagonal (for a queen and bishop)
	//it will check all 4 diagonals and if it is found in one of the four diagonals then that is true
	private static boolean isInDiagonal(int x, int y, Piece p) {
		ArrayList<Piece> diagonal1= new ArrayList <Piece>(); //each arraylist represents a diaognal 
		ArrayList<Piece> diagonal2= new ArrayList <Piece>(); ArrayList<Piece> diagonal3= new ArrayList <Piece>(); ArrayList<Piece> diagonal4= new ArrayList <Piece>();

		return findDiagonal1(x-1,y+1,diagonal1, p) || findDiagonal2(x-1,y-1,diagonal2, p) || findDiagonal3(x+1,y+1,diagonal3, p) || findDiagonal4(x+1,y-1,diagonal4, p);
	}
	
	
	
	
	
	
	//goes through the array of this diagonal 
	//if at any index of the list, there is not an empty piece with value 0 and the current index has a piece that is not a King then
	//returns false if it is blocked and returns true if not blocked
	private static boolean notBlocked(ArrayList <Piece> list, Piece p) {
		for (int i=0;i<list.size();i++) {
			if (list.get(i).getValue() !=0 && !(list.get(i).getLocX()==p.getLocX() && list.get(i).getLocY()==p.getLocY())) {
			
				return false;
			}
		
		}
	
		return true;
	}
	
	//this diagonal moves towards the top right
	//see code comments for diagonal4 method
	private static boolean findDiagonal1(int x, int y, ArrayList <Piece> arr, Piece p) { // x--> x position of the bishop y--> y position of the bishop Piece p--> the piece we are checking to see is under attack
		if (x==-1 || y==8) {
			return false;
		}
		else if (p.getLocX()==x && p.getLocY()==y) {
			
			return notBlocked(arr,p);
		}else {
			arr.add(board[x][y]);
			x--; y++;
			return findDiagonal1(x,y,arr,p);
		}

	}
	
	
	
	
	
	
	
	//this diagonal moves top left
	//see code comments for diagonal4 method
	private static boolean findDiagonal2(int x, int y, ArrayList <Piece> arr, Piece p) {
			if (x==-1 || y==-1) {
				return false;
			}
			else if ( p.getLocX()==x && p.getLocY()==y) {
				
				return notBlocked(arr,p);
			}else {
				arr.add(board[x][y]);
				x--; y--;
				return findDiagonal2(x,y,arr,p);
			}
	}
	
	
	
	
	
	
	//this diagonal moves bottom right
	//works the same way as the bottom method
	private static boolean findDiagonal3(int x, int y, ArrayList <Piece> arr, Piece p) {
		
		if (x==8 || y==8) {
			return false;
		}
		else if ( p.getLocX()==x && p.getLocY()==y) {
			
			return notBlocked(arr,p);
		}else {
			arr.add(board[x][y]);
			x++; y++;
			return findDiagonal3(x,y,arr,p);
		}
		
		

	}
	
	
	
	
	
	
	
	
	
	
	//this diagonal moves bottom left 
	//If it reaches the edge of the board (essentially up until out of bounds) without finding the piece (usually the King) then that means that the King is not in the bottom left diagonal
	//If it finds the piec3, it will return findBlocks(arr,p) essentially returning if there are any pieces blocking the path between the bishop (or queen) and the piece (usually the King) (look at code comments for the findBlocks method)
	//If the method does not reach the bottom edges of the board nor the king it will add the current position (bord[x][y]) into the list. in case we find the king in the diagonal and want to check that there was nothing between the piece and the bishop while we were searching for the king
	//then there is the change in the x and y index and then the recursive call
	private static boolean findDiagonal4(int x, int y, ArrayList <Piece> arr, Piece p) 
	{
		if (x==8 || y==-1) {
			return false;
		}
		else if (p.getLocX()==x && p.getLocY()==y) {
			
			return notBlocked(arr,p);
		}else {
			arr.add(board[x][y]);
			x++; y--;
			return findDiagonal4(x,y,arr,p);
		}
				
	}

	
	
	
	
	
	
	
	
	
	//first check if its movement does not go out of bounds
	//then we check to see if this movement allows it to attack the black king
	//pawns are the only pieces that are not symmetrical in their movements, in other words, the different color pawn, the different type of movement
	private static boolean whitePawnAttack(int x, int y, Piece p) { //x--> x position of the white pawn y--> y position of the white pawn Piece p--> represents the object which we are checking to see is under attack
		if (x-1>=0 && y-1>=0) {
			if (x-1==p.getLocX() && y-1==p.getLocY()) {
			
				return true;
			}
		}
		if (x-1>=0 && y+1<=7) {
			if (x-1==p.getLocX() && y+1==p.getLocY()) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	//black pawn movement
	//first check if its movement does not go out of bounds
	//then we check to see if this movement allows it to attack the white king
	private static boolean blackPawnAttack (int x, int y, Piece p) { //x--> black pawn x position y--> black pawn y position p--> Piece we are checking that is under attack
		if (x+1<=7 && y-1>=0) {
			if (x+1==p.getLocX() && y-1==p.getLocY()) {
		
				return true;
			}
		}
		if (x+1<=7 && y+1<=7) {
			if (x+1==p.getLocX() && y+1==p.getLocY()) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	//knightAttack checks to see if a knight is currently attacking p (of class Piece). Firstly, we need to see if any of the knights movements are not out of bounds (to prevent the OOB error from occuring)
	//Once it is confirmed that this position is in bounds, then we can check and see if the piece (usually the King) is on this position. If so, then we can say that Piece p is under attack by a knight
	private static boolean knightAttack(int x, int y, Piece p) { //x--> x position of the knight, y--> y position of the knight, p--> the piece we are checking to see is under attack
		if (x-2>=0 && y+1<=7) { 
			if (x-2==p.getLocX() && y+1==p.getLocY()) {
				
				return true;
			}
		}
		
		
		if (x-1>=0 && y+2<=7) {
			if (x-1==p.getLocX() && y+2==p.getLocY()) {	
				
				return true;
			}
		}
		
		
		if (x+1<=7 && y+2<=7) {
			if (x+1==p.getLocX() && y+2==p.getLocY()) {

				return true;
			}
		}
		
		
		if (x+2<=7 && y+1<=7) {
			if (x+2==p.getLocX() && y+1==p.getLocY()) {
			
				return true;
			}
		}
		
		
		if (x+2<=7 && y-1>=0) {
			if (x+2==p.getLocX() && y-1==p.getLocY()) {
			
				return true;
			}
		}
		
		
		if (x+1<=7 && y-2>=0) {
			if (x+1==p.getLocX() && y-2==p.getLocY()) {
		
			
				return true;
			}
		}
		
		
		if (x-1>=0 && y-2>=0) {
			if (x-1==p.getLocX() && y-2==p.getLocY()) {
			
				return true;
			}
			
		}
		
		
		if (x-2>=0 && y-1>=0) {
			if (x-2==p.getLocX() && y-1==p.getLocY()) {
			
				return true;
			}
		}
		return false;
	}
}