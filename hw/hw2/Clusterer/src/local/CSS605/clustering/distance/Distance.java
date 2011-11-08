/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.clustering.distance;

import local.CSS605.clustering.DataPoint;

/**
 *
 * @author k
 */
public interface Distance
{
	double getDistance( DataPoint A, DataPoint B );
}
