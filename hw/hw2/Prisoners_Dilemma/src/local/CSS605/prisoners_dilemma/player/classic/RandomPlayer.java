/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.classic;

import local.CSS605.prisoners_dilemma.player.BasePlayer;
import local.CSS605.prisoners_dilemma.player.BasePlayer;
import local.CSS605.prisoners_dilemma.player.Player;
import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public class RandomPlayer extends BasePlayer implements Player
{

	static int PlayerCount = 0;

	public RandomPlayer()
	{
		this("Random Player " + PlayerCount++);
	}

	public RandomPlayer( String id )
	{
		super(id);
	}

	@Override
	public int doMakeMove()
	{
		return getRandomAction();
	}
}
