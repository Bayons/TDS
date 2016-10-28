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
		
		
		System.out.println("\n\nAñadimos usuario a grupo "+usuario.getName()+" "+grupo.getName() +"\n\n");
		programa.addUserToGroup(usuario, grupo);
		programa.addUserToGroup(programa.getUserByName("Bayons"), grupo);
		
		grupo.imprimirGrupo();
		System.out.println("\n\nEliminamos usuario del grupo\n\n");
		//programa.removeUserFromGroup(usuario, grupo);
		
		grupo.imprimirGrupo();
		
		//imprimirListas();
		
		
		

		System.out.println("\n\nGuardamos");
		Path xmlNuevo = FileSystems.getDefault().getPath("", "Resultado.xml");
		// programa.updateTo(xmlNuevo);
	}

	public static void imprimirListas() {
		int i;

		System.out.println("\n\nLISTA DE GRUPOS EN EL SISTEMA \n");
		for (i = 0; i < programa.grupos.size(); i++) {
			programa.grupos.get(i).imprimirGrupo();
		}

		System.out.println("\n\nLISTA DE USUARIOS EN EL SISTEMA \n");
		for (i = 0; i < programa.usuarios.size(); i++) {
			programa.usuarios.get(i).imprimirUsuario();
		}
	}
}
