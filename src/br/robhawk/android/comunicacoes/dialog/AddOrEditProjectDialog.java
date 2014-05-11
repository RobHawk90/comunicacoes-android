package br.robhawk.android.comunicacoes.dialog;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.activity.ProjectActivity;
import br.robhawk.android.comunicacoes.dto.Projeto;
import br.robhawk.android.comunicacoes.util.DateFormater;

public class AddOrEditProjectDialog implements OnClickListener, OnDateSetListener {

	private AlertDialog dialog;
	private DatePickerDialog datePicker;

	private EditText edtName;
	private TextView txtStartDate;
	private TextView txtEndDate;
	private Button btnAddOrEdit;

	private Projeto project;
	private boolean startDate;
	private String labelStartDate;
	private String labelEndDate;

	public AddOrEditProjectDialog(ProjectActivity activity) {
		View layout = LayoutInflater.from(activity).inflate(R.layout.dialog_project, null);

		edtName = (EditText) layout.findViewById(R.id.edtName);
		txtStartDate = (TextView) layout.findViewById(R.id.txtStartDateDialog);
		txtEndDate = (TextView) layout.findViewById(R.id.txtEndDateDialog);
		btnAddOrEdit = (Button) layout.findViewById(R.id.btnAddOrEdit);
		txtStartDate.setOnClickListener(this);
		txtEndDate.setOnClickListener(this);

		labelStartDate = activity.getResources().getString(R.string.start);
		labelEndDate = activity.getResources().getString(R.string.end);

		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		int year = calendar.get(YEAR);
		int monthOfYear = calendar.get(MONTH);
		int dayOfMonth = calendar.get(DAY_OF_MONTH);
		datePicker = new DatePickerDialog(activity, this, year, monthOfYear, dayOfMonth);

		dialog = new AlertDialog.Builder(activity).setView(layout).create();
	}

	public void show() {
		clearUI();

		dialog.show();
	}

	public void show(Projeto project) {
		setUIFrom(project);

		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}

	private void clearUI() {
		project = new Projeto();
		edtName.setText("");
		txtStartDate.setText(labelStartDate);
		txtEndDate.setText(labelEndDate);
		btnAddOrEdit.setText(R.string.add);
	}

	public void setUIFrom(Projeto project) {
		this.project = project;
		edtName.setText(project.getNome());
		txtStartDate.setText(labelStartDate + ": " + DateFormater.ddMMyyyy(project.getInicio()));
		txtEndDate.setText(labelEndDate + ": " + DateFormater.ddMMyyyy(project.getTermino()));
		btnAddOrEdit.setText(R.string.edit);
	}

	@Override
	public void onClick(View v) {
		startDate = v.getId() == R.id.txtStartDateDialog;

		datePicker.show();
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance(Locale.getDefault());
		calendar.set(year, monthOfYear, dayOfMonth);
		String date = DateFormater.ddMMyyyy(calendar.getTime());

		if (startDate) {
			txtStartDate.setText(labelStartDate + ": " + date);
			project.setInicio(calendar.getTime());
		} else {
			txtEndDate.setText(labelEndDate + ": " + date);
			project.setTermino(calendar.getTime());
		}

	}

	public Projeto getProject() {
		project.setNome(edtName.getText().toString());

		return project;
	}

}
