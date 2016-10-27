import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import org.w3c.dom.Node;

public class User {

	private String name;

	private int uId;

	private String password;

	private Path pathToHome;

	private String fullName;

	private EnumShell shell;

	private Group mainGroup;

	private Group[] secundaryGroups;

	private Node nodo;

	public User() {
	}

	public User(String name, int uId, String password, Path pathToHome, String fullName, EnumShell shell,
			Group mainGroup, Group[] secundaryGroups, Node nodo) {

		this.name = name;
		this.uId = uId;
		this.password = password;
		this.pathToHome = pathToHome;
		this.fullName = fullName;
		this.shell = shell;

		this.mainGroup = mainGroup;
		this.secundaryGroups = secundaryGroups.clone();

		this.nodo = nodo;

	}

	public void setSecundaryGroups(Group secundaryGroup) {

		Group[] copy = new Group[secundaryGroups.length + 1];

		for (int i = 0; i < secundaryGroups.length; i++)
			copy[i] = secundaryGroups[i];

		copy[copy.length - 1] = secundaryGroup;
		secundaryGroups = copy.clone();

	}

	public void removeSecundaryGroup(Group secundaryGroup) {

		Group[] copy = new Group[secundaryGroups.length - 1];

		for (int i = 0, j = 0; i < secundaryGroups.length; i++, j++) {
			if (secundaryGroups[i].getgID() == secundaryGroup.getgID())
				i++;
			copy[j] = secundaryGroups[i];
		}

		secundaryGroups = copy.clone();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Path getPathToHome() {
		return pathToHome;
	}

	public void setPathToHome(String pathToHome) {
		File file = new File(pathToHome);
		Path home = file.toPath();
		this.pathToHome = home;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public EnumShell getShell() {
		return shell;
	}

	public void setShell(String shell) {

		EnumShell enumShell = EnumShell.valueOf(shell);
		this.shell = enumShell;
	}

	public Group getMainGroup() {
		return mainGroup;
	}

	public void setMainGroup(Group mainGroup) {
		this.mainGroup = mainGroup;
	}

	public Group[] getSecundaryGroups() {
		return secundaryGroups;
	}

	public void setSecundaryGroups(Group[] secundaryGroups) {
		this.secundaryGroups = secundaryGroups;
	}

	public Node getNodo() {
		return nodo;
	}

	public void setNodo(org.w3c.dom.Node userNode) {
		this.nodo = userNode;
	}

}
