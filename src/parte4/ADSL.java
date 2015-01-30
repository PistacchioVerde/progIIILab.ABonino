package parte4;

import java.rmi.*;
import java.util.*;

import parte1.*;
import parte3.*;

public interface ADSL extends Remote {

	List<RemoteMessageBox> getRemoteMessageBox(AgentID agent) throws RemoteException;
	void insertRemoteMessageBox(RemoteMessageBox messageBox) throws Exception;
	void removeRemoteMessageBox(AgentID agent) throws Exception;
	
}
