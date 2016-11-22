package TDS.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

public class Boletin {

	private ArrayList<Noticia> noticias;

	public Boletin() {
		// noticias = new ArrayList<Noticia>();
	}

	public Boletin(Noticia noticia) {
		/*
		 * noticias = new ArrayList<Noticia>(); anadirNoticia(noticia);
		 */
	}

	public Boletin(ArrayList<Noticia> noticias) {
		/*
		 * this.noticias = noticias;
		 */
	}

	public void anadirNoticia(Noticia noticia) {
		/*
		 * if (!noticias.contains(noticia)) { noticias.add(noticia); }
		 */
	}

	public int noticiasTotales() {
		/* return noticias.size(); */
		return -1;
	}

	// fecha recientes
	// .
	// .
	// .
	// fecha antiguas

	public ArrayList<Noticia> ordenCronologico() {
		/*
		 * int i, n = noticias.size(); ArrayList<Noticia> cronologico = new
		 * ArrayList<Noticia>(); cronologico = (ArrayList<Noticia>)
		 * noticias.clone();
		 * 
		 * for (i = 0; i <= n - 2; i++) { for (int j = n - 1; j > i; j--) { if
		 * (cronologico.get(j - 1).getFechaPublicacion()
		 * .compareTo(cronologico.get(j).getFechaPublicacion()) < 0) {
		 * cronologico.set(i - 1, cronologico.get(j)); cronologico.remove(j);
		 * 
		 * } }
		 * 
		 * } return cronologico;
		 */
		return null;
	}

	public ArrayList<Noticia> ordenCategoria() {
		// Por orden categorias
		// Por orden cronologico
		return null;
	}

	public ArrayList<Noticia> noticiasSimilares(Noticia noticia) {
		// Por orden cronologico
		return null;
	}

	public Boletin boletinFecha(GregorianCalendar fecha) {

		return null;
	}

	public Boletin boletinIntervalo(GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		return null;

	}

	public Boletin boletinCategoria(Categoria categoria) {

		return null;
	}

	public Boletin boletinFechaIntervaloCategoria(GregorianCalendar fecha, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, Categoria categoria) {
		return null;
	}

	public ArrayList<Noticia> getNoticias() {
		return noticias;
	}

	public boolean noticiaSimilar(Noticia noticia) {
		return false;
	}

	public static boolean fechaSimilar(Noticia noticia1, Noticia noticia2) {
		return false;
	}

	public void setNoticias(ArrayList<Noticia> noticias) {
		this.noticias = noticias;
	}
}
