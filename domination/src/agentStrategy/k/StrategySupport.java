/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agentStrategy.k;

import agents.K;
import risk.Territory;
import sim.util.Bag;

/**
 *
 * @author k
 */
public class StrategySupport
{

	public static Bag attackableTerritories( K ruler )
	{
		Bag empire = ruler.getEmpire();
		Bag attackable = new Bag();
		for ( Object o : empire )
		{
			for ( Object t : attackableTerritories((Territory) o, true) )
			{
				if ( !attackable.contains(t) )
				{
					attackable.add(t);
				}
			}
		}
		return attackable;
	}

	public static Bag attackableTerritories( Territory attacker, boolean ignoreAttackerTerritories )
	{
		Bag vt = new Bag();
		int m = getEmpire(attacker);

		for ( Object i : attacker.getNeighbors() )
		{
			Territory t = (Territory) i;
			if ( ignoreAttackerTerritories && isPartOfSameEmpire(m, t) )
			{
				continue;
			}
			vt.add(t);
		}

		return vt;
	}

	public static Bag getAllSubordinates( Territory root, Bag existing )
	{
		Bag unclaimedT = root.getSubordinates();
		unclaimedT.removeAll(existing);

		for ( Object o : unclaimedT )
		{
			existing.add(o);
			for ( Object i : getAllSubordinates((Territory) o, existing) )
			{
				if ( !existing.contains(i) )
				{
					existing.add(i);
				}
			}
		}

		return existing;
	}

	public static boolean isPartOfSameEmpire( Territory t1, Territory t2 )
	{
		return getEmpire(t1) == getEmpire(t2);
	}

	public static boolean isPartOfSameEmpire( int e, Territory t2 )
	{
		return e == getEmpire(t2);
	}

	public static boolean enclosed( Territory t )
	{
		int e = getEmpire(t);
		for ( Object o : t.getNeighbors() )
		{
			if ( !isPartOfSameEmpire(e, (Territory) o) )
			{
				return false;
			}
		}
		return true;
	}

	public static int getEmpire( Territory t )
	{
		if ( t.getSuperior() == null )
		{
			return t.getType();
		}
		return getEmpire(t.getSuperior());
	}
}
