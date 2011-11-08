/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import risk.Agent;
import agentStrategy.k.StrategySupport;
import java.util.ArrayList;
import risk.Territory;
import sim.util.Bag;

/**
 *
 * @author k
 */
public class K extends Agent
{

	public K( int id, int type )
	{
		super(id, type);
		empireName = "K.";
	}
	int r = 0;

	@Override
	protected void attack()
	{
		System.out.println("Empire:");
		for ( Object o : getEmpire() )
		{
			System.out.println(( (Territory) o ).getId());
		}
		this.attackedTerritoryID = 0;
		this.attackingSoldiers = 0;
		Territory attacker = this.myTerritory;
		attacker.produceSoldiers(attacker.getNatRes(), attacker.getPeasants());

		double s = attacker.getSoldiers() * 1;
		Bag availableTargets = StrategySupport.attackableTerritories(attacker, true);
		Territory target = null;
		while ( target == null && !availableTargets.isEmpty() )
		{
			Territory t = (Territory) availableTargets.pop();
			target = t;
			/*
			if ( t.getSoldiers() < s * 1.2 )
			{
				target = t;
			}
			 * 
			 */
		}

		if ( target == null )
		{
			return;
		}

		this.attackedTerritoryID = target.getId();
		this.attackingSoldiers = s;
	}

	public Bag getEmpire()
	{
		Bag b = new Bag();
		b.add(myTerritory);
		return StrategySupport.getAllSubordinates(myTerritory, b);
	}

	public Bag edgeOfEmpire()
	{
		return getEmpire();
		/*
		Bag edge = getEmpire();
		Bag nonEdge = new Bag();
		
		for ( Object o : edge )
		{
		Territory t = (Territory) o;
		if ( StrategySupport.enclosed(t) )
		{
		nonEdge.add(t);
		}
		}
		
		edge.removeAll(nonEdge);
		System.out.println("Edge of Empire:");
		for(Object o : edge)
		{
		System.out.println(((Territory)o).getId());
		}
		return edge;
		 * 
		 */
	}

	@Override
	protected void battleOutcome( long period, int attackerID, double soldiersAttack, int deffenderID, double soldiersDefend, boolean youWon )
	{
		super.battleOutcome(period, attackerID, soldiersAttack, deffenderID, soldiersDefend, youWon);
	}

	@Override
	protected void chooseTax()
	{
		this.tax = .5;
	}

	@Override
	protected void defend( Territory attacker, double soldiersAttacking )
	{
		Territory defender = this.myTerritory;
		double currentS = defender.getSoldiers();
		double allocatedS = 0;
		// create all we can
//		if ( currentS == 0 )
//		{
//		defender.produceSoldiers(defender.getNatRes(), defender.getPeasants());
//		}

		currentS = defender.getSoldiers();
		if ( currentS <= soldiersAttacking /2 )
		{
			allocatedS = currentS;
		}
		else
		{
			allocatedS = soldiersAttacking / 2;
		}

		this.defendingSoldiers = allocatedS;
	}

	@Override
	protected double[] getRetributions()
	{
		return super.getRetributions();
	}

	@Override
	public boolean isAcceptTrade()
	{
		return false;
	}

	@Override
	protected void setRetributionsAndBeneficiaries()
	{
		this.beneficiaries.clear();

		Bag subs = edgeOfEmpire();
		double soldiers = this.myTerritory.getSoldiers();
		double e = soldiers / subs.size();

		this.retributions = new double[subs.size()];
		for ( int i = 0; i < subs.size(); i++ )
		{
			this.retributions[i] = e;
		}
	}

	@Override
	protected void trade()
	{
		// clear array
		System.out.println(String.format("%1$d)  Soldiers: %2$f  NatRes: %3$f  Peasants: %4$f  Alpha: %5$f", this.myTerritory.getId(), this.myTerritory.getSoldiers(), this.myTerritory.getNatRes(), this.myTerritory.getPeasants(), this.myTerritory.getAlpha()));
		this.trade[0] = 0; //receiving territory id
		this.trade[1] = 0; // demand resource type
		this.trade[2] = 0; // demand amount
		this.trade[3] = 0; // offer resource type; 1 for natural resources, 2 for peasants, and 3 for soldiers)
		this.trade[4] = 0; // offer resource amount
	}

	@Override
	protected void acceptTrade( Territory offerer, double demand, int typeDemand, double offer, int typeOffer )
	{
		this.acceptTrade = false; // don't accept trades
	}

	@Override
	protected void tradeOutcome( long period, int proposerID, double[] tradeProposal, boolean tradeCompleted )
	{
		super.tradeOutcome(period, proposerID, tradeProposal, tradeCompleted);
	}
}
