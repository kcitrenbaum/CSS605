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
public class DefectRepeatingPeacemakerPlayer extends RepeatingPeacemakerPlayer
{
private static int PlayerCount = 0;

	public DefectRepeatingPeacemakerPlayer()
	{
		this("Defect Repeating Peacemaker " + PlayerCount++);
	}

	public DefectRepeatingPeacemakerPlayer( String id )
	{
		super(id, 5, Ruleset.Defect);
	}

	@Override
	public int doMakeMove()
	{
		return super.doMakeMove();
	}	
}
