/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma;

import java.util.ArrayList;

/**
 *
 * @author k
 */
public class MatchResults
{
	public String player1Id;
	public String player2Id;
	public int player1Score;
	public int player2Score;
	public ArrayList<RoundResults> roundResults = new ArrayList<RoundResults>();

	private MatchResults()
	{
	}

	public MatchResults( String player1Id, String player2Id, int player1Score, int player2Score )
	{
		this.player1Id = player1Id;
		this.player2Id = player2Id;
		this.player1Score = player1Score;
		this.player2Score = player2Score;
	}

	@Override
	public String toString()
	{
		return player1Id + " vs. " + player2Id + "; " + player1Score + "," + player2Score;
	}
}
