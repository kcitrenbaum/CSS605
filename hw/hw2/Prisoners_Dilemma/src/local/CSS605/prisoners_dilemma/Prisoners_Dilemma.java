package local.CSS605.prisoners_dilemma;

import local.CSS605.prisoners_dilemma.player.classic.RandomPlayer;
import local.CSS605.prisoners_dilemma.player.classic.HumanPlayer;
import local.CSS605.prisoners_dilemma.player.classic.TitForTatPlayer;
import local.CSS605.prisoners_dilemma.player.classic.AlwaysCooperatePlayer;
import local.CSS605.prisoners_dilemma.player.classic.DefectTitForTatPlayer;
import local.CSS605.prisoners_dilemma.player.classic.CooperateWinStayLoseChangePlayer;
import local.CSS605.prisoners_dilemma.player.classic.DefectWinStayLoseChangePlayer;
import local.CSS605.prisoners_dilemma.player.classic.PavlovPlayer;
import local.CSS605.prisoners_dilemma.player.classic.AlwaysDefectPlayer;
import local.CSS605.prisoners_dilemma.player.classic.CooperateTitForTatPlayer;
import com.sun.tools.javac.util.Pair;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.CSS605.prisoners_dilemma.player.*;
import local.CSS605.prisoners_dilemma.player.classic.CooperateNaivePeacemakerPlayer;
import local.CSS605.prisoners_dilemma.player.classic.CooperateRepeatingPeacemakerPlayer;
import local.CSS605.prisoners_dilemma.player.classic.CooperateTitForTwoTatPlayer;
import local.CSS605.prisoners_dilemma.player.classic.DefectNaivePeacemakerPlayer;
import local.CSS605.prisoners_dilemma.player.classic.DefectRepeatingPeacemakerPlayer;
import local.CSS605.prisoners_dilemma.player.classic.DefectTitForTwoTatPlayer;
import local.CSS605.prisoners_dilemma.player.classic.NaivePeacemakerPlayer;
import local.CSS605.prisoners_dilemma.player.classic.RepeatingPeacemakerPlayer;
import local.CSS605.prisoners_dilemma.player.classic.TitForTwoTatPlayer;
import local.CSS605.prisoners_dilemma.player.classic.WinStayLoseChangePlayer;
import local.CSS605.prisoners_dilemma.ruleset.*;

/**
 *
 * @author k
 */
public class Prisoners_Dilemma
{

	public static String getUserInput( String prompt )
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		println(prompt);
		String t = "";
		try
		{
			t = reader.readLine();
		}
		catch ( IOException ex )
		{
			Logger.getLogger(Prisoners_Dilemma.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally
		{
		}
		return t;
	}
	static boolean addHuman = false;
	static boolean printAllActions = false;
	static boolean groupStrategies = false;
	static int playersPerStrategy = 1;
	static int numberOfRounds = 100;
	static String outputFilename = "";
	static BufferedWriter out = null;

	public static void main( String[] args ) throws IOException
	{
		for ( int i = 0; i < args.length; i++ )
		{
			String a = args[i].toLowerCase();
			if ( ( "-r".equals(a) || "-rounds".equals(a) ) && i++ < args.length )
			{
				numberOfRounds = Integer.parseInt(args[i]);
			}

			if ( "-h".equals(a) || "-addhuman".equals(a) )
			{
				addHuman = true;
			}

			if ( "-p".equals(a) || "-printallactions".equals(a) )
			{
				printAllActions = true;
			}

			if ( ( "-pc".equals(a) || "-players".equals(a) ) && i++ < args.length )
			{
				playersPerStrategy = Integer.parseInt(args[i]);
			}

			if ( ( "-gs".equals(a) || "-groupstrategies".equals(a) ) && i++ < args.length )
			{
				groupStrategies = true;
			}
			if ( ( "-o".equals(a) || "-output".equals(a) ) && i++ < args.length )
			{
				outputFilename = args[i];
				out = new BufferedWriter(new FileWriter(outputFilename));
				println("Outputting to: " + outputFilename);
			}
		}

		initRulesets();
		initPlayers(playersPerStrategy, addHuman, groupStrategies);
		HashMap<String, HashMap<String, Integer>> all_scores = new HashMap();

		int rsi = 0;
		for ( Ruleset rs : rulesets )
		{
			HashMap<String, Integer> scores = new HashMap();
			for ( Player p : players )
			{
				if ( p.getId().equals("Unnamed Human") )
				{
					p.setId(getUserInput("What's your name?"));
				}
				scores.put(p.getId(), 0);
			}

			Judge judge = new Judge(rs);
			for ( Pair<Player, Player> pr : getAllPlayerCombinations() )
			{
				MatchResults mr = judge.Play(pr.fst, pr.snd, numberOfRounds);
				scores.put(mr.player1Id, mr.player1Score + scores.get(mr.player1Id));
				log(renderMatchResults(mr, printAllActions));
			}
			all_scores.put("Round " + rsi++ + "(" + rs.getName() + ")", scores);
		}

		for ( String k : all_scores.keySet() )
		{
			ArrayList l = new ArrayList(all_scores.get(k).entrySet());
			java.util.Collections.sort(l, new comparator());
			log("\nFinal Scores: " + k + "\n");

			for ( Object i : l )
			{
				Map.Entry<String, Integer> f = (Map.Entry<String, Integer>) i;
				log("\t" + f.getKey() + "\t\t:, " + f.getValue() + "\n");
			}
		}
		if ( out != null )
		{
			out.flush();
			out.close();
		}
	}

	static void log( String s )
	{
		System.out.print(s);
		if ( out != null )
		{
			try
			{
				out.write(s);
				//out.write("\n");
			}
			catch ( IOException ex )
			{
				Logger.getLogger(Prisoners_Dilemma.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	static void println( String s )
	{
		System.out.println(s);
	}

	static String renderMatchResults( MatchResults mr, boolean includeRounds )
	{
		int p1t = 0;
		int p2t = 0;
		StringBuilder sb = new StringBuilder();
		String nl = "\n";
//		sb.append("Match Results").append(nl);
//		sb.append(mr.player1Id).append(",,").append(mr.player1Score).append(",");
//		sb.append(mr.player2Id).append(",,").append(mr.player2Score).append(nl);
		sb.append(mr.player1Id).append(",").append(mr.player2Id).append(",");
		sb.append(mr.player1Score).append(",").append(mr.player2Score).append(",");
		sb.append(mr.player1Id).append(":").append(mr.player2Id).append(nl);
		if ( includeRounds )
		{

			sb.append("P1 Action").append(",").append(" P1 Score ").append(",").append("P1 Running Total");
			sb.append(",");
			sb.append("P2 Action").append(",").append(" P2 Score ").append(",").append("P2 Running Total");
			sb.append(nl);
			for ( RoundResults rr : mr.roundResults )
			{
				p1t += rr.player1Score;
				p2t += rr.player2Score;
				sb.append(rr.player1Action).append(",");
				sb.append(rr.player1Score).append(",");
				sb.append(p1t).append(",");
				sb.append(rr.player2Action).append(",");
				sb.append(rr.player2Score).append(",");
				sb.append(p2t).append(nl);
			}
			sb.append(nl);
		}
		return sb.toString();
	}

	static class comparator implements Comparator
	{

		@Override
		public int compare( Object t, Object t1 )
		{
			Map.Entry<String, Integer> f = (Map.Entry<String, Integer>) t;
			Map.Entry<String, Integer> s = (Map.Entry<String, Integer>) t1;
			return s.getValue() - f.getValue();
		}
	}

	static ArrayList<Pair<Player, Player>> getAllPlayerCombinations()
	{
		ArrayList<Pair<Player, Player>> pairs = new ArrayList();
		for ( Player p : players )
		{
			for ( Player q : opponents )
			{
				pairs.add(new Pair<Player, Player>(p, q));
			}
		}
		return pairs;
	}
	static ArrayList<Player> players = new ArrayList();
	static ArrayList<Player> opponents = new ArrayList();

	static void initPlayers( int playerPerStrategyCount, boolean addHuman, boolean groupStrategies )
	{
		if ( addHuman )
		{
			players.add(new HumanPlayer());
		}
		for ( int i = 0; i < playerPerStrategyCount; ++i )
		{
			if ( groupStrategies )
			{
				players.add(new AlwaysCooperatePlayer("AlwaysCooperatePlayer"));
				players.add(new AlwaysDefectPlayer("AlwaysDefectPlayer"));
				players.add(new CooperateNaivePeacemakerPlayer("CooperateNaivePeacemakerPlayer"));
				players.add(new CooperateRepeatingPeacemakerPlayer("CooperateRepeatingPeacemakerPlayer"));
				players.add(new CooperateTitForTatPlayer("CooperateTitForTatPlayer"));
				players.add(new CooperateTitForTwoTatPlayer("CooperateTitForTwoTatPlayer"));
				players.add(new CooperateWinStayLoseChangePlayer("CooperateWinStayLoseChangePlayer"));
				players.add(new DefectNaivePeacemakerPlayer("DefectNaivePeacemakerPlayer"));
				players.add(new DefectRepeatingPeacemakerPlayer("DefectRepeatingPeacemakerPlayer"));
				players.add(new DefectTitForTatPlayer("DefectTitForTatPlayer"));
				players.add(new DefectTitForTwoTatPlayer("DefectTitForTwoTatPlayer"));
				players.add(new DefectWinStayLoseChangePlayer("DefectWinStayLoseChangePlayer"));
//				players.add(new NaivePeacemakerPlayer("NaivePeacemakerPlayer"));
				players.add(new PavlovPlayer("PavlovPlayer"));
//				players.add(new RandomPlayer("RandomPlayer"));
//				players.add(new RepeatingPeacemakerPlayer("RepeatingPeacemakerPlayer"));
//				players.add(new TitForTatPlayer("TitForTatPlayer"));
//				players.add(new TitForTwoTatPlayer("TitForTwoTatPlayer"));
//				players.add(new WinStayLoseChangePlayer("WinStayLoseChangePlayer"));
			}
			else
			{
				players.add(new AlwaysCooperatePlayer());
				players.add(new AlwaysDefectPlayer());
				players.add(new CooperateNaivePeacemakerPlayer());
				players.add(new CooperateRepeatingPeacemakerPlayer());
				players.add(new CooperateTitForTatPlayer());
				players.add(new CooperateTitForTwoTatPlayer());
				players.add(new CooperateWinStayLoseChangePlayer());
				players.add(new DefectNaivePeacemakerPlayer());
				players.add(new DefectRepeatingPeacemakerPlayer());
				players.add(new DefectTitForTatPlayer());
				players.add(new DefectTitForTwoTatPlayer());
				players.add(new DefectWinStayLoseChangePlayer());
//				players.add(new NaivePeacemakerPlayer());
				players.add(new PavlovPlayer());
//				players.add(new RandomPlayer());
//				players.add(new RepeatingPeacemakerPlayer());
//				players.add(new TitForTatPlayer());
//				players.add(new TitForTwoTatPlayer());
//				players.add(new WinStayLoseChangePlayer());
			}
		}

		opponents.add(new AlwaysCooperatePlayer("All C Opponent"));
		opponents.add(new AlwaysDefectPlayer("All D Opponent"));
		opponents.add(new CooperateNaivePeacemakerPlayer("Cooperate Naive Peacemaker Opponent"));
		opponents.add(new CooperateRepeatingPeacemakerPlayer("Cooperate Repeating Peacemaker Opponent"));
		opponents.add(new CooperateTitForTatPlayer("Cooperate Tit For Tat Opponent"));
		opponents.add(new CooperateTitForTwoTatPlayer("Cooperate Tit For TwoTat Opponent"));
		opponents.add(new CooperateWinStayLoseChangePlayer("Cooperate Win Stay Lose Change Opponent"));

		opponents.add(new DefectNaivePeacemakerPlayer("Defect Naive Peacemaker Opponent"));
		opponents.add(new DefectRepeatingPeacemakerPlayer("Defect Repeating Peacemaker Opponent"));
		opponents.add(new DefectTitForTatPlayer("Defect Tit For Tat Opponent"));
		opponents.add(new DefectTitForTwoTatPlayer("Defect Tit For TwoTat Opponent"));
		opponents.add(new DefectWinStayLoseChangePlayer("Defect Win Stay Lose Change Opponent"));
//		opponents.add(new NaivePeacemakerPlayer("Naive Peacemaker Opponent"));
		opponents.add(new PavlovPlayer("Pavlov Opponent"));
//		opponents.add(new RandomPlayer("Random Opponent"));
//		opponents.add(new RepeatingPeacemakerPlayer("Repeating Peacemaker Opponent"));
//		opponents.add(new TitForTatPlayer("Tit for Tat Opponent"));
//		opponents.add(new TitForTwoTatPlayer("Tit for Two Tat Opponent"));
//		opponents.add(new WinStayLoseChangePlayer("Win Stay Lose Change Opponent"));
		//opponents.add(new HumanPlayer("Human Opponent"));
	}
	static ArrayList<Ruleset> rulesets = new ArrayList<Ruleset>();

	static void initRulesets()
	{
		boolean add;
//		add = rulesets.add(new DefaultPunishmentRuleset());
//		add = rulesets.add(new AxelrodPayoffRuleset());
//		add = rulesets.add(new AxelrodPunishmentRuleset());
//		add = rulesets.add(new AxelrodPunishmentRuleset());
		add = rulesets.add(new AxelrodPunishmentRuleset());
//		add = rulesets.add(new FriendOrFoeRuleset());
	}
}
