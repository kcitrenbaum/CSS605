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
public class DefectNaivePeacemakerPlayer extends NaivePeacemakerPlayer
{

	static int PlayerCount = 0;

	public DefectNaivePeacemakerPlayer()
	{
		this("Defect Naive Peacemaker " + PlayerCount++);
	}

	public DefectNaivePeacemakerPlayer( String id )
	{
		super(id, Ruleset.Defect);
	}	
}
