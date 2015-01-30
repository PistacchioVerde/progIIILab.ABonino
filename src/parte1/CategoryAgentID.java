package parte1;

public class CategoryAgentID extends GenericAgentID implements AgentID{
	
	public CategoryAgentID(){
		super();
	}
	
	public CategoryAgentID(String category){
		super();
		this.category = category;
	}
	
	@Override
	public boolean equals(Object agentID){
		try{
			return (agentID != null) ? (this.getCategory().equals(((CategoryAgentID) agentID).getCategory())) : false;
		}catch(ClassCastException e) {
			super.equals(agentID);
		}
		return false;
	}
	
}