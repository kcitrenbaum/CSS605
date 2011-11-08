/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player;

import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public interface Player
{

	String getId();

	void setId( String id );

	int makeMove();

	void SetScore( int myMove, int opMove, int myScore, int opScore, String opId );

	Ruleset getRuleset();
	void setRuleset(Ruleset rs);
}