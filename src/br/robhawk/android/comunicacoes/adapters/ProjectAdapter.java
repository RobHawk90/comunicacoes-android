package br.robhawk.android.comunicacoes.adapters;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.activities.ProjectActivity;
import br.robhawk.android.comunicacoes.dtos.Projeto;
import br.robhawk.android.comunicacoes.utils.DateFormater;

public class ProjectAdapter extends ArrayAdapter<Projeto> {

	private LayoutInflater inflater;

	public ProjectAdapter(ProjectActivity activity, List<Projeto> projects) {
		super(activity, R.layout.adapter_project, projects);

		inflater = LayoutInflater.from(activity);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Projeto project = getItem(position);
		View layout = inflater.inflate(R.layout.adapter_project, null);

		TextView txtName = (TextView) layout.findViewById(R.id.txtName);
		TextView txtStartDate = (TextView) layout.findViewById(R.id.txtStartDate);
		TextView txtEndDate = (TextView) layout.findViewById(R.id.txtEndDate);

		txtName.setText(project.getNome());
		txtStartDate.setText(DateFormater.ddMMyyyy(project.getInicio()));
		txtEndDate.setText(DateFormater.ddMMyyyy(project.getTermino()));

		return layout;
	}

}
