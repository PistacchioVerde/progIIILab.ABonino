package parte7;

import parte5.*;

public abstract class JAMSimpleBehaviour extends JAMBehaviour{

	public JAMSimpleBehaviour(JAMAgent jag) {
		super(jag);
	}
	
	public void run(){
		try{
			setup();
			action();
			dispose();
			done();
			running = false;
		} catch (JAMBehaviourInteruptedException err){
			System.out.println(err);
		}
	}
	
}
