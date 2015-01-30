package parte1;

public class PersonalAgentID extends CategoryAgentID implements AgentID{
	
	public PersonalAgentID(){
		super();
	}
	
	public PersonalAgentID(String name, String category){
		super(category);
		this.name = name;
	}
	
	public boolean equals(Object agentID){
		try{
			return (agentID != null) ? (this.getName().equals(((PersonalAgentID) agentID).getName())) ? (this.getCategory().equals(((PersonalAgentID) agentID).getCategory())) : false : false;
		}catch(ClassCastException e) {
			super.equals(agentID);
		}
		return false;
	}
	
}