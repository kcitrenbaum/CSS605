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
public class CooperateNaivePeacemakerPlayer extends NaivePeacemakerPlayer
{

	static int PlayerCount = 0;

	public CooperateNaivePeacemakerPlayer()
	{
		this("Cooperate Naive Peacemaker " + PlayerCount++);
	}

	public CooperateNaivePeacemakerPlayer( String id )
	{
		super(id, Ruleset.Cooperate);
	}	
}
