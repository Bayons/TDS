
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Implementación de la interfaz UserSystemInterface
 * 
 * @author Guillermo Anta Alonso
 * @author Mario Gomez Fernandez
 * @author Miguel Bayon Sanz
 *
 */
public class UserSystemImpl implements UserSystemInterface {

	private ArrayList<User> usuarios = new ArrayList<User>();

	private ArrayList<Group> grupos = new ArrayList<Group>();

	boolean modified = false;

	Document document;

	@Override
	public void loadFrom(Path pathToXML) {

		FileReader input;
		InputSource source;
		Path path = pathToXML;

		DocumentBuilderFactory domParserFactory;
		DocumentBuilder parser;

		domParserFactory = DocumentBuilderFactory.newInstance();
		domParserFactory.setValidating(true);

		try {

			parser = domParserFactory.newDocumentBuilder();

			input = new FileReader(path.toFile());
			source = new InputSource(input);

			parser.setErrorHandler(new SimpleErrorHandler());
			document = parser.parse(source);

			int i = 0, j = 0;
			Node node = null;
			NodeList nodeList = document.getElementsByTagName("grupo");
			Group group = null;
			User user = null;
			Element element = null;

			for (i = 0; i < nodeList.getLength(); i++) {

				group = new Group();
				element = (Element) nodeList.item(i);

				node = nodeList.item(i);

				group.setName(element.getAttributes().getNamedItem("nombre").getNodeValue());
				group.setgID(Integer.parseInt(element.getAttributes().getNamedItem("gId").getNodeValue()));

				grupos.add(group);

			}

			nodeList = document.getElementsByTagName("usuario");
			String grupSecs, grupPrinc;
			int posIni;
			Group nuevoGrupSec = null;

			for (i = 0; i < nodeList.getLength(); i++) {
				user = new User();

				element = (Element) nodeList.item(i);

				NodeList nombre = element.getElementsByTagName("nombre");
				NodeList password = element.getElementsByTagName("password");
				NodeList home = element.getElementsByTagName("home");
				NodeList nombreCompleto = element.getElementsByTagName("nombreCompleto");

				user.setuId(Integer.parseInt(element.getAttributes().getNamedItem("uId").getNodeValue()));
				user.setName(nombre.item(0).getFirstChild().getTextContent());
				user.setPassword(password.item(0).getFirstChild().getTextContent());
				user.setPathToHome(home.item(0).getFirstChild().getTextContent());
				user.setFullName(nombreCompleto.item(0).getFirstChild().getTextContent());
				user.setShell(element.getAttributes().getNamedItem("shell").getNodeValue());

				grupPrinc = element.getAttributes().getNamedItem("grupoPrincipal").getNodeValue();

				for (j = 0; j < grupos.size(); j++) {
					if (grupos.get(j).getName().equals(grupPrinc)) {
						user.setMainGroup(grupos.get(j));

					}
				}

				grupSecs = element.getAttributes().getNamedItem("grupoSecundarios").getNodeValue();
				posIni = 0;
				for (int cont = 0; cont < grupSecs.length(); cont++) {

					if (!grupSecs.equals("null")) {

						user.setSecundaryGroups(getGroupByName(grupSecs));
					}

					if (grupSecs.charAt(cont) == (',')
							&& (nuevoGrupSec = getGroupByName(grupSecs.substring(posIni, cont - 1))) != null) {
						user.setSecundaryGroups(nuevoGrupSec);
						cont++;
						posIni = cont;
					}
				}

				usuarios.add(user);
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (SAXException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		modified = false;
	}

	@Override
	public void updateTo(Path pathToXML) {

		FileWriter output;
		DOMSource source;

		DocumentBuilderFactory domParserFactory;
		DocumentBuilder parser;

		domParserFactory = DocumentBuilderFactory.newInstance();
		domParserFactory.setValidating(true);

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer;

		try {

			output = new FileWriter(pathToXML.toFile());
			parser = domParserFactory.newDocumentBuilder();
			document = parser.newDocument();
			source = new DOMSource(document);
			PrintWriter pw = new PrintWriter(output);
			StreamResult result = new StreamResult(pw);

			Element sistema = document.createElement("sistema");
			document.appendChild(sistema);

			Element usuarioNuevo;
			Element grupoNuevo;

			StringBuilder grSec;
			Element tnombre;
			Element tpassword;
			Element thome;
			Element tnombcomp;

			usuarios.get(0).getMainGroup().imprimirGrupo();

			for (int i = 0; i < usuarios.size(); i++) {
				usuarioNuevo = document.createElement("usuario");

				usuarioNuevo.setAttribute("grupoPrincipal", usuarios.get(i).getMainGroup().getName());

				if (usuarios.get(i).getSecundaryGroups() == null) {

					usuarioNuevo.setAttribute("grupoSecundarios", "null");
				} else {
					grSec = new StringBuilder();
					for (int j = 0; j < usuarios.get(i).getSecundaryGroups().length
							&& usuarios.get(i).getSecundaryGroups()[j] != null; j++) {
						grSec.append(usuarios.get(i).getSecundaryGroups()[j].getName()); // ERROR
						if (j != usuarios.get(i).getSecundaryGroups().length - 1)
							grSec.append(", ");
					}
					usuarioNuevo.setAttribute("grupoSecundarios", grSec.toString());
				}
				usuarioNuevo.setAttribute("shell", usuarios.get(i).getShell().name());
				usuarioNuevo.setAttribute("uId", Integer.toString(usuarios.get(i).getuId()));

				tnombre = document.createElement("nombre");
				tnombre.appendChild(document.createTextNode(usuarios.get(i).getName()));
				usuarioNuevo.appendChild(tnombre);

				tpassword = document.createElement("password");
				tpassword.appendChild(document.createTextNode(usuarios.get(i).getPassword()));
				usuarioNuevo.appendChild(tpassword);

				thome = document.createElement("home");
				thome.appendChild(document.createTextNode(usuarios.get(i).getPathToHome().toString()));
				usuarioNuevo.appendChild(thome);

				tnombcomp = document.createElement("nombreCompleto");
				tnombcomp.appendChild(document.createTextNode(usuarios.get(i).getFullName()));
				usuarioNuevo.appendChild(tnombcomp);

				sistema.appendChild(usuarioNuevo);
			}

			for (int i = 0; i < grupos.size(); i++) {
				grupoNuevo = document.createElement("grupo");
				grupoNuevo.setAttribute("gId", Integer.toString(grupos.get(i).getgID()));
				grupoNuevo.setAttribute("miembros", Integer.toString(grupos.get(i).getMiembros().length));
				grupoNuevo.setAttribute("nombre", grupos.get(i).getName());

				sistema.appendChild(grupoNuevo);
			}

			transformer = tFactory.newTransformer();

			transformer.transform(source, result);

		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		}

	}

	@Override
	public boolean isXMLLoaded() {
		if (document != null)
			return true;
		return false;
	}

	@Override
	public boolean isModifiedAfterLoaded() {
		return modified;
	}

	@Override
	public void createNewUser(String name, int uId, String password, Path pathToHome, String fullName, EnumShell shell,
			Group mainGroup, Group[] secundaryGroups) {

		boolean exists = false;

		for (int i = 0; i < usuarios.size(); i++) {
			if (uId == usuarios.get(i).getuId()) {
				exists = true;
				break;
			}
		}

		if (!exists) {
			User user = new User(name, uId, password, pathToHome, fullName, shell, mainGroup, secundaryGroups);
			usuarios.add(user);
			modified = true;
		}
	}

	@Override
	public User getUserById(int uId) {
		User user = null;
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getuId() == uId) {
				user = usuarios.get(i);
				return user;
			}
		}

		return null;
	}

	@Override
	public User getUserByName(String name) {
		User user = null;
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getName().equals(name)) {
				user = usuarios.get(i);
				return user;
			}
		}

		return null;
	}

	@Override
	public Group getGroupById(int gId) {
		Group group = null;
		for (int i = 0; i < grupos.size(); i++) {
			if (grupos.get(i).getgID() == gId) {
				group = grupos.get(i);
				return group;
			}
		}

		return null;
	}

	@Override
	public Group getGroupByName(String name) {
		Group group = null;
		for (int i = 0; i < grupos.size(); i++) {
			if (grupos.get(i).getName().equals(name)) {
				group = grupos.get(i);
				return group;
			}
		}

		return null;
	}

	@Override
	public void createNewGroup(String name, int gId) {

		boolean exists = false;

		for (int i = 0; i < grupos.size(); i++) {
			if (grupos.get(i).getgID() == gId) {
				exists = true;
				break;
			}
		}

		if (!exists) {
			Group group = new Group(name, gId);
			grupos.add(group);

			modified = true;
		}
	}

	@Override
	public void addUserToGroup(User user, Group group) {
		if (user != null && group != null) {

			if (user.getMainGroup().getgID() != group.getgID() && !user.isInGroup(group)) {
				group.setMiembros(user);
				user.setSecundaryGroups(group);
				modified = true;
			}
		}
	}

	@Override
	public void removeUserFromGroup(User user, Group group) {
		if (user.getMainGroup().getgID() != group.getgID() && user.isInGroup(group)) {
			group.removeUser(user);
			user.removeSecundaryGroup(group);
			modified = true;
		}
	}

	// No funciona
	@Override

	public void removeUserFromSystem(User user) {

		if (usuarios.contains(user)) {
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).equals(user)) {
					usuarios.remove(i);
					modified = true;
				}
			}
		}
	}

	@Override
	public void removeGroupFromSystem(Group group) {

		int i = 0;
		if (grupos.contains(group)) {
			for (i = 0; i < grupos.size(); i++) {
				if (grupos.get(i).equals(group)) {
					grupos.remove(i);
					modified = true;
				}
			}
		}
	}

	public ArrayList<User> getUsuarios() {
		return (ArrayList<User>) usuarios.clone();
	}

	public ArrayList<Group> getGrupos() {
		return (ArrayList<Group>) grupos.clone();
	}

}
