package realtimeproject;

import java.util.Random;
import java.util.Scanner;

class tictac
{
	static char[][]board;
	public tictac()
	{
		board=new char[3][3];
		initBoard();
	}
	void initBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
			board[i][j]=' ';	
			}
		}
	}
	static void displayboard()
	{
		System.out.println("-------------");
		for(int i=0;i<board.length;i++)
		{
			System.out.print("| ");
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println("-------------");		}
	}
	static void placeMark(int row,int col,char mark)
	{
		if(row>=0&&row<=2&&col>=0&&col<=2)
		{
			board[row][col]=mark;
		}
		else
		{
			System.out.println("Invalid Position");
		}
	}
    static boolean checkColWin()
	{
		for(int j=0;j<=2;j++)
		{
			if(board[0][j]!=' ' && board[0][j]==board[1][j]&&board[1][j]==board[2][j])
			{
				return true;
			}
		}
		return false;
	}
    static boolean checkrowWin()
   	{
   		for(int i=0;i<=2;i++)
   		{
   			if(board[i][0]!=' '&&board[i][0]==board[i][1]&&board[i][1]==board[i][2])
   			{
   				return true;
   			}
   		}
   		return false;
   	}
   static boolean checkDiognal()
    {
    	if(board[0][0]!=' '&&board[0][0]==board[1][1]&&board[1][1]==board[2][2]||board[0][2]!=' '&&board[0][2]==board[1][1]&&board[1][1]==board[2][0])
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }   


static boolean checkDraw()
{
	for(int i=0;i<=2;i++)
	{
		for(int j=0;j<=2;j++)
		{
			if(board[i][j]==' ')
			{
				return false;
			}
		}
	}
	return true;
}
}

abstract class player
{
	String name;
	char mark;
	
	abstract void makeMove();
	boolean isValid(int row , int col)
	{
	if(row>=0&&row<=2&&col>=0&&col<=2)	
	{
		if(tictac.board[row][col]==' ')
		{
			return true;
		}
	}
	return false;
	}
}



class HumanPlayer extends player
{
	String name;
	char mark;
	HumanPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
    }
	void makeMove()
	{
	Scanner scan=new Scanner(System.in);
	int row;
	int col;
	do 
	{
		System.out.println("Enter the row And col ");
		 row=scan.nextInt();
		 col=scan.nextInt();	
	}
	while(!isValid(row, col));
	
		tictac.placeMark(row, col, mark);
	}
	
}


class AIplayer extends player
{
	String name;
	char mark;
	AIplayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
    }
	void makeMove()
	{
	Scanner scan=new Scanner(System.in);
	int row;
	int col;
	do 
	{
		Random r=new Random();	
		row =r.nextInt(3);
		col =r.nextInt(3);
	}
	while(!isValid(row, col));
	
		tictac.placeMark(row, col, mark);
	}
	
}

public class tictactoe {
	public static void main(String[] args) {
      tictac t=new tictac();
      HumanPlayer p1=new HumanPlayer("bob",'X');
      HumanPlayer p2= new HumanPlayer("TAI",'0');
      HumanPlayer cp;
      cp=p1;
      while(true)
      {
    	  System.out.println(cp.name +"Turn");
          cp.makeMove();
          tictac.displayboard();
          if(tictac.checkColWin()||tictac.checkDiognal()||tictac.checkrowWin())
          {
        	  System.out.println(cp.name+" " +"has Won");
        	   break;
          }
          else if(tictac.checkDraw())
          {
        	  System.out.println("Game is Draw");
        	  break;
          }
          else
          {
        	 if(cp==p1)
        	 {
        		 cp=p2;
        	 }
        	 else
        	 {
        		 cp=p1;
        	 }
          }
      }

	}
}

