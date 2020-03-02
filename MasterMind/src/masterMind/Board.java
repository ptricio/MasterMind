/**
 * Author: Pat Hurley
 * MasterMind
 */

package masterMind;

import java.util.Arrays;
import java.util.Random;


/**
 * This class is going to check all the 
 * players moves to see if they have won
 * or what pegs to place
 * @author pat
 *
 */
public class Board {
	private Ball[] balls = {Ball.BLACK,Ball.WHITE,Ball.BLUE,Ball.RED,Ball.GREEN,Ball.YELLOW};
	private int attempts = 0;
	private int maxAttempts = 10;
	private Ball[] code = new Ball[4];
	private Ball guess;
	private int blackPeg = 0;
	private int whitePeg = 0;
	private int[] result = new int[2];
	
	/**
	 * This will check the players guess against
	 * the answer code after every submission
	 * then return the number of pegs and their color
	 */
	public int[] checkCode(Ball[] guess ) {
		blackPeg = 0;
		whitePeg = 0;
		boolean[] checked = new boolean[4];
		if (attempts <= maxAttempts) {
			for(int i = 0; i < guess.length; i++) {
				if(guess[i] == code[i]) {
					blackPeg++;
					checked[i] = true;
				}
				for(int j = 0; j < guess.length; j++) {
					if(checked[i]) {
						continue;
					}
					if(guess[i] == code[j]) {
						whitePeg++;
						checked[j] = true;
					}
				}
			
			}	
		}
		
		attempts ++;
		result[0] = blackPeg;
		result[1] = whitePeg;
		return result;
	}
	
	/**
	 * This sets what the players guess
	 * was on the button to then set the
	 * buttons color
	 */
	public void setGuess(Ball temp) {
		guess = temp;
	}
	
	/**
	 * returns the players guess to 
	 * set the buttons color
	 */
	public Ball getGuess() {
		return guess;
	}
	
	/**
	 * This will return the number of attempts to see
	 * if the player has won or lost
	 */
	public int getAttempts() {
		return attempts;
	}
	
	/**
	 * This will check for a player win
	 * based off of a randomly generated answer code
	 */
	public boolean checkWin(Ball[] guess) {
		if(Arrays.deepEquals(guess, code)) {
			return true;
		}else
			return false;
	}
	
	/**
	 * Returns the correct color for the
	 * correct position
	 */
	public Ball getCode(int i) {
		return code[i];
	}
	
	/**
	 * This will randomly generate our answer code
	 * at the start of every game.
	 */
	Random rand = new Random();
	public Ball[] generateCode() {
		for(int i = 0; i < code.length; i++) {
			code[i] = balls[rand.nextInt(6)];
		}
		Arrays.toString(code);
		return code;
	}
	
	
	
}
