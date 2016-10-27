import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {
	public static void main(String[] args){
		
		UserSystemImpl programa = new UserSystemImpl();
		Path xml = FileSystems.getDefault().getPath("xml", "UsuariosUnix.xml");
		programa.loadFrom(xml);
		int i=0;
		for (i=0; i<programa.grupos.size();i++){
			programa.grupos.get(i).imprimirGrupo();
		}
		for (i=0; i<programa.usuarios.size();i++){
			programa.usuarios.get(i).imprimir();
		}
	}
}
