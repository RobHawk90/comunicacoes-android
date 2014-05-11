package br.robhawk.android.comunicacoes.service;

import static br.robhawk.android.comunicacoes.service.ServiceUtils.buildBundleFrom;
import static br.robhawk.android.comunicacoes.service.ServiceUtils.buildEntityFrom;
import static br.robhawk.android.comunicacoes.service.ServiceUtils.execute;
import static br.robhawk.android.comunicacoes.service.ServiceUtils.isPositive;
import static br.robhawk.android.comunicacoes.service.ServiceUtils.setBasicHeaders;
import static br.robhawk.android.comunicacoes.util.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.util.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.util.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.util.ResultCodes.LOGGED;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;

import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import br.robhawk.android.comunicacoes.dto.Usuario;

public class UsuarioServices extends IntentService {

	private static final String SERVICE_NAME = UsuarioServices.class.getSimpleName();
	public static final String LOGIN = "login:" + SERVICE_NAME;

	private ResultReceiver receiver;

	public UsuarioServices() {
		super(SERVICE_NAME);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		try {

			receiver = intent.getParcelableExtra(RECEIVER);
			String action = intent.getAction();

			if (action.equals(LOGIN))
				login(intent);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void login(Intent intent) throws ClientProtocolException, IOException {
		String loginService = intent.getStringExtra(LINK);
		Usuario usuario = (Usuario) intent.getSerializableExtra(DTO);

		HttpPost post = new HttpPost(loginService);
		setBasicHeaders(post);
		post.setEntity(buildEntityFrom(usuario));

		HttpResponse response = execute(post, LOGIN);

		if (isPositive(response))
			receiver.send(LOGGED, buildBundleFrom(response));
	}
}
