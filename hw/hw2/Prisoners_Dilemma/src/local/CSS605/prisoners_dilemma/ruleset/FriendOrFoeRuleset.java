package local.CSS605.prisoners_dilemma.ruleset;
import local.CSS605.prisoners_dilemma.player.Player;

/**
 *
 * @author k
 */
public class FriendOrFoeRuleset extends PayoffRuleset {
	public FriendOrFoeRuleset()
	{
		super(1,0,2,0);
	}
	@Override
	public String getName() {
		return "Friend or Foe Reward Ruleset (R=1,S=0,T=2,P=0) (Higher is better)";
	}
}
