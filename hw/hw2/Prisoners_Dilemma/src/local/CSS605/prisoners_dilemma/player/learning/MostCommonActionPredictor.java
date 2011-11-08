/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.learning;

import java.util.ArrayList;
import local.CSS605.prisoners_dilemma.player.HistoryRecordingPlayer.HistoryItem;
import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public class MostCommonActionPredictor extends ActionPredictorBase
{

	public MostCommonActionPredictor()
	{
		super("Most Common Action Predictor");
	}

	@Override
	public int predictAction( ArrayList<HistoryItem> history )
	{
		if ( history.isEmpty() )
		{
			return Ruleset.Defect;
		}

		int[] actions = Ruleset.getActions();
		int[] counts = new int[actions.length];
		for ( int i : actions )
		{
			counts[i] = 0;
		}

		for ( HistoryItem i : history )
		{
			counts[i.opMove]++;
		}

		int max = 0;
		for ( int i : counts )
		{
			if ( counts[i] > counts[max] )
			{
				max = i;
			}
		}

		return max;
	}

	@Override
	public int predictNextRoundAction( ArrayList<HistoryItem> history, int myAction )
	{
		return predictAction(history);
	}
}
