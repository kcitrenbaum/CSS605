/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.learning;

/**
 *
 * @author k
 */
public abstract class ActionPredictorBase implements ActionPredictor 
{
	public ActionPredictorBase(String id)
	{
		this.id=id;
	}
	
	String id;
	@Override
	public String getId()
	{
		return id;
	}
}
