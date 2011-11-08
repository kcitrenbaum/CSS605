/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.ruleset;
import java.util.Random;
import local.CSS605.prisoners_dilemma.player.Player;

/**
 *
 * @author k
 */
abstract public class Ruleset 
{
/*
	 public enum Action
	{
		Cooperate,
		Defect
	}
* 
* 
*/
	public static final int Cooperate = 0;
	public static final int Defect = 1;

	static final int[] actions = {Cooperate, Defect};

	public static int[] getActions(){return actions;}

	public abstract int[] getScores(Player p1, Player p2, int moveP1, int moveP2);
	public abstract String getName();

	public static int getRandomMove(){
		return actions[new Random().nextInt(actions.length)];
	}

	public static int getNiceMove(){
		return Cooperate;
	}

	public static int getNonniceMove(){
		return Defect;
	}

	public abstract int getBetterScore(int s1, int s2);

	static Ruleset defaultRuleset = new DefaultPunishmentRuleset();
	public static Ruleset getDefaultRuleset(){
		return defaultRuleset;
	}
}
