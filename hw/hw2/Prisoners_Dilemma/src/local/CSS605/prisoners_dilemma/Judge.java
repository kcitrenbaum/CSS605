package local.CSS605.prisoners_dilemma;

import java.util.ArrayList;
import local.CSS605.prisoners_dilemma.player.Player;
import local.CSS605.prisoners_dilemma.ruleset.DefaultPunishmentRuleset;
import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public class Judge
{

	Ruleset ruleset = null;
	ArrayList<RoundResults> results = new ArrayList();

	public Judge()
	{
		this(new DefaultPunishmentRuleset());
	}

	public Judge( Ruleset ruleset )
	{
		this.ruleset = ruleset;
	}

	public MatchResults Play( Player p1, Player p2, int rounds )
	{
		MatchResults mr = new MatchResults(p1.getId(), p2.getId(), 0, 0);
		for ( int i = 0; i < rounds; ++i )
		{
			RoundResults rr = playRound(p1, p2);
			results.add(rr);
			mr.player1Score += rr.player1Score;
			mr.player2Score += rr.player2Score;
			mr.roundResults.add(rr);
			p1.SetScore(rr.player1Action, rr.player2Action, rr.player1Score, rr.player2Score, rr.player2Id);
			p2.SetScore(rr.player2Action, rr.player1Action, rr.player2Score, rr.player1Score, rr.player1Id);
		}
		return mr;
	}

	public RoundResults playRound( Player p1, Player p2 )
	{
		int p1Action = p1.makeMove();
		int p2Action = p2.makeMove();
		int[] scores = ruleset.getScores(p1, p2, p1Action, p2Action);
		return new RoundResults(p1.getId(), p2.getId(), p1Action, p2Action, scores[0], scores[1]);
	}
}
