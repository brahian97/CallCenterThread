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
	private ArrayList<Employee> operators;
	private ArrayList<Employee> supervisors;
	private ArrayList<Employee> directors;

//	public Dispatcher(Call call, long initialTime, Employee employee) {
//		this.incomingCall = call;
//		this.initialTime = initialTime;
//		this.employee = employee;
//	}
//
//	public void run() {
//		log.info("El " + employee.getCharge() + " " + employee.getId() + " contesto la llamada "
//				+ this.incomingCall.getCallId() + " EN EL TIEMPO: "
//				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
//		this.esperarXsegundos(this.incomingCall.getDuration());
//
//		log.info("El " + employee.getCharge() + " " + employee.getId() + " HA TERMINADO LA LLAMADA "
//				+ this.incomingCall.getCallId() + " EN EL TIEMPO: "
//				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
//
//	}
	
	public Dispatcher(Call call, long initialTime, ArrayList<Employee> operators, ArrayList<Employee> supervisors, ArrayList<Employee> directors) {
		this.incomingCall = call;
		this.initialTime = initialTime;
		this.operators = operators;
		this.supervisors = supervisors;
		this.directors = directors;
	}

	public void run() {
		
		if(!operators.isEmpty()) {
    		this.asignar(operators);
    	}else if(!supervisors.isEmpty()) {
    		this.asignar(supervisors);
    	}else if(!directors.isEmpty()) {
    		this.asignar(directors);
    	}else {
    		log.info("La llamada " + this.incomingCall.getCallId() + " esta en el espera, EN EL TIEMPO: "
    				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
    	}
	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	private void asignar(ArrayList<Employee> employees) {
		int position = employees.size()-1;
		Employee employee = employees.get(position);
		employees.remove(position);
		log.info("El " + employee.getCharge() + " " + employee.getId() + " contesto la llamada "
				+ this.incomingCall.getCallId() + " EN EL TIEMPO: "
				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
		this.esperarXsegundos(this.incomingCall.getDuration());

		log.info("El " + employee.getCharge() + " " + employee.getId() + " HA TERMINADO LA LLAMADA "
				+ this.incomingCall.getCallId() + " EN EL TIEMPO: "
				+ (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
		employees.add(employee);
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
