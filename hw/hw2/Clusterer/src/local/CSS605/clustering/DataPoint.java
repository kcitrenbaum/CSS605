/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package local.CSS605.clustering;

import java.awt.Point;

/**
 *
 * @author k
 */
public class DataPoint extends Point
{

	@Override
	public String toString()
	{
		return "DataPoint{" + "label='" + label + "',point{x=" + x + ",y=" + y + "}}";
	}

	public DataPoint( String label, int x, int y )
	{
		this.label = label;
		this.x = x;
		this.y = y;
	}

	public DataPoint( String label, Point pt )
	{
		this.label = label;
		this.x = pt.x;
		this.y = pt.y;
	}
	String label;

	public String getLabel()
	{
		return label;
	}

	public void setLabel( String label )
	{
		this.label = label;
	}

	public void setX( int x )
	{
		this.x = x;
	}

	public void setY( int y )
	{
		this.y = y;
	}

	public double getDistance( DataPoint other )
	{
		return this.distance(other);
	}

	public double getSquaredDistance( DataPoint other )
	{
		return this.distanceSq(other);
	}

	public double getManhattenDistance( DataPoint other )
	{
		return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
	}
}
