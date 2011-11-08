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
public class RepeatingPeacemakerPlayer extends TitForTatPlayer implements Player
{

	private static int PlayerCount = 0;
	protected int peacemakingFrequency = 5;

	public RepeatingPeacemakerPlayer()
	{
		this("");
		super.setId("Repeating Peacemaker (" + peacemakingFrequency + ") " + PlayerCount++);
	}

	public RepeatingPeacemakerPlayer( String id )
	{
		this(id, 5, Integer.MIN_VALUE);
		super.setId(id + " (" + peacemakingFrequency + ")");
	}

	public RepeatingPeacemakerPlayer( String id, int peacemakingFrequency, int defaultAction )
	{
		super(id, defaultAction);
		rnd = new Random();
		if ( peacemakingFrequency < 1 )
		{
			this.peacemakingFrequency = 1;
		}
		else
		{
			this.peacemakingFrequency = peacemakingFrequency;
		}
	}
	int playCount = 0;

	@Override
	public int doMakeMove()
	{
		HistoryItem lastMove = lastMove();
		return lastMove == null
			? ( Integer.MIN_VALUE == defaultAction ) ? getRandomAction() : defaultAction
			: ( ++playCount % peacemakingFrequency == 0 ) ? Ruleset.getNiceMove()
			: lastMove.opMove;
	}
}
