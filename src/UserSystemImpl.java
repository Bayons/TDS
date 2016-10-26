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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public abstract class UserSystemImpl implements UserSystemInterface{

	
	private ArrayList<User> usuarios = new ArrayList<User>();
	
	private ArrayList<Group> grupos = new ArrayList<Group>();
	
 	
	
	@Override
	public void loadFrom(Path pathToXML) {
		
		FileReader input;
		InputSource source;
		Path path =  pathToXML;;
		
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
			

			NamedNodeMap nodeMap = null;
			Node node = null;
			NodeList nodeList = document.getElementsByTagName("usuario");
			User user = null;
			
			
			for (int i=0; i<nodeList.getLength();i++) {
				user = new User();
				
				node = nodeList.item(i);
				user.setNodo(node);
				
				nodeMap = node.getAttributes();
				node = nodeMap.getNamedItem("uId");
				user.setuId(Integer.parseInt(node.getNodeValue()));
				
				node = nodeMap.getNamedItem("nombre");
				user.setName(node.getNodeValue());
				
				node = nodeMap.getNamedItem("contraseña");
				user.setPassword(node.getNodeValue());
				
				node = nodeMap.getNamedItem("home");
				user.setPathToHome(node.getNodeValue());
				
				
			
			}
			
			
			
			nodeList = document.getElementsByTagName("libro");
			
			for (int i=0; i<nodeList.getLength();i++) {
			node = nodeList.item(i);
			nodeMap = node.getAttributes();
			node = nodeMap.getNamedItem("referencia");
			System.out.println(node.getNodeName() + "-> " +
			 node.getNodeValue());
			}
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer;
			
			try {
				
				transformer = tFactory.newTransformer();
				DOMSource domSource = new DOMSource(document);
				StreamResult result = new StreamResult(System.out);
				
				transformer.transform(domSource, result);
				
			} catch (TransformerConfigurationException e1) {
				e1.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
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
		
		
	}

	@Override
	public boolean isXMLLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isModifiedAfterLoaded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createNewUser(String name, int uId, String password, Path pathToHome, String fullName, EnumShell shell,
			Group mainGroup, Group[] secundaryGroups) {
		
		User user = new User(name, uId, password, pathToHome, fullName, shell, mainGroup, secundaryGroups, null);
		
		
	}

	@Override
	public User getUserById(int uId) {
		
		NodeList nodeList = document.getElementsByTagName("usuario");
		NamedNodeMap nodeMap = null;
		Node node = null;
		User user = null;
		
		for (int i=0; i<nodeList.getLength();i++) {
				node = nodeList.item(i);
				nodeMap = node.getAttributes();
				node = nodeMap.getNamedItem("uId");
				
				if(Integer.parseInt(node.getNodeValue()) == uId){
					user = (User) nodeList.item(i);
				}
				
			}
		
		return user;
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group getGroupById(int gId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group getGroupByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createNewGroup(String name, int gId) {
		
		Group group = new Group(name, gId);
		
	}

	@Override
	public void addUserToGroup(User user, Group group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUserFromGroup(User user, Group group) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUserFromSystem(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeGroupFromSystem(Group group) {
		// TODO Auto-generated method stub
		
	}

}
