package java_basic;

public class GetCurrentPath {

	public static void main(String[] args) {
		String currentDirectory = System.getProperty("user.dir");
		System.out.println("Root folder: " + currentDirectory);

	}

}