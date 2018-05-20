package com.AlMundo.CallCenterDomains;

public class Call 
{

	private String callId;
    private Employee agent;
    private int duration;
    private String status;
    
    public Call (String callId, int duration, String status) 
    {
		this.callId = callId;
		this.duration = duration;
		this.status = status;
    }

	public String getCallId() 
	{
		return callId;
	}

	public void setCallId(String callId) 
	{
		this.callId = callId;
	}

	public Employee getAgent() 
	{
		return agent;
	}

	public void setAgent(Employee agent) 
	{
		this.agent = agent;
	}

	public int getDuration() 
	{
		return duration;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}
    
    
}
