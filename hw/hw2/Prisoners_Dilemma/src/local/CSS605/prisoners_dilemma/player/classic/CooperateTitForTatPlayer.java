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
public class CooperateTitForTatPlayer extends TitForTatPlayer implements Player
{

	private static int PlayerCount = 0;

	public CooperateTitForTatPlayer()
	{
		this("Cooperate Tit for Tat " + PlayerCount++);
	}

	public CooperateTitForTatPlayer( String id )
	{
		super(id, Ruleset.Cooperate);
	}

	@Override
	public int doMakeMove()
	{
		return super.doMakeMove();
	}
	
	
}
