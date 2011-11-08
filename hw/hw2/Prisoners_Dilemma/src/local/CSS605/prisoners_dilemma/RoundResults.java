/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma;

/**
 *
 * @author k
 */
public class RoundResults
{

	public String player1Id;
	public String player2Id;
	public int player1Action;
	public int player2Action;
	public int player1Score;
	public int player2Score;

	private RoundResults()
	{
	}

	public RoundResults( String player1Id, String player2Id, int player1Action, int player2Action, int player1Score, int player2Score )
	{
		this.player1Id = player1Id;
		this.player2Id = player2Id;
		this.player1Action = player1Action;
		this.player2Action = player2Action;
		this.player1Score = player1Score;
		this.player2Score = player2Score;
	}

	@Override
	public String toString()
	{
		return player1Id + " vs. " + player2Id + "; " + player1Action + ", " + player2Action + "; " + player1Score + "," + player2Score;
	}
}
