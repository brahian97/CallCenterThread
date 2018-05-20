package com.AlMundo.CallCenterSimulator;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.AlMundo.CallCenterDomains.Call;
import com.AlMundo.CallCenterDomains.Employee;

public class Dispatcher implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(Dispatcher.class);

	private Call incomingCall;
	private Employee employee;
	private long initialTime;

	public Dispatcher(Call call, long initialTime, Employee employee) {
		this.incomingCall = call;
		this.initialTime = initialTime;
		this.employee = employee;
	}

	public void run() {
		log.info("El " + employee.getCharge() + " " + employee.getId() + " contesto la llamada "
				+ this.incomingCall.getCallId() + " EN EL TIEMPO: "
				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
		this.esperarXsegundos(this.incomingCall.getDuration());

		log.info("El " + employee.getCharge() + " " + employee.getId() + " HA TERMINADO LA LLAMADA "
				+ this.incomingCall.getCallId() + " EN EL TIEMPO: "
				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");

	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public Call getIncomingCall() {
		return incomingCall;
	}

	public void setIncomingCall(Call incomingCall) {
		this.incomingCall = incomingCall;
	}

	public long getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(long initialTime) {
		this.initialTime = initialTime;
	}

	public static Logger getLog() {
		return log;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
