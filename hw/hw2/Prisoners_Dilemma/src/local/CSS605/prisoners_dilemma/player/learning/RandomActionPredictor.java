/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.learning;

import java.util.ArrayList;
import java.util.Random;
import local.CSS605.prisoners_dilemma.player.HistoryRecordingPlayer.HistoryItem;
import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public class RandomActionPredictor extends ActionPredictorBase
{

	public RandomActionPredictor( )
	{
		this("Random Action Predictor");
	}

	
	public RandomActionPredictor( String id )
	{
		super(id);
	}

	@Override
	public int predictNextRoundAction( ArrayList<HistoryItem> history, int myAction )
	{
		return getRandomAction();
	}

	@Override
	public int predictAction( ArrayList<HistoryItem> history )
	{
		return getRandomAction(); 
	}

	protected static int getRandomAction()
	{
		return Ruleset.getRandomMove();
	}
}
