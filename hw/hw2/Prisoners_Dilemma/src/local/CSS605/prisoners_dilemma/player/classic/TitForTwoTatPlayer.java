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
public class TitForTwoTatPlayer extends HistoryRecordingPlayer implements Player
{

	static int PlayerCount = 0;

	public TitForTwoTatPlayer()
	{
		super("Tit for Two Tat " + PlayerCount++);
	}

	public TitForTwoTatPlayer( String id )
	{
		super(id);
	}

	protected TitForTwoTatPlayer( String id, int defaultAction )
	{
		super(id, defaultAction);
	}

	@Override
	public int doMakeMove()
	{
		HistoryItem lastMove = lastMove();
		HistoryItem lastLastMove = previousMove(2);
		return lastMove == null || lastLastMove == null
			? ( Integer.MIN_VALUE == defaultAction ) ? getRandomAction() : defaultAction
			: lastMove.opMove;
	}
}
