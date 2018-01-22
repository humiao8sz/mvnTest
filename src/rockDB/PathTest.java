package rockDB;

public class PathTest {

	public static void main(String[] args) {
		PathTest tets = new PathTest();
		String string = tets.getClass().getResource("").getPath()+"rocksdbjni-5.8.6.jar";
		System.out.println(string.substring(1, string.length()));
	}

}
