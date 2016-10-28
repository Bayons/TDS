import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {

	static UserSystemImpl programa = new UserSystemImpl();

	public static void main(String[] args) {

		Path xml = FileSystems.getDefault().getPath("", "UsuariosUnix.xml");
		programa.loadFrom(xml);

		imprimirListas();

		programa.createNewGroup("grupoNuevo", 3);

		programa.createNewGroup("grupoParaBorrar", 4);

		programa.createNewUser("Pepe", 24, "1234", FileSystems.getDefault().getPath("", ""), "Pepelu", EnumShell.bash,
				programa.getGroupById(3), new Group[0]);

		programa.createNewUser("usuarioParaBorrar", 25, "1234", FileSystems.getDefault().getPath("", ""), "Pepelu",
				EnumShell.bash, programa.getGroupById(4), new Group[0]);

		User usuario = programa.getUserByName("usuarioParaBorrar");
		Group grupo = programa.getGroupById(3);

		System.out.println("\n\nAñadimos usuario a grupo " + usuario.getName() + " " + grupo.getName() + "\n\n");
		programa.addUserToGroup(usuario, grupo);
		programa.addUserToGroup(programa.getUserByName("Bayons"), grupo);

		grupo.imprimirGrupo();

		programa.getUserById(usuario.getuId()).imprimirUsuario();
		programa.getUserByName(usuario.getName()).imprimirUsuario();

		System.out.println(programa.getGroupById(grupo.getgID()));
		programa.getGroupById(grupo.getgID()).imprimirGrupo();
		programa.getGroupByName(grupo.getName()).imprimirGrupo();

		usuario.setName("Mario");
		usuario.setPassword("pss");
		usuario.setMainGroup(programa.getGroupByName("GRUPOPUTO 1"));
		usuario.setShell("Zsh");
		usuario.setFullName("Mario Gomez");
		usuario.imprimirUsuario();

		grupo.setName("Nuevo mojon");
		grupo.imprimirGrupo();

		programa.removeUserFromGroup(usuario, grupo);
		programa.removeUserFromSystem(usuario);
		programa.removeGroupFromSystem(grupo);

		// imprimirListas();

		System.out.println("\n\nGuardamos");
		Path xmlNuevo = FileSystems.getDefault().getPath("", "Resultado.xml");
		programa.updateTo(xmlNuevo);
	}

	public static void imprimirListas() {
		int i;

		System.out.println("\n\nLISTA DE GRUPOS EN EL SISTEMA \n");
		for (i = 0; i < programa.getGrupos().size(); i++) {
			programa.getGrupos().get(i).imprimirGrupo();
		}

		System.out.println("\n\nLISTA DE USUARIOS EN EL SISTEMA \n");
		for (i = 0; i < programa.getUsuarios().size(); i++) {
			programa.getUsuarios().get(i).imprimirUsuario();
		}
	}
}
