package TDS.src;

import java.net.URL;
import java.util.GregorianCalendar;

public class Noticia {

	private String titular; // 1-13 palabras

	private GregorianCalendar fechaPublicacion;

	private String fuente;

	private URL url;

	private Categoria categoria;

	public Noticia(String titular, GregorianCalendar fechaPublicacion, String fuente, URL url, Categoria categoria) {

		if (titularValido(titular))
			this.titular = titular;

		this.fechaPublicacion = fechaPublicacion;
		this.fuente = fuente;
		this.url = url;
		this.categoria = categoria;

	}

	public boolean titularValido(String titular) {

		String palabras[] = titular.split(" ");

		if (titular == null || palabras.length > 13) {
			return false;
		}

		return true;
	}

	public int comparaFecha(Noticia noticia) {
		// 0 si es igual, -1 noticiadada despues, 1 noticia dada antes
		return fechaPublicacion.compareTo(noticia.getFechaPublicacion());
	}

	public boolean noticiaSimilar(Noticia noticia) {

		GregorianCalendar inicio = new GregorianCalendar(2016, 1, 1);
		GregorianCalendar fin = new GregorianCalendar(2016, 1, 3);

		long diff = (fin.getTimeInMillis() - inicio.getTimeInMillis());

		int comparacion = fechaPublicacion.compareTo(noticia.getFechaPublicacion());

		boolean valido = false;

		if (comparacion == 0) {
			valido = true;
		}

		if (Math.abs(fechaPublicacion.getTimeInMillis() - noticia.getFechaPublicacion().getTimeInMillis()) != diff) {

			valido = true;
		}

		if (!titular.equals(noticia.getTitular()) || !categoria.equals(noticia.getCategoria()) || !valido) {

			return false;
		}

		return true;
	} // titular categoria fecha de publicacion(2 dias de diferencia) =

	public String getTitular() {
		return titular;
	}

	public GregorianCalendar getFechaPublicacion() {
		return fechaPublicacion;
	}

	public String getFuente() {
		return fuente;
	}

	public URL getUrl() {
		return url;
	}

	public Categoria getCategoria() {
		return categoria;
	}

}
