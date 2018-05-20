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

import com.AlMundo.CallCenterDomains.*;
import com.AlMundo.CallCenterSimulator.Dispatcher;

public class AppManager {

	private ArrayList<Call> incomingCalls;
	private ArrayList<Employee> operators;
	private ArrayList<Employee> supervisors;
	private ArrayList<Employee> directors;
	private static final int numLlamadasAlTiempo = 10;
	
	public static void main(String[] args) {
		new AppManager().calling();

	}

	public void calling() {
		
        Integer numberCalls = 15;
        
        this.incomingCalls = this.createCalls(numberCalls);

        long init = System.currentTimeMillis();
        
        ExecutorService executorService = Executors.newFixedThreadPool(this.numLlamadasAlTiempo);
        
        for (Call call: incomingCalls) {
            Runnable dispatcher = new Dispatcher(call, init);
            executorService.execute(dispatcher);
        }
        executorService.shutdown();	// Cierro el Executor
        while (!executorService.isTerminated()) {
        	//System.out.println("<--->");
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
