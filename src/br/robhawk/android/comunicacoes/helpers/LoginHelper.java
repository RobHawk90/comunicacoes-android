package br.robhawk.android.comunicacoes.helpers;

import static br.robhawk.android.comunicacoes.services.Links.LOGIN_LINK;
import static br.robhawk.android.comunicacoes.services.UsuarioServices.LOGIN;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.LOGGED;
import android.content.Intent;
import android.os.Bundle;
import br.robhawk.android.comunicacoes.activities.LoginActivity;
import br.robhawk.android.comunicacoes.dtos.Usuario;
import br.robhawk.android.comunicacoes.services.UsuarioServices;
import br.robhawk.android.comunicacoes.utils.ResultHandler;
import br.robhawk.android.comunicacoes.utils.ResultHandler.Receiver;

public class LoginHelper implements Receiver {

	private LoginActivity activity;
	private ResultHandler receiver;

	public LoginHelper(LoginActivity activity) {
		this.activity = activity;

		receiver = new ResultHandler(this);
	}

	public void startLogin(Usuario usuario) {
		Intent login = new Intent(activity, UsuarioServices.class).setAction(LOGIN).putExtra(RECEIVER, receiver)
				.putExtra(LINK, LOGIN_LINK).putExtra(DTO, usuario);
		activity.startService(login);
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		switch (resultCode) {
		case LOGGED:
			activity.loggedOn(resultData);
			break;

		default:
			break;
		}
	}
}
