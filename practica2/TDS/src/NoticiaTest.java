package TDS.src;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.GregorianCalendar;

import org.junit.Test;

public class NoticiaTest {

	@Test
	public void testNoticia() {
		
		URL url;
		try {
			url = new URL("http://www.google.es");
			Noticia noticia = new Noticia("Titular", new GregorianCalendar(1994, 06, 28), "Fuente", url,
					Categoria.economia);
			assertNotNull(noticia);
			assertNotNull(noticia.getTitular());
			assertNotNull(noticia.getFechaPublicacion());
			assertNotNull(noticia.getFuente());
			assertNotNull(noticia.getUrl());
			assertNotNull(noticia.getCategoria());
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

	@Test
	public void testTitularValido() {
		URL url;
		String titular = "Titular";
		
		try {
			
			url = new URL("http://www.google.es");
			Noticia noticia = new Noticia(titular, new GregorianCalendar(1994, 06, 28), "Fuente", url,
					Categoria.economia);
			assertNotNull(noticia);
			assertNotNull(titular);
			assertTrue(noticia.titularValido(titular));
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testComparaFecha() {
		
		URL url;
		GregorianCalendar primera =new GregorianCalendar(1994, 06, 28);
		GregorianCalendar segunda =new GregorianCalendar(1995, 06, 26);
		GregorianCalendar igual   =new GregorianCalendar(1994, 06, 28);

		
		
		try {
			
			url = new URL("http://www.google.es");
			Noticia noticia1 = new Noticia("Titular", primera, "Fuente", url,
					Categoria.economia);
			Noticia noticia2 = new Noticia("Titular", segunda, "Fuente", url,
					Categoria.economia);
			Noticia noticia3 = new Noticia("Titular", igual, "Fuente", url,
					Categoria.economia);
			
			
			assertNotNull(noticia1);
			assertNotNull(noticia2);
			assertNotNull(noticia3);
			assertNotNull(primera);
			assertNotNull(segunda);
			assertNotNull(igual);
			
			assertSame(noticia1.comparaFecha(noticia2),1); //1
			assertSame(noticia2.comparaFecha(noticia1),-1);
			assertSame(noticia1.comparaFecha(noticia3),0);

			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testNoticiaSimilar() {

		URL url;
		GregorianCalendar primera =new GregorianCalendar(1994, 06, 28);
		GregorianCalendar segunda =new GregorianCalendar(1995, 06, 27);
		
		try {
			
			url = new URL("http://www.google.es");
			Noticia noticia1 = new Noticia("Titular", primera, "Fuente", url,
					Categoria.economia);
			Noticia noticia2 = new Noticia("Titular", segunda, "Fuente", url,
					Categoria.economia);

			assertNotNull(noticia1);
			assertNotNull(noticia2);
			
			assertTrue(noticia1.noticiaSimilar(noticia2));
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetTitular() {
		URL url;
		try {
			url = new URL("http://www.google.es");
			Noticia noticia = new Noticia("Titular", new GregorianCalendar(1994, 06, 28), "Fuente", url,
					Categoria.economia);
			
			assertNotNull(noticia.getTitular());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

	@Test
	public void testGetFechaPublicacion() {
		URL url;
		try {
			url = new URL("http://www.google.es");
			Noticia noticia = new Noticia("Titular", new GregorianCalendar(1994, 06, 28), "Fuente", url,
					Categoria.economia);
			
			assertNotNull(noticia.getFechaPublicacion());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	@Test
	public void testGetFuente() {
		URL url;
		try {
			url = new URL("http://www.google.es");
			Noticia noticia = new Noticia("Titular", new GregorianCalendar(1994, 06, 28), "Fuente", url,
					Categoria.economia);
			
			assertNotNull(noticia.getFuente());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	@Test
	public void testGetUrl() {
		URL url;
		try {
			url = new URL("http://www.google.es");
			Noticia noticia = new Noticia("Titular", new GregorianCalendar(1994, 06, 28), "Fuente", url,
					Categoria.economia);
			
			assertNotNull(noticia.getUrl());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	@Test
	public void testGetCategoria() {
		URL url;
		try {
			url = new URL("http://www.google.es");
			Noticia noticia = new Noticia("Titular", new GregorianCalendar(1994, 06, 28), "Fuente", url,
					Categoria.economia);
			
			assertNotNull(noticia.getCategoria());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

}
