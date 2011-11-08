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
public class DefectWinStayLoseChangePlayer extends WinStayLoseChangePlayer implements Player
{

	static int PlayerCount = 0;

	public DefectWinStayLoseChangePlayer()
	{
		this("Defect Win Stay Lose Change " + PlayerCount++);
	}

	public DefectWinStayLoseChangePlayer( String id )
	{
		super(id, Ruleset.Defect);
	}
}
