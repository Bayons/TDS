package TDS.src;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Ignore;
import org.junit.Test;

public class BoletinTest {

	@Test
	public void testBoletinVacio() {
		Boletin vacio = new Boletin();
		assertNotNull(vacio);
		assertNotNull(vacio.getNoticias());
		assertTrue(vacio.getNoticias().size() == 0);
	}

	@Test
	public void testAgregaNoticia() {
		final int ESPERADO;
		Boletin boletin = new Boletin();
		try {

			URL url = new URL("http://www.google.es");
			Noticia noticia = new Noticia("Titular", new GregorianCalendar(1994, 06, 28), "Fuente", url,
					Categoria.economia);

			assertNotNull(noticia);
			assertNotNull(noticia.getTitular());
			assertNotNull(noticia.getFechaPublicacion());
			assertNotNull(noticia.getFuente());
			assertNotNull(noticia.getUrl());
			assertNotNull(noticia.getCategoria());
			assertNotNull(boletin);
			assertNotNull(boletin.getNoticias());

			ESPERADO = boletin.getNoticias().size();
			boletin.anadirNoticia(noticia);

			assertNotEquals(ESPERADO, boletin.getNoticias().size());
			assertEquals(ESPERADO + 1, boletin.getNoticias().size());

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testNoticiaRepetida() {
		final int ESPERADO;
		Boletin boletin = new Boletin();
		Noticia noticia = new Noticia("Titular", new GregorianCalendar(2015, 06, 28), "Fuente", null,
				Categoria.economia);

		assertNotNull(noticia);
		assertNotNull(boletin);
		assertNotNull(boletin.getNoticias());
		
		boletin.anadirNoticia(noticia);
		ESPERADO = boletin.getNoticias().size();
		boletin.anadirNoticia(noticia);


		assertNotEquals(0, boletin.getNoticias().size());
		assertEquals(ESPERADO, boletin.getNoticias().size());
	}

	@Test
	public void testBoletinFromListaDeNoticias() {
		Noticia noticia1 = new Noticia("Primer titular", new GregorianCalendar(2015, 06, 28), "FuenteUno", null,
				Categoria.economia);
		Noticia noticia2 = new Noticia("Segundo titular", new GregorianCalendar(2015, 07, 28), "FuenteDos", null,
				Categoria.internacional);
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();

		noticias.add(noticia1);
		noticias.add(noticia2);

		Boletin boletin = new Boletin(noticias);

		assertNotNull(noticia1);
		assertNotNull(noticia2);
		assertNotNull(noticias);
		assertNotNull(boletin);
		assertNotNull(boletin.getNoticias());
		assertNotEquals(0, boletin.getNoticias().size());
		for (int i = 0; i < boletin.getNoticias().size(); i++)
			assertNotNull(boletin.getNoticias().get(i));
	}

	@Test
	public void testNumeroNoticiasDeBoletin() {
		final int ESPERADO;
		Noticia noticia1 = new Noticia("Primer titular", new GregorianCalendar(2015, 06, 28), "FuenteUno", null,
				Categoria.economia);
		Noticia noticia2 = new Noticia("Segundo titular", new GregorianCalendar(2015, 07, 28), "FuenteDos", null,
				Categoria.internacional);
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();

		noticias.add(noticia1);
		noticias.add(noticia2);

		ESPERADO = noticias.size();

		Boletin boletin = new Boletin(noticias);

		assertNotNull(boletin);
		assertNotNull(boletin.getNoticias());
		assertNotNull(boletin.noticiasTotales());
		assertNotEquals(0, boletin.noticiasTotales());
		assertEquals(ESPERADO, boletin.noticiasTotales());
	}

	@Test
	public void testFechaNoticiaMasReciente() {
		Noticia noticia1 = new Noticia("Titular Antiguo", new GregorianCalendar(1998, 06, 28), "FuenteUno", null,
				Categoria.economia);
		Noticia noticia2 = new Noticia("Titular Reciente", new GregorianCalendar(), "FuenteDos", null,
				Categoria.internacional);
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();

		noticias.add(noticia1);
		noticias.add(noticia2);

		Boletin boletin = new Boletin(noticias);

		assertNotNull(boletin);
		assertNotNull(boletin.getNoticias());
		assertNotNull(noticia1.getFechaPublicacion());
		assertNotNull(noticia2.getFechaPublicacion());
		assertNotEquals(0, boletin.noticiasTotales());

		int erroresTotales = 0;
		GregorianCalendar reciente = boletin.ordenCronologico().get(0).getFechaPublicacion();
		for (int i = 0; i < boletin.noticiasTotales(); i++) {
			if (reciente.before(boletin.getNoticias().get(i).getFechaPublicacion()))
				erroresTotales++;
		}

		assertTrue(erroresTotales == 0);
	}
	@Test
	public void testFechaNoticiaMasAntiguas() {
		Noticia noticia1 = new Noticia("Titular Antiguo", new GregorianCalendar(1998, 06, 28), "FuenteUno", null,
				Categoria.economia);
		Noticia noticia2 = new Noticia("Titular Reciente", new GregorianCalendar(), "FuenteDos", null,
				Categoria.internacional);
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();

		noticias.add(noticia1);
		noticias.add(noticia2);

		Boletin boletin = new Boletin(noticias);

		assertNotNull(boletin);
		assertNotNull(boletin.getNoticias());
		assertNotNull(noticia1.getFechaPublicacion());
		assertNotNull(noticia2.getFechaPublicacion());
		assertNotEquals(0, boletin.noticiasTotales());

		int erroresTotales = 0;
		GregorianCalendar antiguo = boletin.ordenCronologico().get(boletin.noticiasTotales() - 1).getFechaPublicacion();
		for (int i = 0; i < boletin.noticiasTotales(); i++) {
			if (antiguo.after(boletin.getNoticias().get(i).getFechaPublicacion()))
				erroresTotales++;
		}

		assertTrue(erroresTotales == 0);
	}

	@Test
	public void testOrdenCronologico() {
		Noticia noticia1 = new Noticia("Titular Antiguo", new GregorianCalendar(1998, 06, 28), "FuenteUno", null,
				Categoria.economia);
		Noticia noticia2 = new Noticia("Titular Reciente", new GregorianCalendar(), "FuenteDos", null,
				Categoria.internacional);
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();

		noticias.add(noticia1);
		noticias.add(noticia2);

		Boletin boletin = new Boletin(noticias);

		assertNotNull(boletin);
		assertNotNull(boletin.getNoticias());
		assertNotEquals(0, boletin.noticiasTotales());

		int erroresTotales = 0;
		for (int i = 0; i < boletin.noticiasTotales(); i++) {
			for (int j = i + 1; j < boletin.noticiasTotales(); j++) {
				if (boletin.ordenCronologico().get(i).getFechaPublicacion()
						.after(boletin.ordenCronologico().get(j).getFechaPublicacion()))
					erroresTotales++;
			}
		}

		assertTrue(erroresTotales == 0);
	}

	@Test
	public void testOrdenCategoria() {
		Noticia noticia1 = new Noticia("Titular Antiguo", new GregorianCalendar(1998, 06, 28), "FuenteUno", null,
				Categoria.economia);
		Noticia noticia2 = new Noticia("Titular Reciente", new GregorianCalendar(), "FuenteDos", null,
				Categoria.internacional);
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();

		noticias.add(noticia1);
		noticias.add(noticia2);

		Boletin boletin = new Boletin(noticias);

		assertNotNull(boletin);
		assertNotNull(boletin.getNoticias());
		assertNotEquals(0, boletin.noticiasTotales());

		int erroresTotales = 0;
		for (int i = 0; i < boletin.noticiasTotales(); i++) {
			for (int j = i + 1; j < boletin.noticiasTotales(); j++) {
				if (boletin.ordenCategoria().get(i).getCategoria()
						.compareTo(boletin.ordenCategoria().get(j).getCategoria()) > 0)
					erroresTotales++;
			}
		}

		assertTrue(erroresTotales == 0);

	}

	@Test
	public void testNoticiasSimilares() {
		final int ESPERADO;
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
		Noticia noticia = new Noticia("Titular", new GregorianCalendar(1998, 06, 28), "FuenteUno", null,
				Categoria.internacional);

		noticias.add(noticia);
		noticias.add(new Noticia("Titular", new GregorianCalendar(1998, 06, 27), "FuenteDos", null,
				Categoria.internacional));
		noticias.add(new Noticia("Titular", new GregorianCalendar(1998, 06, 29), "FuenteTres", null,
				Categoria.internacional));

		ESPERADO = noticias.size();

		noticias.add(new Noticia("Distinto por completo", new GregorianCalendar(2009, 01, 06), "FuenteDos", null,
				Categoria.sociedad));

		Boletin boletin = new Boletin(noticias);
		ArrayList<Noticia> similares = boletin.noticiasSimilares(noticia);

		assertNotNull(boletin);
		assertNotNull(boletin.getNoticias());
		assertNotEquals(0, boletin.noticiasTotales());
		assertNotNull(similares);
		assertEquals(ESPERADO, similares.size());

		int erroresTotales = 0;
		for (int i = 0; i < similares.size(); i++) {
			for (int j = i + 1; j < similares.size(); j++) {
				if (!similares.get(i).getTitular().equals(similares.get(j).getTitular()))
					erroresTotales++;
				if (!similares.get(i).getCategoria().equals(similares.get(j).getCategoria()))
					erroresTotales++;
				if (!Boletin.fechaSimilar(similares.get(i), similares.get(j)))
					erroresTotales++;
			}
		}

		assertTrue(erroresTotales == 0);
	}

	@Test
	public void testBoletinFecha() {
		final GregorianCalendar FECHA = new GregorianCalendar(1998, 06, 28);
		final int ESPERADO;
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();

		noticias.add(new Noticia("Titular", new GregorianCalendar(1998, 06, 28), "FuenteUno", null,
				Categoria.internacional));
		noticias.add(new Noticia("Titular De Fecha Similar", new GregorianCalendar(1998, 06, 27), "FuenteDos", null,
				Categoria.nacional));
		noticias.add(new Noticia("Otro Titular De Fecha Similar", new GregorianCalendar(1998, 06, 29), "FuenteTres",
				null, Categoria.economia));

		ESPERADO = noticias.size();

		noticias.add(new Noticia("Distinto por completo", new GregorianCalendar(2009, 01, 06), "FuenteDos", null,
				Categoria.sociedad));

		Boletin boletin = new Boletin(noticias);
		Boletin similares = boletin.boletinFecha(FECHA);

		assertNotNull(similares);
		assertNotNull(similares.getNoticias());
		assertNotEquals(0, similares.noticiasTotales());
		assertEquals(ESPERADO, similares.noticiasTotales());

		int erroresTotales = 0;
		for (int i = 0; i < similares.noticiasTotales(); i++) {
			for (int j = i + 1; j < similares.noticiasTotales(); j++) {
				if (!Boletin.fechaSimilar(similares.getNoticias().get(i), similares.getNoticias().get(j)))
					erroresTotales++;
			}
		}

		assertTrue(erroresTotales == 0);

	}

	@Test
	public void testBoletinIntervalo() {
		final GregorianCalendar INICIO = new GregorianCalendar(1998, 06, 26);
		final GregorianCalendar FIN = new GregorianCalendar(1998, 06, 30);
		final int ESPERADO;
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();

		noticias.add(new Noticia("Titular", new GregorianCalendar(1998, 06, 28), "FuenteUno", null,
				Categoria.internacional));
		noticias.add(new Noticia("Titular Distinto", new GregorianCalendar(1998, 06, 27), "FuenteDos", null,
				Categoria.nacional));
		noticias.add(new Noticia("Titular Diferente", new GregorianCalendar(1998, 06, 29), "FuenteTres", null,
				Categoria.economia));

		ESPERADO = noticias.size();

		noticias.add(new Noticia("Distinto por completo", new GregorianCalendar(2009, 01, 06), "FuenteDos", null,
				Categoria.sociedad));

		Boletin boletin = new Boletin(noticias);
		Boletin similares = boletin.boletinIntervalo(INICIO, FIN);

		assertNotNull(similares);
		assertNotNull(similares.getNoticias());
		assertNotEquals(0, similares.noticiasTotales());
		assertEquals(ESPERADO, similares.noticiasTotales());

		int erroresTotales = 0;
		for (int i = 0; i < similares.noticiasTotales(); i++) {
			if (similares.getNoticias().get(i).getFechaPublicacion().before(INICIO)
					|| similares.getNoticias().get(i).getFechaPublicacion().after(FIN))
				erroresTotales++;
		}

		assertTrue(erroresTotales == 0);
	}

	@Test
	public void testBoletinCategoria() {
		final Categoria CATEGORIA = Categoria.internacional;
		final int ESPERADO;
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();

		noticias.add(new Noticia("Titular", new GregorianCalendar(1998, 06, 28), "FuenteUno", null,
				Categoria.internacional));
		noticias.add(new Noticia("Titular", new GregorianCalendar(1998, 06, 27), "FuenteDos", null,
				Categoria.internacional));
		noticias.add(new Noticia("Titular", new GregorianCalendar(1998, 06, 29), "FuenteTres", null,
				Categoria.internacional));

		ESPERADO = noticias.size();

		noticias.add(new Noticia("Distinto por completo", new GregorianCalendar(2009, 01, 06), "FuenteDos", null,
				Categoria.sociedad));

		Boletin boletin = new Boletin(noticias);
		Boletin similares = boletin.boletinCategoria(CATEGORIA);

		assertNotNull(similares);
		assertNotNull(similares.getNoticias());
		assertNotEquals(0, similares.noticiasTotales());
		assertEquals(ESPERADO, similares.noticiasTotales());

		int erroresTotales = 0;
		for (int i = 0; i < similares.noticiasTotales(); i++) {
			for (int j = i + 1; j < similares.noticiasTotales(); j++) {
				if (!similares.getNoticias().get(i).getCategoria()
						.equals(similares.getNoticias().get(j).getCategoria()))
					erroresTotales++;
			}
		}

		assertTrue(erroresTotales == 0);
	}

	@Test
	public void testBoletinIntervaloCategoria() {
		final GregorianCalendar FECHA = new GregorianCalendar(1998, 06, 28);
		final GregorianCalendar INICIO = new GregorianCalendar(1998, 06, 20);
		final GregorianCalendar FIN = new GregorianCalendar(1998, 06, 30);
		final int ESPERADO;
		final Categoria CATEGORIA = Categoria.internacional;
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();

		noticias.add(new Noticia("Titular Valido", new GregorianCalendar(1998, 06, 28), "FuenteUno", null,
				Categoria.internacional));
		noticias.add(new Noticia("Titular Tambien Valido", new GregorianCalendar(1998, 06, 27), "FuenteDos", null,
				Categoria.internacional));

		ESPERADO = noticias.size();

		noticias.add(new Noticia("Titular Viejo", new GregorianCalendar(1995, 06, 21), "FuenteUno", null,
				Categoria.internacional));
		noticias.add(new Noticia("Titular Con Fecha No Similar", new GregorianCalendar(1998, 06, 21), "FuenteUno", null,
				Categoria.internacional));
		noticias.add(new Noticia("Titular De Distinta Categoria", new GregorianCalendar(1998, 06, 29), "FuenteTres",
				null, Categoria.economia));
		noticias.add(new Noticia("Distinto por completo", new GregorianCalendar(2009, 01, 06), "FuenteDos", null,
				Categoria.sociedad));

		Boletin boletin = new Boletin(noticias);
		Boletin similares = boletin.boletinFechaIntervaloCategoria(FECHA, INICIO, FIN, CATEGORIA);

		assertNotNull(similares);
		assertNotNull(similares.getNoticias());
		assertNotEquals(0, similares.noticiasTotales());
		assertEquals(ESPERADO, similares.noticiasTotales());

		int erroresTotales = 0;
		for (int i = 0; i < similares.noticiasTotales(); i++) {
			if (similares.getNoticias().get(i).getFechaPublicacion().before(INICIO)
					|| similares.getNoticias().get(i).getFechaPublicacion().after(FIN))
				erroresTotales++;
			for (int j = i + 1; j < similares.noticiasTotales(); j++) {
				if (!similares.getNoticias().get(i).getCategoria()
						.equals(similares.getNoticias().get(j).getCategoria()))
					erroresTotales++;
				if (!Boletin.fechaSimilar(similares.getNoticias().get(i), similares.getNoticias().get(j)))
					erroresTotales++;
			}
		}

		assertTrue(erroresTotales == 0);
	}
}
