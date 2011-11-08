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
public class TitForTatPlayer extends HistoryRecordingPlayer implements Player
{

	private static int PlayerCount = 0;

	public TitForTatPlayer()
	{
		super("Tit for Tat " + PlayerCount++);
	}

	public TitForTatPlayer( String id )
	{
		super(id);
	}

	protected TitForTatPlayer( String id, int defaultAction )
	{
		super(id, defaultAction);
	}

	@Override
	public int doMakeMove()
	{
		HistoryItem lastMove = lastMove();
		return lastMove == null
			? ( Integer.MIN_VALUE == defaultAction ) ? getRandomAction() : defaultAction
			: lastMove.opMove;
	}
}
