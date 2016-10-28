

public class Group {

	private String name;
	private int gId;
	private User[] miembros = new User[0];

	/**
	 * Constructor de la clase Group
	 * @param name String con el nombre del grupo
	 * @param gId int con el identificador unico del grupo
	 */
	public Group(String name, int gId) {

		this.name = name;
		this.gId = gId;

	}

	/**
	 * Constructor vacio de la clase Group
	 */
	public Group() {
	}

	/**
	 * Getter del atributo name
	 * @return String con el nombre del grupo
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter del atributo name
	 * @param name String con el nuevo nombre del grupo
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter del atributo gId
	 * @return int con el identificador unico del grupo
	 */
	public int getgID() {
		return gId;
	}

	/**
	 * Setter del atributo gId
	 * @param gID int con el nuevo identificador unico del grupo
	 */
	public void setgID(int gID) {
		this.gId = gID;
	}

	/**
	 * Getter del atributo miembros
	 * @return User[] con todos los miembros del grupo
	 */
	public User[] getMiembros() {
		return miembros;
	}

	/**
	 * Añade un nuevo miembro al grupo
	 * @param usuario User con el nuevo usuario a añadir
	 */
	public void setMiembros(User usuario) {

		User[] copy = new User[miembros.length + 1];

		for (int i = 0; i < miembros.length; i++)
			copy[i] = miembros[i];

		copy[copy.length - 1] = usuario;
		miembros = copy.clone();
	}

	/**
	 * Elimina un miembro del grupo
	 * @param user User a eliminar del grupo
	 */
	public void removeUser(User user) {

		User[] copy = new User[miembros.length - 1];

		for (int i = 0, j = 0; i < miembros.length && j < copy.length; i++, j++) {
			if (miembros[i].getuId() == user.getuId())
				i++;
			copy[j] = miembros[i];
		}

		miembros = copy.clone();

	}

	/**
	 * Imprime los atributos del grupo
	 */
	public void imprimirGrupo() {
		System.out.println("Nombre del grupo: " + name);
		System.out.println("GID del grupo: " + gId);
		System.out.println("Usuarios del grupo: " + miembros.length + "\n");

		if (miembros.length > 0) {
			for (int i = 0; i < miembros.length; i++) {

				System.out.println("   Nombre: " + miembros[i].getName() + "\n   uId: " + miembros[i].getuId());
				System.out.println();
			}
		}

	}

}