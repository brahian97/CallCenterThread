package com.AlMundo.CallCenter.Main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.AlMundo.CallCenterDomains.*;
import com.AlMundo.CallCenterSimulator.Dispatcher;

public class AppManager {

	private static final Logger log = LoggerFactory.getLogger(Dispatcher.class);
	
	private ArrayList<Call> incomingCalls;
	private ArrayList<Employee> operators;
	private ArrayList<Employee> supervisors;
	private ArrayList<Employee> directors;
	private int numEmployees;
	
	public static void main(String[] args) {
		new AppManager().calling();

	}

	public void calling() {
		int numOperators = 5;
		int numSupervisors = 2;
		int numDirectors = 1;
		
		this.numEmployees = numOperators + numSupervisors + numDirectors;
		
        int numberCalls = 15;
        
        this.incomingCalls = this.createCalls(numberCalls);
        this.createEmployees(numOperators, numSupervisors, numDirectors);
        
        long init = System.currentTimeMillis();
        
        ExecutorService executorService = Executors.newFixedThreadPool(this.numEmployees+2);
        
//        for (Call call: incomingCalls) {
//            Runnable dispatcher = new Dispatcher(call, init);
//            executorService.execute(dispatcher);
//        }
        
//        for (Call call: incomingCalls) {
//        	int position;
//        	Runnable dispatcher = null;
//        	if(!operators.isEmpty()) {
//        		position = operators.size()-1;
//        		Employee employee = operators.get(position);
//        		dispatcher = new Dispatcher(call, init, employee);
//        		operators.remove(position);
//        		executorService.execute(dispatcher);
//        	}else if(!supervisors.isEmpty()) {
//        		position = supervisors.size()-1;
//        		Employee employee = supervisors.get(position);
//        		supervisors.remove(position);
//        		dispatcher = new Dispatcher(call, init, employee);
//        		executorService.execute(dispatcher);
//        	}else if(!directors.isEmpty()) {
//        		position = directors.size()-1;
//        		Employee employee = directors.get(position);
//        		directors.remove(position);
//        		dispatcher = new Dispatcher(call, init, employee);
//        		executorService.execute(dispatcher);
//        		directors.add(employee);
//        	}else {
//        		call.setStatus("En Espera");
//            	log.info("La llamada "+call.getCallId()+" esta "+ call.getStatus());
//        	}
//        }
        
        for (Call call: incomingCalls) {
        	Runnable dispatcher = new Dispatcher(call, init, operators, supervisors, directors);
        	executorService.execute(dispatcher);
        }
        executorService.shutdown();	// Cierro el Executor
        while (!executorService.isTerminated()) {
        	//System.out.print(". ");
        	// Espero a que terminen de ejecutarse todos los procesos 
        	// para pasar a las siguientes instrucciones 
        }
        
        long fin = System.currentTimeMillis();	// Instante final del procesamiento
        System.out.println("Tiempo total de procesamiento: "+(fin-init)/1000+" Segundos");
    }
	
	private ArrayList<Call> createCalls(int numberCalls){
		ArrayList<Call> calls = new ArrayList<Call>();

        for(int i=0; i<numberCalls; i++){
        	int duration = (int) (Math.random()*(10-5)) + 5;
            Call call = new Call(Integer.toString(i), duration, "Nueva");
            System.out.println(duration);
            calls.add(call);
        }
        
        return calls;
    }
	
	private void createEmployees(int operators, int supervisors, int directors){
		
		this.operators = new ArrayList<Employee>();
		this.supervisors = new ArrayList<Employee>();
		this.directors = new ArrayList<Employee>();

		for(int i=0; i<operators; i++){
			Employee operator = new Employee(""+i, "Operator");
			this.operators.add(operator);
		}
		for(int i=0; i<supervisors; i++){
			Employee supervisor = new Employee(""+i, "Supervisor");
			this.supervisors.add(supervisor);
		}
		for(int i=0; i<directors; i++){
			Employee director = new Employee(""+i, "Director");
			this.directors.add(director);
		}
	}
}
