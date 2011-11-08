/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.prisoners_dilemma.player;

import java.util.Random;
import local.CSS605.prisoners_dilemma.ruleset.DefaultPunishmentRuleset;
import local.CSS605.prisoners_dilemma.ruleset.Ruleset;

/**
 *
 * @author k
 */
public abstract class BasePlayer implements Player
{

	String id;
	protected float randomChance = 0;
	protected Random rnd = new Random();
	protected int defaultAction = Integer.MIN_VALUE;
	Ruleset ruleset = Ruleset.getDefaultRuleset();

	public BasePlayer( String id, float randomChance, int defaultAction )
	{
		this.id = id;
		this.randomChance = randomChance;
		this.defaultAction = defaultAction;
	}

	public BasePlayer( String id, float randomChance )
	{
		this(id, randomChance, Integer.MIN_VALUE);
	}

	public BasePlayer( String id )
	{
		this(id, 0);
	}

	public BasePlayer()
	{
		this("");
	}

	@Override
	public int makeMove()
	{
		return ( rnd.nextFloat() < randomChance )
			? getRandomAction()
			: doMakeMove();
	}

	abstract protected int doMakeMove();

	@Override
	public void SetScore( int myMove, int opMove, int myScore, int opScore, String opId )
	{
	}

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public void setId( String id )
	{
		this.id = id;
	}

	public float getRandomChance()
	{
		return randomChance;
	}

	public void setRandomChange( float chance )
	{
		this.randomChance = chance;
	}

	protected static int getRandomAction()
	{
		return Ruleset.getRandomMove();
	}

	@Override
	public Ruleset getRuleset()
	{
		return ruleset;
	}

	@Override
	public void setRuleset( Ruleset ruleset )
	{
		this.ruleset = ruleset;
	}
}
