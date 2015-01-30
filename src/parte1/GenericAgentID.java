package parte1;

import java.io.Serializable;

public class GenericAgentID implements AgentID, Serializable{
	
	protected String name;
	protected String category;
	
	public GenericAgentID(){
		this.name = "n/a";
		this.category = "n/a";
	}

	@Override
	public boolean equals(Object agentID){
		if(agentID == null) return false;
		try{
			if(agentID instanceof GenericAgentID) return true;
		}catch(ClassCastException e) {
			return false;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "(" + this.name +", " + this.category + ")";
	}

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericAgentID other = (GenericAgentID) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}*/
	
}