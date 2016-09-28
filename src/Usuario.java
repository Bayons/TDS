
public class Usuario {

	String nombre, grupo, conec, descon;

	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public void setConec(String conec) {
		this.conec = conec;
	}

	public void setDescon(String descon) {
		this.descon = descon;
	}

	public String getNombre() {
		return nombre;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getConec() {
		return conec;
	}

	public String getDescon() {
		return descon;
	}
	
	public void printNG(){
		System.out.println("Nombre: "+nombre+" || Grupo: ");
	}
}
