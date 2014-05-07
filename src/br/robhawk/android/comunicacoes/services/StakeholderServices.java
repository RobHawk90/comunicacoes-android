package br.robhawk.android.comunicacoes.services;

import static br.robhawk.android.comunicacoes.services.Links.ADD_OR_EDIT_STAKEHOLDER_LINK;
import static br.robhawk.android.comunicacoes.services.Links.GET_STAKEHOLDERS_LINK;
import static br.robhawk.android.comunicacoes.services.ServiceUtils.buildBundleFrom;
import static br.robhawk.android.comunicacoes.services.ServiceUtils.buildEntityFrom;
import static br.robhawk.android.comunicacoes.services.ServiceUtils.execute;
import static br.robhawk.android.comunicacoes.services.ServiceUtils.isOk;
import static br.robhawk.android.comunicacoes.services.ServiceUtils.setBasicHeaders;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.STAKEHOLDERS_FOUND;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.STAKEHOLDER_ADDED;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.STAKEHOLDER_REMOVED;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import br.robhawk.android.comunicacoes.dtos.Stakeholder;

public class StakeholderServices extends IntentService {

	private static final String SERVICE_NAME = StakeholderServices.class.getSimpleName();
	public static final String ADD_OR_EDIT_STAKEHOLDER = "addOrEditStakeholder:" + SERVICE_NAME;
	public static final String GET_STAKEHOLDERS = "getStakeholders:" + SERVICE_NAME;
	public static final String REMOVE_STAKEHOLDER = "removeStakeholder:" + SERVICE_NAME;

	private ResultReceiver receiver;

	public StakeholderServices() {
		super(SERVICE_NAME);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try {

			receiver = intent.getParcelableExtra(RECEIVER);
			String action = intent.getAction();

			if (action.equals(ADD_OR_EDIT_STAKEHOLDER))
				addOrEditStakeholder(intent);
			else if (action.equals(GET_STAKEHOLDERS))
				getStakeholders(intent);
			else if (action.equals(REMOVE_STAKEHOLDER))
				removeStakeholder(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removeStakeholder(Intent intent) throws ClientProtocolException, IOException {
		String removeStakeholder = intent.getStringExtra(LINK);

		HttpDelete delete = new HttpDelete(removeStakeholder);
		setBasicHeaders(delete);

		HttpResponse response = execute(delete, REMOVE_STAKEHOLDER);

		if (isOk(response))
			receiver.send(STAKEHOLDER_REMOVED, null);
	}

	private void getStakeholders(Intent intent) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(GET_STAKEHOLDERS_LINK);
		setBasicHeaders(get);

		HttpResponse response = execute(get, GET_STAKEHOLDERS);

		if (isOk(response))
			receiver.send(STAKEHOLDERS_FOUND, buildBundleFrom(response));
	}

	private void addOrEditStakeholder(Intent intent) throws ClientProtocolException, IOException {
		Stakeholder stakeholder = (Stakeholder) intent.getSerializableExtra(DTO);

		HttpPost post = new HttpPost(ADD_OR_EDIT_STAKEHOLDER_LINK);
		setBasicHeaders(post);
		post.setEntity(buildEntityFrom(stakeholder));

		HttpResponse response = execute(post, ADD_OR_EDIT_STAKEHOLDER);

		if (isOk(response))
			receiver.send(STAKEHOLDER_ADDED, buildBundleFrom(response));
	}

}
