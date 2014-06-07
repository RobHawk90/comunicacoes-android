package br.robhawk.android.comunicacoes.service;

public class Links {

	private static final String SERVER = "http://comunicacoes-web.herokuapp.com/services";
	// private static final String SERVER =
	// "http://192.168.0.102:8080/services";
	// private static final String SERVER = "http://192.168.1.48:8080/services";
	// private static final String SERVER =
	// "http://192.168.43.188:8080/services";

	// UserServices
	public static final String LOGIN_LINK = SERVER + "/usuario/login/";

	// StakeholderServices
	public static final String ADD_OR_EDIT_STAKEHOLDER_LINK = SERVER + "/stakeholder/addOrEdit/";
	public static final String GET_STAKEHOLDERS_LINK = SERVER + "/stakeholder/getAll/";
	public static final String REMOVE_STAKEHOLDER_LINK = SERVER + "/stakeholder/remove/%s/";
	public static final String REMOVE_STAKEHOLDERS_LINK = SERVER + "/stakeholder/remove/stakeholders?";
	public static final String ADD_OR_EDIT_ADDRESS_LINK = SERVER + "/stakeholder/address/";

	// ProjectServices
	public static final String ADD_OR_EDIT_PROJECT_LINK = SERVER + "/project/addOrEdit/";
	public static final String GET_PROJECTS_LINK = SERVER + "/project/getAll/";
	public static final String REMOVE_PROJECT_LINK = SERVER + "/project/remove/%s/";
}
