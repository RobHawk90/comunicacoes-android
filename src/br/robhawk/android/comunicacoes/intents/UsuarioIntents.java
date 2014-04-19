package br.robhawk.android.comunicacoes.intents;

import static br.robhawk.android.comunicacoes.services.UsuarioServices.LOGIN;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.RECEIVER;
import android.content.Intent;
import br.robhawk.android.comunicacoes.database.ConfigUtils;
import br.robhawk.android.comunicacoes.login.MainActivity;
import br.robhawk.android.comunicacoes.services.UsuarioServices;

public class UsuarioIntents {

	private static final String SERVER = ConfigUtils.serverUrl();
	private static final String LOGIN_LINK = SERVER + "/usuario/login/";

	private UsuarioIntents() {
	}

	public static void login(MainActivity context) {
		Intent login = new Intent(context, UsuarioServices.class);
		login.setAction(LOGIN).putExtra(RECEIVER, context.receiver).putExtra(LINK, LOGIN_LINK)
				.putExtra(DTO, context.usuario);
		context.startService(login);
	}
}
