package br.robhawk.android.comunicacoes.adapters;

import java.util.List;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.activities.StakeholderActivity;
import br.robhawk.android.comunicacoes.dtos.Stakeholder;
import br.robhawk.android.comunicacoes.utils.ImageEditor;

public class StakeholderAdapter extends ArrayAdapter<Stakeholder> {

	private LayoutInflater inflater;

	public StakeholderAdapter(StakeholderActivity context, List<Stakeholder> stakeholders) {
		super(context, R.layout.adapter_stakeholder, stakeholders);

		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Stakeholder stakeholder = getItem(position);
		Bitmap photo = ImageEditor.decodeFrom(stakeholder, getContext(), 50);

		View layout = inflater.inflate(R.layout.adapter_stakeholder, null);

		ImageView imgPhoto = (ImageView) layout.findViewById(R.id.imgPhoto);
		TextView txtName = (TextView) layout.findViewById(R.id.txtName);
		TextView txtEmail = (TextView) layout.findViewById(R.id.txtEmail);

		imgPhoto.setImageBitmap(photo);
		txtName.setText(stakeholder.getNome());
		txtEmail.setText(stakeholder.getEmail());

		return layout;
	}

}
