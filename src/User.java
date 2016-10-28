import java.io.File;
import java.nio.file.Path;

/**
 * Clase User, modela un usuario de del Sistema
 * 
 * @author Guillermo Anta Alonso
 * @author Mario Gomez Fernandez
 * @author Miguel Bayon Sanz
 *
 */
public class User {

	private String name;

	private int uId;

	private String password;

	private Path pathToHome;

	private String fullName;

	private EnumShell shell;

	private Group mainGroup;

	private Group[] secundaryGroups = new Group[0];

	public User() {
	}

	/**
	 * Constructor de la clase User, crea e inicializa User.
	 * 
	 * @param name
	 * @param uId
	 * @param password
	 * @param pathToHome
	 * @param fullName
	 * @param shell
	 * @param mainGroup
	 * @param secundaryGroups
	 */
	public User(String name, int uId, String password, Path pathToHome, String fullName, EnumShell shell,
			Group mainGroup, Group[] secundaryGroups) {

		this.name = name;
		this.uId = uId;
		this.password = password;
		this.pathToHome = pathToHome;
		this.fullName = fullName;
		this.shell = shell;
		this.mainGroup = mainGroup;
		this.secundaryGroups = secundaryGroups.clone();

	}

	/**
	 * 
	 * @param uId
	 */
	public void setuId(int uId) {
		this.uId = uId;
	}

	public boolean isInGroup(Group group) {
		if (mainGroup.getgID() == group.getgID())
			return true;
		for (int i = 0; i < secundaryGroups.length; i++) {
			if (secundaryGroups[i].getgID() == group.getgID())
				return true;
		}
		return false;
	}

	/**
	 * Añade un grupo secundario al usuario
	 * 
	 * @param secundaryGroup
	 *            Group con el grupo a añadir
	 */
	public void setSecundaryGroups(Group secundaryGroup) {

		Group[] copy = new Group[secundaryGroups.length + 1];

		for (int i = 0; i < secundaryGroups.length; i++)
			copy[i] = secundaryGroups[i];

		copy[copy.length - 1] = secundaryGroup;

		secundaryGroups = copy.clone();

	}

	/**
	 * Elimina el grupo secundario especificado al usuario
	 * 
	 * @param secundaryGroup
	 *            Group con el grupo a eliminar
	 */
	public void removeSecundaryGroup(Group secundaryGroup) {

		Group[] copy = new Group[secundaryGroups.length - 1];

		for (int i = 0, j = 0; i < secundaryGroups.length && j < copy.length; i++, j++) {
			if (secundaryGroups[i].getgID() == secundaryGroup.getgID())
				i++;
			copy[j] = secundaryGroups[i];
		}

		secundaryGroups = copy.clone();
	}

	/**
	 * Getter del atributo name
	 * 
	 * @return String con el nombre del usuario
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter del atributo name
	 * 
	 * @param name
	 *            String con el nombre del usuario
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter del atributo uId
	 * 
	 * @return int con la uId del usuario
	 */
	public int getuId() {
		return uId;
	}

	/**
	 * Getter del atributo password
	 * 
	 * @return String con la nueva contraseña
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter del atributo password
	 * 
	 * @param password
	 *            String con la nueva contraseña
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter del atributo pathToHome
	 * 
	 * @return Path con la direccion del Home del usuario
	 */
	public Path getPathToHome() {
		return pathToHome;
	}

	/**
	 * Setter del atributo pathToHome
	 * 
	 * @param pathToHome
	 *            String con la nueva direccion del Home del usuario
	 */
	public void setPathToHome(String pathToHome) {
		File file = new File(pathToHome);
		Path home = file.toPath();
		this.pathToHome = home;
	}

	/**
	 * Getter del atributo fullName
	 * 
	 * @return String con el nombre completo del usuario
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Setter del atributo fullName
	 * 
	 * @param fullName
	 *            String con el nuevo nombre completo del usuario
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public EnumShell getShell() {
		return shell;
	}

	/**
	 * Setter del atributo shell
	 * 
	 * @param shell
	 *            String con el nuevo shell del usuario
	 */
	public void setShell(String shell) {

		EnumShell enumShell = EnumShell.valueOf(shell);
		this.shell = enumShell;
	}

	/**
	 * Getter del atributo mainGroup
	 * 
	 * @return Group con el grupo principal del usuario
	 */
	public Group getMainGroup() {
		return mainGroup;
	}

	/**
	 * Setter del atributo mainGroup
	 * 
	 * @param mainGroup
	 *            Group con el nuevo grupo principal del usuario
	 */
	public void setMainGroup(Group mainGroup) {
		this.mainGroup = mainGroup;
	}

	/**
	 * Getter del atributo secundaryGroups
	 * 
	 * @return Group[] lista de los grupos secundarios a los que pertenece el
	 *         usuario
	 */
	public Group[] getSecundaryGroups() {
		return secundaryGroups;
	}

	/**
	 * Imprime los atributos principales de usuario
	 */
	public void imprimirUsuario() {
		System.out.println("Nombre del usuario: " + name);
		System.out.println("UID del usuario: " + uId);
		System.out.println("Pass: " + password);
		System.out.println("MG: " + mainGroup.getName());
		System.out.println("SG:");
		for (int i = 0; i < secundaryGroups.length; i++) {
			if (secundaryGroups[i] != null)
				System.out.println(secundaryGroups[i].getName());
		}
		System.out.println();
	}

}
