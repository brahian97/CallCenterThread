package com.AlMundo.CallCenterSimulator;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import com.AlMundo.CallCenterDomains.Call;
import com.AlMundo.CallCenterDomains.Employee;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	private BlockingQueue<Employee> operators;
	private BlockingQueue<Employee> supervisors; 
	private BlockingQueue<Employee> directors;
	private ArrayList<Call> calls;

    public void testInitialization()
    {
    	try{
    		BlockingQueue<Employee> operators = new PriorityBlockingQueue<Employee>();
    		BlockingQueue<Employee> supervisors = new PriorityBlockingQueue<Employee>();
    		BlockingQueue<Employee> directors = new PriorityBlockingQueue<Employee>();
        	Employee auxiliar1 = new Employee ("1", "OPERADOR");
        	Employee auxiliar2 = new Employee ("2", "SUPERVISOR");
        	Employee auxiliar3 = new Employee ("3", "DIRECTOR");
        	Call example1 = new Call("1", (long) new Random().nextFloat() * (1000 - 5000) + 5000, "INICIADA");
        	Call example2 = new Call("2", (long) new Random().nextFloat() * (1000 - 5000) + 5000, "INICIADA");
        	Call example3 = new Call("3", (long) new Random().nextFloat() * (1000 - 5000) + 5000, "INICIADA");
        	operators.add(auxiliar1);
        	supervisors.add(auxiliar2);
        	directors.add(auxiliar3);
        	ExecutorService CallCenterSimulator = Executors.newFixedThreadPool(3);
        	Dispatcher conmuter1 = new Dispatcher(operators, supervisors, directors, example1);
        	Dispatcher conmuter2 = new Dispatcher(operators, supervisors, directors, example2);
        	Dispatcher conmuter3 = new Dispatcher(operators, supervisors, directors, example3);
        	CallCenterSimulator.execute(conmuter1);
        	CallCenterSimulator.execute(conmuter2);
        	CallCenterSimulator.execute(conmuter3);        	
        	Thread.sleep(1000);
//        	assertEquals (conmuter1.getCall().getAgent().getCharge(), "OPERADOR");
//        	assertEquals (conmuter2.getCall().getAgent().getCharge(), "SUPERVISOR");
//        	assertEquals (conmuter3.getCall().getAgent().getCharge(), "DIRECTOR");        	
    	}
    	catch(InterruptedException e) 
    	{
            e.printStackTrace();
            fail();
    	}
    }
    
    public void createEmployees(int numOperators, int numSsupervisors, int numDirectors){
    	
    	this.operators = new PriorityBlockingQueue<Employee>();
    	this.supervisors = new PriorityBlockingQueue<Employee>();
    	this.directors = new PriorityBlockingQueue<Employee>();

    	for(int i=0; i<numOperators; i++){
    		Employee operator = new Employee(""+i, "Operator");
    		operators.add(operator);
    	}
    	for(int j=0; j<numSsupervisors; j++){
    		Employee supervisor = new Employee(Integer.toString(j), "Supervisor");
    		supervisors.add(supervisor);
    	}
    	for(int k=0; k<numDirectors; k++){
    		Employee director = new Employee(Integer.toString(k), "Director");
    		directors.add(director);
    	}
    }

    public void createCalls(int calls){
        this.calls = new ArrayList<Call>();

        for(int i=0; i<calls; i++){
            Call call = new Call(Integer.toString(i), (long) new Random().nextFloat()*(1000-5000)+5000, "Nueva");
            this.calls.add(call);
        }
    }

    public void tenCalls(){
        try{
            int calls = 10;
            this.createCalls(calls);
            this.createEmployees(5,1,1);
            ExecutorService executorService = Executors.newFixedThreadPool(calls);
            

            Dispatcher conmuter1 = new Dispatcher(this.operators, this.supervisors, this.directors, this.calls.get(0));
            Dispatcher conmuter2 = new Dispatcher(this.operators, this.supervisors, this.directors, this.calls.get(1));
            Dispatcher conmuter3 = new Dispatcher(this.operators, this.supervisors, this.directors, this.calls.get(2));
            Dispatcher conmuter4 = new Dispatcher(this.operators, this.supervisors, this.directors, this.calls.get(3));
            Dispatcher conmuter5 = new Dispatcher(this.operators, this.supervisors, this.directors, this.calls.get(4));
            Dispatcher conmuter6 = new Dispatcher(this.operators, this.supervisors, this.directors, this.calls.get(5));
            Dispatcher conmuter7 = new Dispatcher(this.operators, this.supervisors, this.directors, this.calls.get(6));
            Dispatcher conmuter8 = new Dispatcher(this.operators, this.supervisors, this.directors, this.calls.get(7));
            Dispatcher conmuter9 = new Dispatcher(this.operators, this.supervisors, this.directors, this.calls.get(8));
            Dispatcher conmuter10 = new Dispatcher(this.operators, this.supervisors, this.directors, this.calls.get(9));
            
            Thread.sleep(10000);
            
            executorService.execute(conmuter1);
            executorService.execute(conmuter2);
            executorService.execute(conmuter3);
            executorService.execute(conmuter4);
            executorService.execute(conmuter5);
            executorService.execute(conmuter6);
            executorService.execute(conmuter7);
            executorService.execute(conmuter8);
            executorService.execute(conmuter9);
            executorService.execute(conmuter10);

            Thread.sleep(1000);
            

//            assert conmuter1.getIncomingCall().getAgent() instanceof Employee;
//            assert conmuter2.getIncomingCall().getAgent() instanceof Employee;
//            assert conmuter3.getIncomingCall().getAgent() instanceof Employee;
//            assert conmuter4.getIncomingCall().getAgent() instanceof Employee;
//            assert conmuter5.getIncomingCall().getAgent() instanceof Employee;
            
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        }
    }
    
}
