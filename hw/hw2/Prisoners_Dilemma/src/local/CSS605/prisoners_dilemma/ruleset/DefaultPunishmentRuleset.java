package local.CSS605.prisoners_dilemma.ruleset;
import local.CSS605.prisoners_dilemma.player.Player;

/**
 *
 * @author k
 */
public class DefaultPunishmentRuleset extends PayoffRuleset {
	/* 	          Coop   Defect     
 	 *   		---------------
	 * Coop  	| 1,1  | 12,0 | 
 	 *   		--------------
	 * Defect  	| 0,12 | 3,3  | 
 	 *   		---------------
	 */
	public DefaultPunishmentRuleset()
	{
		super(1,12,0,3);
	}

	@Override
	public String getName() {
		return "Default Punishment Ruleset (R=1,S=12,T=0,P=3) (Lower is better)";
	}
}
