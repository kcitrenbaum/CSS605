/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.clustering;

import java.util.ArrayList;
import local.CSS605.clustering.Cluster;
import local.CSS605.clustering.ClusteringAlgorithm;
import local.CSS605.clustering.DataPoint;

/**
 *
 * @author k
 */
public abstract class ClusteringBase implements ClusteringAlgorithm
{

	@Override
	public abstract ArrayList<Cluster> getClusters();

	@Override
	public abstract void run() throws Exception;

	ArrayList<DataPoint> data = new ArrayList();

	public ClusteringBase()
	{
	}


	@Override
	public ArrayList<DataPoint> getData()
	{
		return data;
	}

	@Override
	public void setData( ArrayList<DataPoint> data )
	{
		this.data = data;
	}
	
}
