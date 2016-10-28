import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {
	public static void main(String[] args){
		
		UserSystemImpl programa = new UserSystemImpl();
		Path xml = FileSystems.getDefault().getPath("", "UsuariosUnix.xml");
		programa.loadFrom(xml);
		int i=0;
		System.out.println("Imprimimos usuarios y grupos");
		for (i=0; i<programa.grupos.size();i++){
			programa.grupos.get(i).imprimirGrupo();
		}
		for (i=0; i<programa.usuarios.size();i++){
			programa.usuarios.get(i).imprimir();
		}
		
		programa.createNewGroup("grupoNuevo", 3);
		programa.createNewUser("Pepe", 24, "1234", FileSystems.getDefault().getPath("", ""), "Pepelu", EnumShell.bash, programa.getGroupByName("grupoNuevo"), new Group[0]);

		System.out.println("\n\nCreamos un usuario y un grupo y volvemos a imprimir");
		for (i=0; i<programa.grupos.size();i++){
			programa.grupos.get(i).imprimirGrupo();
		}
		for (i=0; i<programa.usuarios.size();i++){
			programa.usuarios.get(i).imprimir();
		}
	}
}
