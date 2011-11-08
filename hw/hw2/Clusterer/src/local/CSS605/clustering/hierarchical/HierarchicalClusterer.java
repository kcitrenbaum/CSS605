/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.clustering.hierarchical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import local.CSS605.clustering.Cluster;
import local.CSS605.clustering.ClusteringBase;
import local.CSS605.clustering.DataPoint;

/**
 *
 * @author k
 */
public class HierarchicalClusterer extends ClusteringBase
{
	/*
	 * 
	 * Distance measures
	 * 
	 * Average (centroid) -> average-link
	 * Nearest -> Single Link
	 * Farthest -> Complete Link
	 */

	@Override
	public ArrayList<Cluster> getClusters()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void run() throws Exception
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	List<Cluster> clusterClusters(List<Cluster> cluster)
	{
		List<Cluster> n = new LinkedList();
						
		return n;
	}

	ArrayList<DataPoint> data;
	HashMap<DataPoint, HashMap<DataPoint, Double>> matrix;

	HashMap<DataPoint, HashMap<DataPoint, Double>> getDistanceMatrix( ArrayList<DataPoint> data )
	{
		Queue<DataPoint> d = new LinkedList();
		HashMap<DataPoint, HashMap<DataPoint, Double>> m = new HashMap();
		while ( d.size() > 0 )
		{
			DataPoint p1 = d.remove();
			HashMap<DataPoint, Double> row = new HashMap();
			for ( DataPoint p2 : d )
			{
				row.put(p2, p1.getDistance(p2));
			}
			m.put(p1,row);
		}
		return m;
	}
}
