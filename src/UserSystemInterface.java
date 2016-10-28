
import java.nio.file.Path;

public interface UserSystemInterface {
	
	
	/**
	 * Carga desde un XML solo si ha sido validado desde la respectiva DTD
	 * @param pathToXML.
	 */
	void loadFrom(Path pathToXML);
	
	
	/**
	 * Vuelca la información del sistema en un XML
	 * @param pathToXML
	 */
	void updateTo(Path pathToXML);
	
	
	/**
	 * Devuelve un booleano indicando si el archivo XML se ha cargado correctamente
	 * @return true si está cargado, false si no.
	 */
	boolean isXMLLoaded();
	
	
	/**
	 * Devuelve un booleano que indica si el documento ha sido modificado despues de ser cargado
	 * @return true si ha sido modificado, false si no.
	 */
	boolean isModifiedAfterLoaded();
	
	/**
	 * Crea un usuario nuevo y lo añade a la lista de usuarios del sistema
	 * @param name
	 * @param uId
	 * @param password
	 * @param pathToHome
	 * @param fullName
	 * @param shell
	 * @param mainGroup
	 * @param secundaryGroups
	 */
	void createNewUser(String name, int uId, String password, Path pathToHome, String fullName, EnumShell shell,
			Group mainGroup, Group[] secundaryGroups);
	
	/**
	 * Devuelve el Usuario que tenga el ID que recibe el método, 
	 * @param uId
	 * @return Usuario si hay coincidencia, null si no.
	 */
	User getUserById(int uId);
	
	
	/**
	 * Devuelve el Usuario que tenga el nombre que recibe el método,
	 * @param name
	 * @return Usuario si hay coincidencia, null si no.
	 */
	User getUserByName(String name);
	
	
	/**
	 * Devuelve el Grupo que tenga el gId que recive el método.
	 * @param gId
	 * @return Grupo si hay coincidencia, null si no
	 */
	Group getGroupById(int gId);
	
	
	/**
	 * Devuelve el Grupo que tenga el gId que recive el método.
	 * @param name
	 * @return Grupo si hay coincidencia, null si no
	 */
	Group getGroupByName(String name);
	
	
	/**
	 * Crea un nuevo grupo y lo añade a la lista de grupos del sistema
	 * @param name
	 * @param gId
	 */
	void createNewGroup(String name, int gId);
	
	
	/**
	 * Añade un usuario a un grupo y añade el grupo a la lista de grupos secundarios del usuario y 
	 * el usuario a la lista de miembros del grupo
	 * @param user
	 * @param group
	 */
	void addUserToGroup(User user, Group group);
	
	/**
	 * Elimina un usuario de un grupo, elimina el grupo de la lista de grupos secundarios del usuario y 
	 * el usuario de la lista de miembros del grupo
	 * @param user
	 * @param group
	 */
	void removeUserFromGroup(User user, Group group);
	
	/**
	 * Elimina un usuario del sistema (de todos los grupos a los que pertenece)
	 * @param user
	 */
	void removeUserFromSystem(User user);
	
	/**
	 * Elimina un grupo del sistema, solo si este no es un grupo principal de ningun usuario
	 * @param group
	 */
	void removeGroupFromSystem(Group group);
}