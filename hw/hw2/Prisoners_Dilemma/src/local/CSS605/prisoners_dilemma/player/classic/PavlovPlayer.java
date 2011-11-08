/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.classic;

import local.CSS605.prisoners_dilemma.player.HistoryRecordingPlayer;
import local.CSS605.prisoners_dilemma.player.Player;
import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public class PavlovPlayer extends HistoryRecordingPlayer implements Player
{
	static int PlayerCount = 0;

	public PavlovPlayer()
	{
		this("Pavlov " + PlayerCount++);
	}

	public PavlovPlayer( String id )
	{
		super(id);
	}

	@Override
	public int doMakeMove()
	{
		HistoryItem lastMove = lastMove();
		return lastMove == null ? Ruleset.Cooperate : lastMove.myMove == lastMove.opMove ? Ruleset.Cooperate : Ruleset.Defect;
	}
}
