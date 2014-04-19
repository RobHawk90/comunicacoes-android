package br.robhawk.android.comunicacoes.login;

import static br.robhawk.android.comunicacoes.intents.UsuarioIntents.login;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.LOGADO;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.dtos.Usuario;
import br.robhawk.android.comunicacoes.utils.ResultHandler;
import br.robhawk.android.comunicacoes.utils.ResultHandler.Receiver;

public class MainActivity extends Activity implements OnClickListener, Receiver {

	public ResultHandler receiver;
	public Usuario usuario;

	private EditText edtUsuario;
	private EditText edtSenha;
	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		receiver = new ResultHandler(this);

		edtUsuario = (EditText) findViewById(R.id.edtUsuario);
		edtSenha = (EditText) findViewById(R.id.edtSenha);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		usuario = new Usuario();
		usuario.setNome(edtUsuario.getText().toString());
		usuario.setSenha(edtSenha.getText().toString());

		login(this);
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		switch (resultCode) {
		case LOGADO:
			Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

}
