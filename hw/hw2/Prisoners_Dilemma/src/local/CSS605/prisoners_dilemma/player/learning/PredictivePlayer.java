/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.learning;

import local.CSS605.prisoners_dilemma.player.HistoryRecordingPlayer;
import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public class PredictivePlayer extends HistoryRecordingPlayer
{
	ActionPredictor predictor;

	public PredictivePlayer( String id, ActionPredictor predictor )
	{
		super(id);
		this.predictor = predictor;
	}

	public PredictivePlayer( ActionPredictor predictor )
	{
		this("Predictive Player: ", predictor);
	}

	public PredictivePlayer()
	{
		this("Predictive Player: ", new RandomActionPredictor());
	}

	@Override
	public int doMakeMove()
	{
		int action = predictor.predictAction(history);
		System.out.println("Predicted: " + action);
		return getMove(action);
	}

	protected int getMove(int predictedOpMove)
	{
		if(predictedOpMove == Ruleset.Cooperate)
		{
			return Ruleset.Defect;
		}
		return Ruleset.Cooperate;
	}
}
