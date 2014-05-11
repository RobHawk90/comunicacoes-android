package br.robhawk.android.comunicacoes.service;

import static br.robhawk.android.comunicacoes.service.Links.ADD_OR_EDIT_PROJECT_LINK;
import static br.robhawk.android.comunicacoes.service.Links.GET_PROJECTS_LINK;
import static br.robhawk.android.comunicacoes.service.ServiceUtils.buildBundleFrom;
import static br.robhawk.android.comunicacoes.service.ServiceUtils.buildEntityFrom;
import static br.robhawk.android.comunicacoes.service.ServiceUtils.execute;
import static br.robhawk.android.comunicacoes.service.ServiceUtils.isPositive;
import static br.robhawk.android.comunicacoes.service.ServiceUtils.setBasicHeaders;
import static br.robhawk.android.comunicacoes.util.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.util.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.util.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.util.ResultCodes.PROJECTS_FOUND;
import static br.robhawk.android.comunicacoes.util.ResultCodes.PROJECT_ADDED;
import static br.robhawk.android.comunicacoes.util.ResultCodes.PROJECT_REMOVED;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import br.robhawk.android.comunicacoes.dto.Projeto;

public class ProjectServices extends IntentService {

	private static final String SERVICE_NAME = ProjectServices.class.getSimpleName();
	public static final String ADD_OR_EDIT_PROJECT = "addProject:" + SERVICE_NAME;
	public static final String GET_PROJECTS = "getProjects:" + SERVICE_NAME;
	public static final String REMOVE_PROJECT = "removeProject:" + SERVICE_NAME;

	private ResultReceiver receiver;

	public ProjectServices() {
		super(SERVICE_NAME);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try {
			receiver = intent.getParcelableExtra(RECEIVER);
			String action = intent.getAction();

			if (action.equals(ADD_OR_EDIT_PROJECT))
				addOrEditProject(intent);
			else if (action.equals(GET_PROJECTS))
				getProjects(intent);
			else if (action.equals(REMOVE_PROJECT))
				remove(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void remove(Intent intent) throws ClientProtocolException, IOException {
		String removeProject = intent.getStringExtra(LINK);

		HttpDelete delete = new HttpDelete(removeProject);
		setBasicHeaders(delete);

		HttpResponse response = execute(delete, REMOVE_PROJECT);

		if (isPositive(response))
			receiver.send(PROJECT_REMOVED, buildBundleFrom(response));
	}

	private void getProjects(Intent intent) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(GET_PROJECTS_LINK);
		setBasicHeaders(get);

		HttpResponse response = execute(get, GET_PROJECTS);

		if (isPositive(response))
			receiver.send(PROJECTS_FOUND, buildBundleFrom(response));
	}

	private void addOrEditProject(Intent intent) throws ClientProtocolException, IOException {
		Projeto project = (Projeto) intent.getSerializableExtra(DTO);

		HttpPost post = new HttpPost(ADD_OR_EDIT_PROJECT_LINK);
		setBasicHeaders(post);
		post.setEntity(buildEntityFrom(project));

		HttpResponse response = execute(post, ADD_OR_EDIT_PROJECT);

		if (isPositive(response))
			receiver.send(PROJECT_ADDED, buildBundleFrom(response));
	}

}
