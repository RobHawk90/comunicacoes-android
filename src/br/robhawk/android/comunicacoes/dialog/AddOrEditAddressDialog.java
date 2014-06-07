package br.robhawk.android.comunicacoes.dialog;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.activity.StakeholderActivity;

public class AddOrEditAddressDialog {

	private AlertDialog dialog;

	// private Endereco address;

	private EditText edtStreet;
	private EditText edtNumber;
	private EditText edtNeighbourhood;
	private EditText edtPostalCode;
	private EditText edtCity;
	private EditText edtState;
	private EditText edtCountry;
	private Button btnAddOrEdit;

	public AddOrEditAddressDialog(StakeholderActivity activity) {
		View layout = LayoutInflater.from(activity).inflate(R.layout.dialog_address, null);

		edtStreet = (EditText) layout.findViewById(R.id.edtStreet);
		edtNumber = (EditText) layout.findViewById(R.id.edtNumber);
		edtNeighbourhood = (EditText) layout.findViewById(R.id.edtNeighbourhood);
		edtPostalCode = (EditText) layout.findViewById(R.id.edtPostalCode);
		edtCity = (EditText) layout.findViewById(R.id.edtCity);
		edtState = (EditText) layout.findViewById(R.id.edtState);
		edtCountry = (EditText) layout.findViewById(R.id.edtCountry);
		btnAddOrEdit = (Button) layout.findViewById(R.id.btnAddOrEdit);

		dialog = new AlertDialog.Builder(activity).setView(layout).create();
	}

	public void show() {
		clearUI();

		dialog.show();
	}

	/*public void show(Endereco adress) {
		setUIFrom(adress);

		dialog.show();
	}*/

	public void dismiss() {
		dialog.dismiss();
	}

	private void clearUI() {
		// address = new Endereco();
		edtStreet.setText("");
		edtNumber.setText("");
		edtNeighbourhood.setText("");
		edtPostalCode.setText("");
		edtCity.setText("");
		edtState.setText("");
		edtCountry.setText("");
		btnAddOrEdit.setText(R.string.add);
	}

	/*public void setUIFrom(Endereco address) {
		this.address = address;
		edtStreet.setText(address.getRua());
		edtNumber.setText(address.getNumero());
		edtNeighbourhood.setText(address.getBairro());
		edtPostalCode.setText(address.getCep());
		edtCity.setText(address.getCidade());
		edtState.setText(address.getEstado());
		edtCountry.setText(address.getPais());
		btnAddOrEdit.setText(R.string.edit);
	}*/

	/*public Endereco getAdress() {
		address.setRua(edtStreet.getText().toString());
		address.setNumero(Integer.parseInt(edtNumber.getText().toString()));
		address.setBairro(edtNeighbourhood.getText().toString());
		address.setCep(edtPostalCode.getText().toString());
		address.setCidade(edtCity.getText().toString());
		address.setEstado(edtState.getText().toString());
		address.setPais(edtCountry.getText().toString());

		return address;
	}*/

}
