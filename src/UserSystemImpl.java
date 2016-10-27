import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class UserSystemImpl implements UserSystemInterface {

	ArrayList<User> usuarios = new ArrayList<User>();

	ArrayList<Group> grupos = new ArrayList<Group>();

	@Override
	public void loadFrom(Path pathToXML) {

		FileReader input;
		InputSource source;
		Path path = pathToXML;
		;

		DocumentBuilderFactory domParserFactory;
		DocumentBuilder parser;

		domParserFactory = DocumentBuilderFactory.newInstance();
		domParserFactory.setValidating(true);

		try {

			parser = domParserFactory.newDocumentBuilder();

			System.out.println(parser.getClass());
			System.out.println(parser.isValidating());

			input = new FileReader(path.toFile());
			source = new InputSource(input);

			parser.setErrorHandler(new SimpleErrorHandler());
			Document document = parser.parse(source);

			System.out.println(document.getDoctype().getName());

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
				group.setNode(node);

				group.setName(element.getAttributes().getNamedItem("nombre").getNodeValue());
				group.setgID(Integer.parseInt(element.getAttributes().getNamedItem("gId").getNodeValue()));

				grupos.add(group);
			}

			nodeList = document.getElementsByTagName("usuario");

			for (i = 0; i < nodeList.getLength(); i++) {
				user = new User();

				element = (Element) nodeList.item(i);
				user.setNodo(element);

				NodeList nombre = element.getElementsByTagName("nombre");
				NodeList contrasena = element.getElementsByTagName("contraseņa");
				NodeList home = element.getElementsByTagName("home");
				NodeList nombreCompleto = element.getElementsByTagName("nombreCompleto");

				user.setuId(Integer.parseInt(element.getAttributes().getNamedItem("uId").getNodeValue()));
				user.setName(nombre.item(0).getFirstChild().getTextContent());
				user.setPassword(contrasena.item(0).getFirstChild().getTextContent());
				user.setPathToHome(home.item(0).getFirstChild().getTextContent());
				user.setFullName(nombreCompleto.item(0).getFirstChild().getTextContent());
				user.setShell(element.getAttributes().getNamedItem("shell").getNodeValue());

				for (j = 0; j < grupos.size(); j++) {
					if (grupos.get(j).equals(element.getAttributes().getNamedItem("grupoPrincipal").getNodeValue())) {
						user.setMainGroup(grupos.get(j));
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

	}

	@Override
	public void updateTo(Path pathToXML) {

		FileReader input;
		InputSource source;
		Path path = pathToXML;
		;

		DocumentBuilderFactory domParserFactory;
		DocumentBuilder parser;

		domParserFactory = DocumentBuilderFactory.newInstance();
		domParserFactory.setValidating(true);

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer;

		try {

			parser = domParserFactory.newDocumentBuilder();

			input = new FileReader(path.toFile());
			source = new InputSource(input);

			Document document = parser.parse(source);

			transformer = tFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult result = new StreamResult(System.out);

			transformer.transform(domSource, result);

		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (SAXException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		}

	}

	@Override
	public boolean isXMLLoaded() {// HACER

		return false;
	}

	@Override
	public boolean isModifiedAfterLoaded() {// HACER
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createNewUser(String name, int uId, String password, Path pathToHome, String fullName, EnumShell shell,
			Group mainGroup, Group[] secundaryGroups) {

		User user = new User(name, uId, password, pathToHome, fullName, shell, mainGroup, secundaryGroups, null);
		usuarios.add(user);

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

		Group group = new Group(name, gId);
		grupos.add(group);
	}

	@Override
	public void addUserToGroup(User user, Group group) {// revisar
		if (user.getMainGroup().getgID() != group.getgID() && !user.isInGroup(group)) {
			group.setMiembros(user);
			user.setSecundaryGroups(group);
		}
	}

	@Override
	public void removeUserFromGroup(User user, Group group) {
		if (user.getMainGroup().getgID() != group.getgID() && user.isInGroup(group)) { // revisar
			group.removeUser(user);
			user.removeSecundaryGroup(group);
		}
	}

	@Override
	public void removeUserFromSystem(User user) {

		if (usuarios.contains(user)) {
			for (int i = 0; i < usuarios.size(); i++) {
				if (usuarios.get(i).equals(user))
					usuarios.remove(i);
			}
		}
	}

	@Override
	public void removeGroupFromSystem(Group group) {

		int i = 0;
		if (grupos.contains(group)) {
			for (i = 0; i < grupos.size(); i++) {
				if (grupos.get(i).equals(group))
					grupos.remove(i);
			}
		}
	}

}
