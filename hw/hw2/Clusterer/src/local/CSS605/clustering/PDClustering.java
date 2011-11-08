/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.clustering;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.CSS605.clustering.kmeans.KMeans;

/**
 *
 * @author k
 */
public class PDClustering
{

	static String datafile = "data.csv";
	static String outfile = "clusters.csv";
	static int clusterCount = 0;
	static BufferedWriter out = null;

	public static void main( String[] args ) throws IOException, Exception
	{
		if ( args.length == 0 )
		{
			printUsage();
			return;
		}
		initOptions(args);
		ArrayList<DataPoint> data = readDataFromFile(datafile);
		HashMap<Integer, ArrayList<Cluster>> hclusters = new HashMap();

		if ( data.isEmpty() )
		{
			return;
		}
		if ( clusterCount == 0 )
		{
			for ( int i = 2; i < data.size() / 4; ++i )
			{
				KMeans clusterer = new KMeans();
				clusterer.setData(data);
				clusterer.setClusterCount(i);
				clusterer.run();
				ArrayList<Cluster> clusters = clusterer.getClusters();
				hclusters.put(i, clusters);
				outputClusters(clusters, outfile + "." + i + ".csv");
			}
			double bestE = Double.MAX_VALUE;
			int bestSize = 0;
			for ( Integer i : hclusters.keySet() )
			{
				double e = 0;
				for ( Cluster c : hclusters.get(i) )
				{
					e += c.calculateEnergy();
				}
				if ( e < bestE )
				{
					bestE = e;
					bestSize = i;
				}
				println("Cluster Size: " + i + " Energy: " + e);
			}
			println("Optimal Cluster Size: " + bestSize + " Energy: " + bestE);
			for ( Cluster c : hclusters.get(bestSize) )
			{
				println(c.toString());
			}
		}
		else
		{
			KMeans clusterer = new KMeans();

			clusterer.setData(data);
			clusterer.setClusterCount(clusterCount);
			clusterer.run();
			ArrayList<Cluster> clusters = clusterer.getClusters();
			outputClusters(clusters, outfile + ".csv");
		}
		/*
		clusterer.setClusterCount(6);
		clusterer.setData(data);
		clusterer.run();
		ArrayList<Cluster> clusters = clusterer.getClusters();
		outputClusters(clusters, outfile);
		 * 
		 */
	}

	static void printUsage()
	{
		println("Usage: ... -i $CSVFILE -o $OUTPUT_FILE_BASE [default=./clusters.csv] -cc $CLUSTER_COUNT");
		println(" If $CLUSTER_COUNT is not provided, PDClustering will attempt to find to optimal cluster size > 2 and < data.size() / 4 and will output all clusters to $OUTPUT_FILE_BASE.$CLUSTER_COUNT.csv");
	}

	static void initOptions( String[] args ) throws IOException
	{
		for ( int i = 0; i < args.length; i++ )
		{
			String a = args[i].toLowerCase();
			if ( ( "-i".equals(a) || "-datafile".equals(a) ) && i++ < args.length )
			{
				datafile = args[i];
			}

			if ( ( "-o".equals(a) || "-outfile".equals(a) ) && i++ < args.length )
			{
				outfile = args[i];
			}

			if ( ( "-cc".equals(a) || "-clustercount".equals(a) ) && i++ < args.length )
			{
				clusterCount = Integer.parseInt(args[i]);
			}
		}
		println("Reading from: " + datafile);
		println("Outputting to: " + outfile);
		println("Cluster Count: " + clusterCount);
	}

	static void println( String s )
	{
		System.out.println(s);
	}

	static void write( String s )
	{
		try
		{
			if ( out == null )
			{
				out = new BufferedWriter(new FileWriter(outfile));
			}
			out.write(s);
		}
		catch ( IOException ex )
		{
			Logger.getLogger(PDClustering.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	static ArrayList<DataPoint> readDataFromFile( String filename ) throws FileNotFoundException, IOException
	{
		ArrayList<DataPoint> points = new ArrayList();
		BufferedReader in = new BufferedReader(new FileReader(filename));
		
		ArrayList<String> fileContents = new ArrayList<String>();
		String ln = "";
		while ( ( ln = in.readLine() ) != null )
		{
			fileContents.add(ln);
		}

		while ( ( ln = in.readLine() ) != null )
		{
			String[] s = ln.split(",");
			if ( 3 != s.length )
			{
				continue;
			}
			points.add(new DataPoint(s[2], Integer.parseInt(s[0]), Integer.parseInt(s[1])));
		}

		return points;
	}

	static void outputClusters( ArrayList<Cluster> clusters, String filename ) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(filename));
		String sep = ",";
		String nl = "\n";
		String header = "Cluster,Datapoint,X,Y";
		out.write(header + nl);
		println(header);
		for ( Cluster c : clusters )
		{
			//	println(c.toString());
			String cid = new Integer(c.getId()).toString();
			out.write(c.toCSV() + nl);
			for ( DataPoint p : c.getData() )
			{
				String t = ( cid + sep + p.getLabel() + sep + p.x + sep + p.y + nl );
				out.write(t);
				//	println(t);
			}
		}
		out.flush();
		out.close();
	}
}
