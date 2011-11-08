/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player;

import java.util.ArrayList;

/**
 *
 * @author k
 */
public abstract class HistoryRecordingPlayer extends BasePlayer implements Player
{

	String lastOp = "";
	protected ArrayList<HistoryItem> history = new ArrayList<HistoryItem>();

	public class HistoryItem
	{

		public int myMove;
		public int opMove;
		public int myScore;
		public int opScore;

		private HistoryItem()
		{
		}

		public HistoryItem( int myMove, int opMove, int myScore, int opScore )
		{
			this.myMove = myMove;
			this.opMove = opMove;
			this.myScore = myScore;
			this.opScore = opScore;
		}
	}

	public HistoryRecordingPlayer( String id )
	{
		super(id, 0, Integer.MIN_VALUE);
	}

	public HistoryRecordingPlayer( String id, float randomChance )
	{
		super(id, randomChance);
	}

	public HistoryRecordingPlayer( String id, int defaultAction )
	{
		super(id, 0, defaultAction);
	}

	public HistoryRecordingPlayer( String id, float randomChance, int defaultAction )
	{
		super(id, randomChance, defaultAction);
	}

	public HistoryItem previousMove( int stepsBack )
	{
		if ( history.size() < stepsBack )
		{
			return null;
		}
		return history.get(history.size() - stepsBack);
	}

	public HistoryItem lastMove()
	{
		return previousMove(1);
	}

	@Override
	public void SetScore( int myMove, int opMove, int myScore, int opScore, String opId )
	{
		if ( !lastOp.equals(opId) )
		{
			history.clear();
		}
		history.add(new HistoryItem(myMove, opMove, myScore, opScore));
	}
}
