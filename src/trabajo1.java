import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class trabajo1 {

	public static void main(String[] args) {
		ArrayList<String> lista;
		ArrayList<Usuario> usuarios;

		lista = creaLista();
		usuarios = creaUsuarios(lista);

	}

	public static ArrayList<String> creaLista() {
		FileReader fr;
		BufferedReader br;
		String linea;
		ArrayList<String> lista = new ArrayList<String>();

		try {

			fr = new FileReader("/etc/passwd");
			br = new BufferedReader(fr);

			while ((linea = br.readLine()) != null) {
				lista.add(linea);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return lista;
	}

	public static ArrayList<Usuario> creaUsuarios(ArrayList<String> lista) {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		int j, k;

		for (int i = 0; i < lista.size(); i++) {
			j = 0;
			k = 0;
			while (lista.get(i).charAt(j) != ':') {
				j++;
			}
			usuarios.add(new Usuario(lista.get(i).substring(0, j)));
			j++;
			k = j;
			while (lista.get(i).charAt(k) != ':') {
				k++;
			}
			usuarios.get(i).setGrupo(lista.get(i).substring(j, k));

			usuarios.get(i).printNG();
		}

		return usuarios;
	}

}
