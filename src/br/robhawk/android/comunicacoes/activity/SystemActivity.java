package br.robhawk.android.comunicacoes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.robhawk.android.comunicacoes.R;

public class SystemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system);
	}

	public void manageStakeholders(View v) {
		Intent stakeholder = new Intent(this, StakeholderActivity.class);
		startActivity(stakeholder);
	}

	public void manageProjects(View v) {
		Intent project = new Intent(this, ProjectActivity.class);
		startActivity(project);
	}
}
