package api.endPoints;

public class Routes {

	// For user module:swagger site https://petstore.swagger.io/
	public static String base_URL = "https://petstore.swagger.io/v2/";
	public static String post_URL = base_URL + "user";
	public static String get_URL = base_URL + "user/{userName}";
	public static String update_URL = base_URL + "user/{userName}";
	public static String delete_URL = base_URL + "user/{userName}";

	// GitHub Repository
	public static String BaseGitHub_URL = "https://api.github.com";
	public static String PostGitHub_URL = BaseGitHub_URL + "/user/repos";
	public static String GetGitHub_URL = BaseGitHub_URL + "/repos/Dheerendra6691/GitRepo1";
	public static String PutGitHub_URL = BaseGitHub_URL + "/user/repos";
	public static String PatchGitHub_URL = BaseGitHub_URL + "/repos/Dheerendra6691/GitRepo2";


}
