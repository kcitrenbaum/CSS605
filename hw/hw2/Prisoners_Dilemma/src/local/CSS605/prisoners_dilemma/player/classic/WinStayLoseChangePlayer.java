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
public class WinStayLoseChangePlayer extends HistoryRecordingPlayer implements Player
{

	private static int PlayerCount = 0;
	protected int defaultAction;

	public WinStayLoseChangePlayer()
	{
		this("Win Stay Lose Change " + PlayerCount++);
	}

	public WinStayLoseChangePlayer( String id )
	{
		this(id, Integer.MIN_VALUE);
	}

	protected WinStayLoseChangePlayer( String id, int defaultAction )
	{
		super(id);
		this.defaultAction = defaultAction;
	}

	@Override
	public int doMakeMove()
	{
		HistoryItem lastMove = lastMove();
		return lastMove == null
			? ( Integer.MIN_VALUE == defaultAction ) 
				? getRandomAction() 
				: defaultAction
			: (lastMove.myScore == getRuleset().getBetterScore(lastMove.myScore , lastMove.opScore )) 
				? lastMove.myMove
				: Ruleset.Cooperate == lastMove.myMove 
					? Ruleset.Defect 
					: Ruleset.Cooperate;
	}
}
