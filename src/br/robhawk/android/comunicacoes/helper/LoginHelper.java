package br.robhawk.android.comunicacoes.helper;

import static br.robhawk.android.comunicacoes.service.Links.LOGIN_LINK;
import static br.robhawk.android.comunicacoes.service.UsuarioServices.LOGIN;
import static br.robhawk.android.comunicacoes.util.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.util.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.util.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.util.ResultCodes.LOGGED;
import android.content.Intent;
import android.os.Bundle;
import br.robhawk.android.comunicacoes.activity.LoginActivity;
import br.robhawk.android.comunicacoes.dto.Usuario;
import br.robhawk.android.comunicacoes.service.UsuarioServices;
import br.robhawk.android.comunicacoes.util.ResultHandler;
import br.robhawk.android.comunicacoes.util.ResultHandler.Receiver;

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
