/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.classic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.CSS605.prisoners_dilemma.Prisoners_Dilemma;
import local.CSS605.prisoners_dilemma.player.BasePlayer;
import local.CSS605.prisoners_dilemma.player.Player;
import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public class HumanPlayer extends BasePlayer implements Player
{

	static int PlayerCount = 0;

	public HumanPlayer()
	{
		this("Unnamed Human");
	}

	public HumanPlayer( String id )
	{
		super(id);
		System.out.println("Created human player");
	}
	Random rnd = new Random();

	@Override
	public int doMakeMove()
	{
		int move = Integer.MIN_VALUE;
		BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(System.in));
		while ( move == Integer.MIN_VALUE )
		{
			String t = Prisoners_Dilemma.getUserInput("Make a move [C]ooperate or [D]efect: ");
			System.out.println(" Got: " + t);
			String m = t.toLowerCase();
			if ( m.startsWith("c") )
			{
				move = Ruleset.Cooperate;
			}
			else if ( m.startsWith("d") )
			{
				move = Ruleset.Defect;
			}
			else
			{
				System.out.println("Huh? ");
			}
		}
		return move;
	}

	@Override
	public void SetScore( int myMove, int opMove, int myScore, int opScore, String opId )
	{
		System.out.println("Results: " + getId() + " vs. " + opId);
		System.out.println(" My move:" + ( ( myMove == Ruleset.Cooperate ) ? "Cooperate" : "Defect" ) + " Op move:" + ( ( opMove == Ruleset.Cooperate ) ? "Cooperate" : "Defect" ));
		System.out.println(" My score: " + myScore + " Op score: " + opScore);
	}
}
