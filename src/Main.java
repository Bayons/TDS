import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {

	static UserSystemImpl programa = new UserSystemImpl();

	public static void main(String[] args) {

		System.out.println("----------Cargamos los usuarios y los grupos del sistema...");
		Path xml = FileSystems.getDefault().getPath("", "UsuariosUnix.xml");
		programa.loadFrom(xml);
		imprimirListas();

		System.out.println("----------Creamos nuevos grupos y usuarios");
		programa.createNewGroup("grupoNuevo", 3);
		programa.createNewGroup("grupoParaBorrar", 4);
		programa.createNewUser("Pepe", 24, "1234", FileSystems.getDefault().getPath("", ""), "Pepelu", EnumShell.bash,
				programa.getGroupById(3), new Group[0]);
		programa.createNewUser("usuarioParaBorrar", 25, "1234", FileSystems.getDefault().getPath("", ""), "Pepelu",
				EnumShell.bash, programa.getGroupById(4), new Group[0]);
		imprimirListas();

		System.out.println("----------Obtenemos un usuario por su nombre");
		User usuario = programa.getUserByName("usuarioParaBorrar");
		usuario.imprimirUsuario();
		
		System.out.println("----------Obtenemos un grupo por su gId");
		Group grupo = programa.getGroupById(3);
		grupo.imprimirGrupo();
		
		System.out.println("----------Introducimos un usuario en un grupo");
		programa.addUserToGroup(usuario, grupo);
		programa.addUserToGroup(programa.getUserByName("Bayons"), grupo);
		imprimirListas();

		System.out.println("----------Modificamos al usuario " + usuario.getName());
		usuario.setName("Mario");
		usuario.setPassword("pss");
		usuario.setMainGroup(programa.getGroupByName("GRUPONUEVO 1"));
		usuario.setShell("Zsh");
		usuario.setFullName("Mario Gomez");
		imprimirListas();

		System.out.println("----------Modificamos el grupo "+grupo.getName());
		grupo.setName("Nuevo nuevo grupo");
		imprimirListas();

		System.out.println("----------Eliminamos a "+usuario.getName()+" del grupo "+grupo.getName());
		programa.removeUserFromGroup(usuario, grupo);
		imprimirListas();
		
		System.out.println("----------Eliminamos al usuario "+usuario.getName()+" del sistema");
		programa.removeUserFromSystem(usuario);
		imprimirListas();
		
		System.out.println("----------Eliminamos el grupo "+grupo.getName()+" del sistema");
		programa.removeGroupFromSystem(grupo);
		imprimirListas();

		System.out.println("----------Guardamos");
		Path xmlNuevo = FileSystems.getDefault().getPath("", "Resultado.xml");
		programa.updateTo(xmlNuevo);
	}

	public static void imprimirListas() {
		int i;

		System.out.println("\n\nLISTA DE GRUPOS EN EL SISTEMA \n");
		for (i = 0; i < programa.getGrupos().size(); i++) {
			programa.getGrupos().get(i).imprimirGrupo();
		}

		System.out.println("LISTA DE USUARIOS EN EL SISTEMA \n");
		for (i = 0; i < programa.getUsuarios().size(); i++) {
			programa.getUsuarios().get(i).imprimirUsuario();
		}
	}
}
