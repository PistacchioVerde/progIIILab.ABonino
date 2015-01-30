package parte3;

import java.util.*;

import parte1.*;

public class TestPt3 {

    public static void main(String[] args) throws Exception {

        System.out.println("Creazione agenti...");
        PersonalAgentID agent1 = new PersonalAgentID("Matteo", "Verdi");
        PersonalAgentID agent2 = new PersonalAgentID("Mario", "Bianchi");
        PersonalAgentID agent3 = new PersonalAgentID("Giuseppe", "Rossi");
        PersonalAgentID agent4 = new PersonalAgentID("Alfredo", "Neri");

        System.out.println("Creazione lista di box list...");
        List<MessageBox> listaBox = new LinkedList<MessageBox>();
        listaBox.add(new MessageBox((PersonalAgentID) agent1));
        listaBox.add(new MessageBox((PersonalAgentID) agent2));
        listaBox.add(new MessageBox((PersonalAgentID) agent3));
        listaBox.add(new MessageBox((PersonalAgentID) agent4));

        for (MessageBox box : listaBox) {
            System.out.println(box.getOwner());
        }

        Message mess1 = new Message(agent1, agent4, Performative.AGREE, "Mess1", "extra");
        Message mess2 = new Message(agent2, agent3, Performative.AGREE, "Mess2", "extra");
        Message mess3 = new Message(agent3, agent2, Performative.AGREE, "Mess3", "extra");
        Message mess4 = new Message(agent4, agent1, Performative.AGREE, "Mess4", "extra");

        System.out.println("Creazione Thread");
        AgentRunnable run1 = new AgentRunnable(agent1, listaBox, mess1);
        AgentRunnable run2 = new AgentRunnable(agent2, listaBox, mess2);
        AgentRunnable run3 = new AgentRunnable(agent3, listaBox, mess3);
        AgentRunnable run4 = new AgentRunnable(agent4, listaBox, mess4);

        System.out.println("Avvio Thread");
        System.out.println("");
        Thread t1 = new Thread(run1);
        Thread t2 = new Thread(run2);
        Thread t3 = new Thread(run3);
        Thread t4 = new Thread(run4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}