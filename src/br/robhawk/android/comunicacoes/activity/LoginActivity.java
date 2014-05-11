package br.robhawk.android.comunicacoes.activity;

import static br.robhawk.android.comunicacoes.util.IntentExtras.JSON;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.dto.Usuario;
import br.robhawk.android.comunicacoes.helper.LoginHelper;
import br.robhawk.android.comunicacoes.json.JsonParser;

public class LoginActivity extends Activity {

	private LoginHelper helper;

	private EditText edtUsuario;
	private EditText edtSenha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		helper = new LoginHelper(this);

		edtUsuario = (EditText) findViewById(R.id.edtLogin);
		edtSenha = (EditText) findViewById(R.id.edtPassword);
	}

	public void login(View v) {
		Usuario user = buildUser();
		helper.startLogin(user);
	}

	private Usuario buildUser() {
		Usuario usuario = new Usuario();
		usuario.setLogin(edtUsuario.getText().toString());
		usuario.setSenha(edtSenha.getText().toString());
		return usuario;
	}

	public void loggedOn(Bundle resultData) {
		getAuthenticatedUserFrom(resultData);
		startSystem();
	}

	private Usuario getAuthenticatedUserFrom(Bundle resultData) {
		String json = resultData.getString(JSON);
		return JsonParser.convertToType(Usuario.class, json);
	}

	private void startSystem() {
		Intent system = new Intent(this, SystemActivity.class);
		startActivity(system);
	}

}
