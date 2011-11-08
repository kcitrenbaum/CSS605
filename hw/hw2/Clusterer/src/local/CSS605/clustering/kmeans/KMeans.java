/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.clustering.kmeans;

import local.CSS605.clustering.ClusteringBase;
import java.awt.Point;
import java.util.ArrayList;
import local.CSS605.clustering.Cluster;
import local.CSS605.clustering.ClusteringAlgorithm;
import local.CSS605.clustering.DataPoint;

/**
 *
 * @author k
 */
public class KMeans extends ClusteringBase implements ClusteringAlgorithm
{

	double epsilon = 0.1;
	int maxRuns = 10000;
	ArrayList<Cluster> clusters = new ArrayList();

	@Override
	public void run() throws Exception
	{
		if ( null == getData())
		{
			throw new Exception("Data cannot be null");
		}
		if ( null == clusters )
		{
			throw new Exception("Clusters cannot be null");
		}
		if ( 0 == clusters.size() )
		{
			throw new Exception("Must initialize cluster count");
		}
		setClusters(doCluster(getData(), clusters.size(), maxRuns, epsilon));
	}
	/*
	void run( int maxRuns )
	{
	double lastEnergy = Double.MAX_VALUE;
	double stepEnergy = Double.MAX_VALUE;
	int runs = 0;
	double d = 0.0;
	do
	{
	stepEnergy = step();
	d = Math.pow(lastEnergy - stepEnergy, 2);
	lastEnergy = stepEnergy;
	}
	while ( d > epsilon && runs++ < maxRuns );
	}
	 * 
	double step()
	{
	for ( Cluster c : clusters )
	{
	c.clearPoints();
	}
	for ( Point p : data )
	{
	closestCluster(clusters, p).addPoint(p);
	}
	double energy = 0;
	for ( Cluster c : clusters )
	{
	energy += c.calculateEnergy();
	}
	return energy;
	}
	 * 
	 */

	static ArrayList<Cluster> doCluster( ArrayList<DataPoint> data, int clusterCount, int maxRuns, double eplison )
	{
		ArrayList<Cluster> clusters = new ArrayList();
		if ( clusterCount != clusters.size() )
		{
			clusters.clear();
			for ( int i = 0; i < clusterCount; i++ )
			{
				clusters.add(new Cluster());
			}
		}

		double lastEnergy = Double.MAX_VALUE;
		double stepEnergy = Double.MAX_VALUE;
		int runs = 0;
		double d = 0.0;
		do
		{
			for ( Cluster c : clusters )
			{
				c.clearDataPoints();
			}
			for ( DataPoint p : data )
			{
				closestCluster(clusters, p).addDataPoint(p);
			}
			stepEnergy=0;
			for(Cluster c: clusters)
			{
				c.calculateCentroid();
				stepEnergy+=c.calculateEnergy();
			}
			d = Math.pow(lastEnergy - stepEnergy, 2);
			System.out.println("Run=" + runs + " Energy="+stepEnergy + " D=" + d);
			lastEnergy = stepEnergy;
		}
		while ( d > eplison && runs++ < maxRuns );
		return clusters;
	}

	static Cluster closestCluster( ArrayList<Cluster> clusters, DataPoint p )
	{
		Cluster b = clusters.get(0);
		double bd = Double.MAX_VALUE;
		for ( Cluster c : clusters )
		{
			double t = c.getDistanceFromCentroid(p);
			if ( t < bd )
			{
				bd = t;
				b = c;
			}
		}
		return b;
	}

	@Override
	public ArrayList<Cluster> getClusters()
	{
		return clusters;
	}

	public void setClusters( ArrayList<Cluster> clusters )
	{
		this.clusters = clusters;
	}

	public int getClusterCount()
	{
		return clusters.size();
	}

	public void setClusterCount( int clusterCount )
	{
		if ( null == clusters )
		{
			clusters = new ArrayList();
		}
		clusters.clear();
		for ( int i = 0; i < clusterCount; ++i )
		{
			clusters.add(new Cluster());
		}
	}

	public double getEplison()
	{
		return epsilon;
	}

	public void setEplison( double eplison )
	{
		this.epsilon = eplison;
	}

	public int getMaxRuns()
	{
		return maxRuns;
	}

	public void setMaxRuns( int maxRuns )
	{
		this.maxRuns = maxRuns;
	}
}
