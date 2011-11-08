/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.learning;

/**
 *
 * @author k
 */
public class CommonActionPredictivePlayer extends PredictivePlayer
{
	public CommonActionPredictivePlayer()
	{
		super(new MostCommonActionPredictor());
	}
}
