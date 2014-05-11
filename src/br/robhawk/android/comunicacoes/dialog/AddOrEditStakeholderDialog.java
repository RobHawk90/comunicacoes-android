package br.robhawk.android.comunicacoes.dialog;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.activity.StakeholderActivity;
import br.robhawk.android.comunicacoes.dto.Stakeholder;
import br.robhawk.android.comunicacoes.dto.Usuario;
import br.robhawk.android.comunicacoes.util.ImageEditor;

public class AddOrEditStakeholderDialog {

	private StakeholderActivity activity;
	private Stakeholder stakeholder;
	private AlertDialog dialog;

	private String encodedPhoto;
	private ImageView imgPhoto;
	private EditText edtName;
	private EditText edtLogin;
	private EditText edtPassword;
	private EditText edtPhone;
	private EditText edtEmail;
	private Button btnAddOrEdit;

	public AddOrEditStakeholderDialog(StakeholderActivity activity) {
		this.activity = activity;
		View layout = LayoutInflater.from(activity).inflate(R.layout.dialog_stakeholder, null);

		imgPhoto = (ImageView) layout.findViewById(R.id.imgPhoto);
		edtName = (EditText) layout.findViewById(R.id.edtName);
		edtLogin = (EditText) layout.findViewById(R.id.edtLoginStakeholder);
		edtPassword = (EditText) layout.findViewById(R.id.edtPasswordStakeholder);
		edtPhone = (EditText) layout.findViewById(R.id.edtPhone);
		edtEmail = (EditText) layout.findViewById(R.id.edtEmail);
		btnAddOrEdit = (Button) layout.findViewById(R.id.btnAddOrEdit);

		dialog = new AlertDialog.Builder(activity).setView(layout).create();
	}

	public Stakeholder getStakeholder() {
		stakeholder.setFoto(encodedPhoto);
		stakeholder.setEmail(edtEmail.getText().toString());
		stakeholder.setNome(edtName.getText().toString());
		stakeholder.setTelefone(edtPhone.getText().toString());
		stakeholder.setUsuario(writeUser());

		return stakeholder;
	}

	private Usuario writeUser() {
		Usuario usuario = stakeholder.getUsuario();
		usuario.setLogin(edtLogin.getText().toString());
		usuario.setSenha(edtPassword.getText().toString());

		return usuario;
	}

	public void setPhotoView(Intent choosen) {
		Bitmap photo = ImageEditor.buildPhoto(choosen);
		encodedPhoto = ImageEditor.encode(photo);
		imgPhoto.setImageBitmap(photo);
	}

	public void show() {
		clearUI();

		dialog.show();
	}

	public void show(Stakeholder stakeholder) {
		setUIFrom(stakeholder);

		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}

	private void clearUI() {
		stakeholder = new Stakeholder();
		encodedPhoto = null;
		imgPhoto.setImageBitmap(null);
		imgPhoto.setBackgroundResource(R.drawable.ic_photo);
		edtName.setText("");
		edtLogin.setText("");
		edtPassword.setText("");
		edtEmail.setText("");
		edtPhone.setText("");
		btnAddOrEdit.setText(R.string.add);
	}

	private void setUIFrom(Stakeholder stakeholder) {
		Bitmap photo = ImageEditor.decodeFrom(stakeholder, activity, 100);
		this.stakeholder = stakeholder;
		encodedPhoto = stakeholder.getFoto();
		imgPhoto.setImageBitmap(photo);
		edtName.setText(stakeholder.getNome());
		edtLogin.setText(stakeholder.getUsuario().getLogin());
		edtPassword.setText(stakeholder.getUsuario().getSenha());
		edtEmail.setText(stakeholder.getEmail());
		edtPhone.setText(stakeholder.getTelefone());
		btnAddOrEdit.setText(R.string.edit);
	}
}
