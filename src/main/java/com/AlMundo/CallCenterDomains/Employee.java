/**
 * 
 */
package com.AlMundo.CallCenterDomains;

/**
 * @author Usuario
 *
 */
public class Employee {
	private String numId;
	private String charge ;
	


	public Employee (String numId, String charge)
	{
		this.numId = numId;
		this.charge = charge;
	}

	public void setId (String numId)
	{
		this.numId = numId;
	}

	public String getId ()
	{
		return numId;
	}

	public void setCharge (String charge)
	{
		this.charge = charge;
	}

	public String getCharge ()
	{
		return charge;
	}
}