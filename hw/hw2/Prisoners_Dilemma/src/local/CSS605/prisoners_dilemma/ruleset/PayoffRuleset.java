/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.ruleset;

import local.CSS605.prisoners_dilemma.player.Player;

/**
 *
 * @author k
 */
public abstract class PayoffRuleset extends Ruleset
{
	// T > R > P > S
	// 2 R > T + S

	protected int R;
	protected int S;
	protected int T;
	protected int P;
	boolean higherIsBetter = false;

	protected PayoffRuleset( int R, int S, int T, int P )
	{
		this.R = R;
		this.S = S;
		this.T = T;
		this.P = P;
		higherIsBetter = T > S;
	}


	/*		From Axelrod
	 * 		  Coop   Defect
	 *   		---------------
	 * Coop  	| R,R  | S,T |
	 *   		--------------
	 * Defect  	| T,S  | P,P  |
	 *   		---------------
	 */
	@Override
	public int[] getScores( Player p1, Player p2, int moveP1, int moveP2 )
	{
		int[] scores = new int[2];
		if ( Cooperate == moveP1 )
		{
			if ( Cooperate == moveP2 )
			{
				scores[0] = R;
				scores[1] = R;
			}
			else
			{
				scores[0] = S;
				scores[1] = T;
			}
		}
		else
		{
			if ( Cooperate == moveP2 )
			{
				scores[0] = T;
				scores[1] = S;
			}
			else
			{
				scores[0] = P;
				scores[1] = P;
			}
		}
		return scores;
	}

	@Override
	public int getBetterScore( int s1, int s2 )
	{
		return higherIsBetter
			? ( s1 > s2 )
				? s1
				: s2
			: ( s1 < s2 )
				? s1
				: s2;
	}
}
