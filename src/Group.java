import org.w3c.dom.Node;

public class Group {
	
	private String name;
	
	private int gId;
	
	private User[] miembros;
	
	private Node node;
	
	
	
	public Group(String name, int gId){
		
		this.name = name;
		this.gId = gId;
	}

	public Group(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getgID() {
		return gId;
	}

	public void setgID(int gID) {
		this.gId = gID;
	}

	public User[] getMiembros() {
		return miembros;
	}

	public void setMiembros(User usuario) {
		
		User[] copy = new User[miembros.length+1];
		
		for (int i=0; i<miembros.length; i++)
			copy[i]=miembros[i];
		
		copy[copy.length-1]=usuario;
		miembros=copy.clone();
		
	}
	
	public void removeUser(User user){
		
		User[] copy = new User[miembros.length-1];
		
		for(int i=0, j=0; i<miembros.length; i++, j++){
			if (miembros[i].getuId() == user.getuId())
				i++;
			copy[j] = miembros[i];
		}
		
		miembros = copy.clone();
		
	}
	
	public void setNode(Node node) {
		this.node = node;
		
	}
	
	
}
