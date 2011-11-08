/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.classic;

import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public class CooperateRepeatingPeacemakerPlayer extends RepeatingPeacemakerPlayer
{
private static int PlayerCount = 0;

	public CooperateRepeatingPeacemakerPlayer()
	{
		this("Cooperate Repeating Peacemaker " + PlayerCount++);
	}

	public CooperateRepeatingPeacemakerPlayer( String id )
	{
		super(id, 5, Ruleset.Cooperate);
	}

	@Override
	public int doMakeMove()
	{
		return super.doMakeMove();
	}	
}
