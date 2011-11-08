/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player.classic;

import local.CSS605.prisoners_dilemma.player.HistoryRecordingPlayer;
import local.CSS605.prisoners_dilemma.player.Player;
import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public class CooperateWinStayLoseChangePlayer extends WinStayLoseChangePlayer implements Player
{

	private static int PlayerCount = 0;

	public CooperateWinStayLoseChangePlayer()
	{
		this("Cooperate Win Stay Lose Change " + PlayerCount++);
	}

	public CooperateWinStayLoseChangePlayer( String id )
	{
		super(id, Ruleset.Cooperate);
	}
}
