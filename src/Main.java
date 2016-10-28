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
				programa.getGroupByName("grupoNuevo"), new Group[0]);
		
		programa.createNewUser("usuarioParaBorrar", 25, "1234", FileSystems.getDefault().getPath("", ""), "Pepelu",
				EnumShell.bash, programa.getGroupByName("grupoparaBorrar"), new Group[0]);
		
		
		User usuario = programa.getUserById(24);
		Group grupo = programa.getGroupById(4);
		
		
		grupo.imprimirGrupo();
		
		System.out.println("Añadimos usuario a grupo "+usuario.getName()+" "+grupo.getName());
		programa.addUserToGroup(usuario, grupo);
		
		grupo.imprimirGrupo();
		System.out.println("Eliminamos usuario del grupo");
		programa.removeUserFromGroup(usuario, grupo);
		
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
