package parte2;

import parte1.*;

public class ProvaMessageBoxNoSync {

	public static void main(String[] args) throws JAMMessageBoxException {
		AgentID owner = new PersonalAgentID("Proprietario", "owner");
		MessageBoxNoSync box = null;
		try {
			box = new MessageBoxNoSync((PersonalAgentID) owner);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		AgentID pag1 = new PersonalAgentID("agente1", "categoria1");
		AgentID pag2 = new PersonalAgentID("agente2", "categoria2");
		AgentID cag1 = new CategoryAgentID("categoria1");
		AgentID cag2 = new CategoryAgentID("categoria2");
		
		
		box.write(new Message(pag1, cag1, Performative.INFORM, "contentmsg1", "extra"));
		box.write(new Message(pag2, cag2, Performative.FAILURE, "contentmsg1", "extra"));
		box.write(new Message(cag2, cag1, Performative.INFORM, "contentmsg1", "extra"));
		box.write(new Message(pag1, pag2, Performative.UNKNOWN, "contentmsg1", "extra"));
		System.out.println(box + "\n");
		
		try {
			System.out.println(box.readMessage() + "\n");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println(box + "\n");
		
		try {
			System.out.println(box.readMessage(Performative.PROPOSAL) + "\n");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			System.out.println(box.readMessage(cag2) + "\n");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println(box + "\n");
		
		System.out.println(box.isThereAMessage());
		System.out.println(box.isThereAMessage(pag2));
		
	}

}
