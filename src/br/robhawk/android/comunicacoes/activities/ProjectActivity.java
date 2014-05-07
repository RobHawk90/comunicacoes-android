package br.robhawk.android.comunicacoes.activities;

import static br.robhawk.android.comunicacoes.utils.IntentExtras.JSON;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.adapters.ProjectAdapter;
import br.robhawk.android.comunicacoes.dialogs.AddOrEditProjectDialog;
import br.robhawk.android.comunicacoes.dtos.Projeto;
import br.robhawk.android.comunicacoes.helpers.ProjectHelper;
import br.robhawk.android.comunicacoes.json.JsonParser;

public class ProjectActivity extends Activity {

	private static final int ATTACH_STAKEHOLDERS = 0;

	private ListView list;
	private ProjectHelper helper;
	private AddOrEditProjectDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);

		helper = new ProjectHelper(this);
		helper.getProjects();

		dialog = new AddOrEditProjectDialog(this);

		list = (ListView) findViewById(R.id.listProjects);
		registerForContextMenu(list);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.context_project, menu);

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		Projeto project = (Projeto) list.getItemAtPosition(info.position);

		switch (item.getItemId()) {
		case R.id.removeProject:
			helper.remove(project);
			break;

		case R.id.attachStakeholders:
			Intent stakeholder = new Intent(this, StakeholderActivity.class);
			startActivityForResult(stakeholder, ATTACH_STAKEHOLDERS);
			break;

		case R.id.editProject:
			dialog.show(project);
			break;

		default:
			break;
		}

		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(R.string.addProject);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		dialog.show();

		return super.onOptionsItemSelected(item);
	}

	public void addOrEditProject(View v) {
		helper.addOrEdit(dialog.getProject());
		dialog.dismiss();
	}

	public void notifyProjectAdded() {
		Toast.makeText(this, R.string.project_added, Toast.LENGTH_SHORT).show();

		helper.getProjects();
	}

	public void buildProjectList(Bundle resultData) {
		String json = resultData.getString(JSON);
		ArrayList<Projeto> projects = JsonParser.convertToList(Projeto.class, json);
		list.setAdapter(new ProjectAdapter(this, projects));
	}

	public void notifyStakeholdersAttached() {
		Toast.makeText(this, "Adicionados", Toast.LENGTH_SHORT).show();
	}

	public void notifyProjectRemoved() {
		Toast.makeText(this, R.string.project_removed, Toast.LENGTH_SHORT).show();
		helper.getProjects();
	}

}
