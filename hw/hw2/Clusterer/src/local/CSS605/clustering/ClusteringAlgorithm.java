/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.clustering;

import java.util.ArrayList;

/**
 *
 * @author k
 */
public interface ClusteringAlgorithm
{
	ArrayList<DataPoint> getData();	
	void setData(ArrayList<DataPoint> data);

	ArrayList<Cluster> getClusters();
//	int getClusterCount();
//	void setClusters(ArrayList<Cluster> clusters);

//	void setClusterCount(int clusterCount);

	void run() throws Exception;
}
