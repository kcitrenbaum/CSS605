package local.CSS605.prisoners_dilemma.ruleset;
import local.CSS605.prisoners_dilemma.player.Player;

/**
 *
 * @author k
 */
public class AxelrodPunishmentRuleset extends PayoffRuleset {

	/*		From Axelrod
	 * 		  Coop   Defect     
 	 *   		---------------
	 * Coop  	| l,1  | 5,0 | 
 	 *   		--------------
	 * Defect  	| 0,5  | 3,3  | 
 	 *   		---------------
	 */
	public AxelrodPunishmentRuleset()
	{
		super(1,5,0,3);
	}

	@Override
	public String getName() {
		return "Axelrod Punishment Ruleset (R=1,S=5,T=0,P=3) (Lower is better)";
	}
}
