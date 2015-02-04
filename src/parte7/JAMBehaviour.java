package parte7;

import parte5.*;

public abstract class JAMBehaviour implements Runnable{

	boolean done;
	public boolean running;
	Thread myThread;
	JAMAgent myAgent;
	
	public JAMBehaviour(JAMAgent jag){
		this.done = false;
		this.myAgent = jag;
		this.running = false;
	}
	
	public void done(){ done = true; myThread.interrupt();}
	
	boolean isDone(){ return done; }

	public void setMyThread(Thread myThread){ this.myThread = myThread; }
	
	void sleep(long ms) throws InterruptedException{ myThread.sleep(ms); }
	
	void action() throws JAMBehaviourInteruptedException{}
	
	void setup() throws JAMBehaviourInteruptedException{}
	
	void dispose() throws JAMBehaviourInteruptedException{}
	
	public void setDone(boolean value){ done = value; }
	
	public boolean isRunning(){return running;}
}