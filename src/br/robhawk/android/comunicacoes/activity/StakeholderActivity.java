package br.robhawk.android.comunicacoes.activity;

import static br.robhawk.android.comunicacoes.util.IntentExtras.JSON;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.adapter.StakeholderAdapter;
import br.robhawk.android.comunicacoes.dialog.AddOrEditStakeholderDialog;
import br.robhawk.android.comunicacoes.dto.Stakeholder;
import br.robhawk.android.comunicacoes.helper.StakeholderHelper;
import br.robhawk.android.comunicacoes.json.JsonParser;
import br.robhawk.android.comunicacoes.util.Notifier;

public class StakeholderActivity extends Activity {

	private final int REQUEST_PHOTO = 0;

	private StakeholderHelper helper;
	private AddOrEditStakeholderDialog dialog;

	private ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stakeholder);

		helper = new StakeholderHelper(this);
		helper.getStakeholders();

		dialog = new AddOrEditStakeholderDialog(this);

		list = (ListView) findViewById(R.id.listStakeholders);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.option_stakeholder, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addStakeholder:
			dialog.show();
			break;

		case R.id.attachStakeholders:
			StakeholderAdapter adapter = (StakeholderAdapter) list.getAdapter();
			List<Stakeholder> stakeholders = adapter.getSelectedStakeholders();

			if (stakeholders.isEmpty())
				Notifier.showMessage(this, R.string.shouldSelectAnItem);
			else {
				String json = JsonParser.convertToJson(stakeholders);
				Intent project = new Intent(this, ProjectActivity.class);
				project.putExtra(JSON, json);
				startActivity(project);
			}
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	public void loadPhoto(View v) {
		Intent fileChooser = new Intent(Intent.ACTION_GET_CONTENT);
		fileChooser.setType("gagt/sdf");
		startActivityForResult(fileChooser, REQUEST_PHOTO);
	}

	public void addOrEditStakeholder(View v) {
		helper.addOrEdit(dialog.getStakeholder());
		helper.getStakeholders();
		dialog.dismiss();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_PHOTO && resultCode == RESULT_OK && data != null)
			dialog.setPhotoView(data);

		super.onActivityResult(requestCode, resultCode, data);
	}

	public void buildStakeholdersList(Bundle resultData) {
		String json = resultData.getString(JSON);
		ArrayList<Stakeholder> stakeholders = JsonParser.convertToList(Stakeholder.class, json);
		list.setAdapter(new StakeholderAdapter(this, stakeholders));
	}

	public void notifyStakeholderRemoved(Bundle resultData) {
		helper.getStakeholders();
	}

	public void notifyStakeholderAdded(Bundle resultData) {
		Toast.makeText(this, R.string.added, Toast.LENGTH_SHORT).show();
	}

	public void notifyStakeholderUpdated(Bundle resultData) {
		Toast.makeText(this, R.string.edited, Toast.LENGTH_SHORT).show();
	}

	public void showUpdateDialog(Stakeholder stakeholder) {
		dialog.show(stakeholder);
	}

}
