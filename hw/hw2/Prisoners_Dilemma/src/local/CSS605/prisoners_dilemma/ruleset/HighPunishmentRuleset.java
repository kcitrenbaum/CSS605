package local.CSS605.prisoners_dilemma.ruleset;
import local.CSS605.prisoners_dilemma.player.Player;

/**
 *
 * @author k
 */
public class HighPunishmentRuleset extends PayoffRuleset {
	/* 	          Coop   Defect     
 	 *   		---------------
	 * Coop  	| 1,1  | 50,0 | 
 	 *   		--------------
	 * Defect  	| 0,50 | 3,3  | 
 	 *   		---------------
	 */
	public HighPunishmentRuleset ()
	{
		super(1,50,0,3);
	}

	@Override
	public String getName() {
		return "High Punishment Ruleset (R=1,S=50,T=0,P=3) (Lower is better)";
	}
}
