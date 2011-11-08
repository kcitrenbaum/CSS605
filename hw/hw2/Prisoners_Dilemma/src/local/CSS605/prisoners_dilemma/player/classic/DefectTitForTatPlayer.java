/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.classic;

import java.util.Random;
import local.CSS605.prisoners_dilemma.player.HistoryRecordingPlayer;
import local.CSS605.prisoners_dilemma.player.Player;
import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public class DefectTitForTatPlayer extends TitForTatPlayer implements Player
{
	static int PlayerCount = 0;

	public DefectTitForTatPlayer()
	{
		this("Defect Tit for Tat " + PlayerCount++);
	}

	public DefectTitForTatPlayer( String id )
	{
		super(id, Ruleset.Defect);
	}
}
