package parte3;

import java.util.List;
import parte1.*;

public class AgentRunnable implements Runnable {

    private AgentID owner;
    private List<MessageBox> boxList;
    private Message message;

    public AgentRunnable(PersonalAgentID agent, List<MessageBox> boxList, Message message) {
        this.owner = agent;
        this.boxList = boxList;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(this.owner + " - Avvio ");
                int i = 0;
                boolean scritto = false;
                while (!scritto) {
                    i = (int) (4 * Math.random());
                    if ((boxList.get(i).getOwner()).equals(owner) == false) {
                        boxList.get(i).write(message);
                        scritto = true;
                    }
                }
                System.out.println(this.owner + " - Inviato messaggio a " + boxList.get(i).getOwner());

                for (int j = 0; j < boxList.size(); j++) {
                    if ((this.boxList.get(j).getOwner()).equals(owner)) {
                        System.out.println(this.owner + " - Leggo...");
                        System.out.println(this.boxList.get(j).readMessage());
                        System.out.println(this.owner + " - Letto messaggio\n");
                    }
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }
}
