/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.clustering;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import local.CSS605.clustering.distance.Distance;
import local.CSS605.clustering.distance.EUDistance;

/**
 *
 * @author k
 */
public class Cluster
{

	@Override
	public String toString()
	{
		return "Cluster{" + "id=" + id + ", centroid={x=" + centroid.x + ",y=" + centroid.y + "}, energy=" + energy + ",data=" + data + '}';
	}

	public String toCSV()
	{
		return id + ",Centroid," + centroid.x + "," + centroid.y;
	}

	public int getId()
	{
		return id;
	}
	static int clusterCount = 0;
	Distance distanceMeasure = new EUDistance();
	int id;
	double energy = 0.0;
	DataPoint centroid = new DataPoint("Centroid", 0, 0);
	ArrayList<DataPoint> data = new ArrayList();

	public void calculateCentroid()
	{
		if ( data == null )
		{
			data = new ArrayList();
		}
		if ( 0 == data.size() )
		{
			centroid.x = new Random().nextInt(100);
			centroid.y = new Random().nextInt(100);
			return;
		}
		int tX = 0;
		int tY = 0;

		for ( DataPoint p : data )
		{
			tX += p.x;
			tY += p.y;
		}
		centroid.x = tX / data.size();
		centroid.y = tY / data.size();
	}

	public ArrayList<DataPoint> getData()
	{
		return data;
	}

	public void setData( ArrayList<DataPoint> data )
	{
		this.data = data;
	}

	public void clearDataPoints()
	{
		if ( data == null )
		{
			data = new ArrayList();
		}
		data.clear();
	}

	public void addDataPoint( DataPoint p )
	{
		if ( data == null )
		{
			data = new ArrayList();
		}
		data.add(p);
	}

	public void removeDataPoint( DataPoint p )
	{
		if ( data == null )
		{
			data = new ArrayList();
			return;
		}
		data.remove(p);
	}

	public Cluster()
	{
		this(Integer.MIN_VALUE, null, null);
	}

	public Cluster( int id )
	{
		this(id, null, null);
	}

	public Cluster( Distance distanceMeasure )
	{
		this(Integer.MIN_VALUE, distanceMeasure, null);
	}

	public Cluster( DataPoint initialPoint )
	{
		this(Integer.MIN_VALUE, null, initialPoint);
	}

	public Cluster( int id, Distance distanceMeasure, DataPoint initialPoint )
	{
		if ( Integer.MIN_VALUE == id )
		{
			id = clusterCount;
		}
		this.id = id;
		if ( null == distanceMeasure )
		{
			distanceMeasure = new EUDistance();
		}
		this.distanceMeasure = distanceMeasure;
		if ( initialPoint == null )
		{
			initialPoint = new DataPoint("Centroid", new Random().nextInt(100), new Random().nextInt(100));
		}
		this.centroid = initialPoint;
		clusterCount++;
	}

	public double getEnergy()
	{
		return energy;
	}

	public double calculateEnergy()
	{
		double e = 0.0;
		for ( DataPoint p : data )
		{
			e += Math.pow(getDistanceFromCentroid(p), 2);
		}
		energy = e;
		return e;
	}

	public double getDistanceFromCentroid( DataPoint p )
	{
		return distanceMeasure.getDistance(centroid, p);
	}
}
