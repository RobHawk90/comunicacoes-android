package br.robhawk.android.comunicacoes.adapter;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.activity.StakeholderActivity;
import br.robhawk.android.comunicacoes.dto.Stakeholder;
import br.robhawk.android.comunicacoes.util.ImageEditor;

public class StakeholderAdapter extends ArrayAdapter<Stakeholder> {

	private StakeholderActivity context;
	private LayoutInflater inflater;
	private List<Stakeholder> selecteds;

	public StakeholderAdapter(StakeholderActivity context, List<Stakeholder> stakeholders) {
		super(context, R.layout.adapter_stakeholder, stakeholders);
		this.context = context;

		selecteds = new ArrayList<Stakeholder>();
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Stakeholder stakeholder = getItem(position);
		Log.e("stake", stakeholder.toString());
		Bitmap photo = ImageEditor.decodeFrom(stakeholder, getContext(), 50);

		View layout = inflater.inflate(R.layout.adapter_stakeholder, null);

		ImageView imgPhoto = (ImageView) layout.findViewById(R.id.imgPhoto);
		TextView txtName = (TextView) layout.findViewById(R.id.txtName);
		TextView txtEmail = (TextView) layout.findViewById(R.id.txtEmail);
		CheckBox ckbStakeholder = (CheckBox) layout.findViewById(R.id.ckbStakeholder);

		imgPhoto.setImageBitmap(photo);
		txtName.setText(stakeholder.getNome());
		txtEmail.setText(stakeholder.getEmail());
		ckbStakeholder.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)
					selecteds.add(stakeholder);
				else
					selecteds.remove(stakeholder);
			}
		});

		layout.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				context.showUpdateDialog(stakeholder);

				return false;
			}
		});

		return layout;
	}

	public List<Stakeholder> getSelectedStakeholders() {
		return selecteds;
	}

}
