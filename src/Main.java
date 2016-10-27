import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Main {
	public static void main(String[] args){
		
		UserSystemImpl programa = new UserSystemImpl();
		Path xml = FileSystems.getDefault().getPath("/Users/Mario/Documents/workspace/tds/xml", "UsuariosUnix.xml");
		
		programa.loadFrom(xml);
	}
}
