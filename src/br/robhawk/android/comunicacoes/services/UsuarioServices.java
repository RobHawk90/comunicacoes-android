package br.robhawk.android.comunicacoes.services;

import static br.robhawk.android.comunicacoes.services.ServiceUtils.buildEntityFrom;
import static br.robhawk.android.comunicacoes.services.ServiceUtils.execute;
import static br.robhawk.android.comunicacoes.services.ServiceUtils.isOk;
import static br.robhawk.android.comunicacoes.services.ServiceUtils.setBasicHeaders;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.LOGADO;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;

import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import br.robhawk.android.comunicacoes.dtos.Usuario;

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

		if (isOk(response))
			receiver.send(LOGADO, null);
	}
}
