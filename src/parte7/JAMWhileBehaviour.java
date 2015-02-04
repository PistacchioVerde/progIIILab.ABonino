package parte7;

import parte5.JAMAgent;

public abstract class JAMWhileBehaviour extends JAMBehaviour{

	public JAMWhileBehaviour(JAMAgent jag) {
		super(jag);
	}
	
	public void run(){
		try{
			setup();
			while (!isDone()) action();
		}catch (JAMBehaviourInteruptedException err){
			if(isDone()) return;
			System.out.println(err);
		} finally {
			try{
				dispose();
				running = false;
			} catch (JAMBehaviourInteruptedException err){
				System.out.println(err);
			}
		}
	}

}
