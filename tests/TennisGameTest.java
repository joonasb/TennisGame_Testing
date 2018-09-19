import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore() ;
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();			
	}
	
	@Test
	public void testTennisGame_Player1HasAdvantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		
		game.player1Scored();
		//Act
		String score = game.getScore();
		
		// Assert
		assertEquals("Player 1 advantage incorrect", "player1 has advantage", score);
	}
	
	@Test
	public void testTennisGame_Player2HasAdvantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		for(int index = 1; index <= 3; index++) {
	        game.player1Scored();
	    }
		
		for(int index = 1; index <= 3; index++) {
	        game.player2Scored();
	    }
		   
		game.player2Scored();
		   
		//Act
		String score = game.getScore();
		
		//Assert
		assertEquals("Player 2 advantage incorrect", "player2 has advantage", score);
	}
	
	@Test
	public void testTennisGame_Player1Wins_After_Advantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		for(int index = 1; index <= 3; index++) {
	        game.player1Scored();
	    }
		
		for(int index = 1; index <= 3; index++) {
	        game.player2Scored();
	    }
		
		game.player1Scored();
		game.player1Scored();
		
		//Act
		String score = game.getScore();

		// Assert
		assertEquals("Player 1 wins incorrect", "player1 wins", score);			
	}
	
	@Test
	public void testTennisGame_Player2Wins_After_Advantage() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
				
		for(int index = 1; index <= 3; index++) {
			game.player1Scored();
		}
				
		for(int index = 1; index <= 3; index++) {
			game.player2Scored();
		}
				
		game.player2Scored();
		game.player2Scored();
				
		//Act
		String score = game.getScore();

		// Assert
		assertEquals("Player 2 wins incorrect", "player2 wins", score);	
	}
	
	@Test
	public void testTennisGame_Player1Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		for(int index = 1; index <= 4; index++) {
			game.player1Scored();
		}
		
		//Act
		String score = game.getScore();
		
		//Assert
		assertEquals("Player 1 wins incorrect", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_Player2Wins() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		for(int index = 1; index <= 4; index++) {
			game.player2Scored();
		}
		
		//Act
		String score = game.getScore();
		
		//Assert
		assertEquals("Player 2 wins incorrect", "player2 wins", score);
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsAndPlayer1TriesToScore_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();			
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsAndPlayer2TriesToScore_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();			
	}
	
	@Test
	public void testTennisGame_Deuce_Advantage_Test() throws TennisGameException {
		/// First test case is deuce, then player 1 advantage, then deuce, then player 2 advantage and finally deuce
		
		//Arrange
		TennisGame game = new TennisGame();
		for(int index = 1; index <= 3; index++) {
			game.player1Scored();
		}
				
		for(int index = 1; index <= 3; index++) {
			game.player2Scored();
		}
		
		game.player1Scored();
		game.player2Scored();
		
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);	
		
		game.player1Scored();
		
		//Act2
		String score2 = game.getScore();
		// Assert2
		assertEquals("Player 1 advantage incorrect", "player1 has advantage", score2);
		
		game.player2Scored();
		
		//Act3
		String score3 = game.getScore();
		// Assert3
		assertEquals("Tie score incorrect", "deuce", score3);
		
		game.player2Scored();
		
		//Act4
		String score4 = game.getScore();
		//Assert4
		assertEquals("Player 2 advantage incorrect", "player2 has advantage", score4);
		
		game.player1Scored();
		
		//Act5
		String score5 = game.getScore();
		//Assert5
		assertEquals("Tie score incorrect", "deuce", score5);
		
		
	}
	
	@Test
	public void testTennisGame_normal_game_situation_test1() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		//Act
		String score = game.getScore();
		//Assert
		assertEquals("30 - 15 incorrect", "30 - 15", score);
	}
	
	@Test
	public void testTennisGame_normal_game_situation_test2() throws TennisGameException {
		//Arrange 
		TennisGame game = new TennisGame();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		String score = game.getScore();
		//Assert
		assertEquals("40 - 15 incorrect", "40 - 15", score);
	}
}
