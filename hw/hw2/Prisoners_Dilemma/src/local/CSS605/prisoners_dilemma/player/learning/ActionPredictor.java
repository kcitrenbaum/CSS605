/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.learning;

import java.util.ArrayList;
import local.CSS605.prisoners_dilemma.player.HistoryRecordingPlayer.HistoryItem;

/**
 *
 * @author k
 */
public interface ActionPredictor
{
	int predictAction(ArrayList<HistoryItem> history);	
	int predictNextRoundAction(ArrayList<HistoryItem> history, int myAction);	
	String getId();
}
