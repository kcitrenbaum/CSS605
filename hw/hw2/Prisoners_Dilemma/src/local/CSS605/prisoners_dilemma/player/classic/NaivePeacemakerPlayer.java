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
public class NaivePeacemakerPlayer extends TitForTatPlayer implements Player
{

	private static int PlayerCount = 0;
	protected float peacemakingFrequencyChance = .2f;

	public NaivePeacemakerPlayer()
	{
		this("");
		super.setId("Naive Peacemaker (" + peacemakingFrequencyChance + ") " + PlayerCount++);
	}

	public NaivePeacemakerPlayer( String id )
	{
		this(id, .2f, Integer.MIN_VALUE);
	}

	public NaivePeacemakerPlayer( String id, int defaultAction )
	{
		this(id, .2f, defaultAction);
	}

	public NaivePeacemakerPlayer( String id, float chance, int defaultAction )
	{
		super(id, defaultAction);
		rnd = new Random();
		this.peacemakingFrequencyChance = chance;
		super.setId(id + " (" + peacemakingFrequencyChance + ") ");
	}

	@Override
	public int doMakeMove()
	{
		HistoryItem lastMove = lastMove();
		return lastMove == null
			? ( Integer.MIN_VALUE == defaultAction ) ? getRandomAction() : defaultAction
			: ( peacemakingFrequencyChance < rnd.nextFloat() ) ? Ruleset.getNiceMove()
			: lastMove.opMove;
	}
}
