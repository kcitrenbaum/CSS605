package local.CSS605.prisoners_dilemma.ruleset;
import local.CSS605.prisoners_dilemma.player.Player;

/**
 *
 * @author k
 */
public class AxelrodPayoffRuleset extends PayoffRuleset {
	public AxelrodPayoffRuleset()
	{
		super(3,0,5,1);
	}
	@Override
	public String getName() {
		return "Axelrod Reward Ruleset (R=3,S=0,T=5,P=1) (Higher is better)";
	}
}
