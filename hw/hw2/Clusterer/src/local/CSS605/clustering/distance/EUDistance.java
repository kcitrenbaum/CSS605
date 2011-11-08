/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.clustering.distance;

import java.awt.Point;
import local.CSS605.clustering.DataPoint;

/**
 *
 * @author k
 */
public class EUDistance implements Distance
{
	@Override
	public double getDistance( DataPoint A, DataPoint B )
	{
		return A.getDistance(B);
	}
}
