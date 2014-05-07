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
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.adapters.StakeholderAdapter;
import br.robhawk.android.comunicacoes.dialogs.AddOrEditStakeholderDialog;
import br.robhawk.android.comunicacoes.dtos.Stakeholder;
import br.robhawk.android.comunicacoes.helpers.StakeholderHelper;
import br.robhawk.android.comunicacoes.json.JsonParser;

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

		registerForContextMenu(list);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.context_stakeholder, menu);

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem addStakeholder = menu.add(R.string.addStakeholder);
		addStakeholder.setIcon(R.drawable.ic_add_stakeholder);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		dialog.show();

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

		if (!stakeholders.isEmpty())
			list.setAdapter(new StakeholderAdapter(this, stakeholders));
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		Stakeholder stakeholder = (Stakeholder) list.getItemAtPosition(info.position);

		switch (item.getItemId()) {
		case R.id.removeStakeholder:
			helper.remove(stakeholder);
			break;

		case R.id.editStakeholder:
			dialog.show(stakeholder);
			break;

		case R.id.grantPermissions:

			break;

		default:
			break;
		}

		return super.onContextItemSelected(item);
	}

	public void notifyStakeholderRemoved(Bundle resultData) {
		helper.getStakeholders();
	}

}
