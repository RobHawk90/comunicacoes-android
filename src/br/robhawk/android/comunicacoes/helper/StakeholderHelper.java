package br.robhawk.android.comunicacoes.helper;

import static br.robhawk.android.comunicacoes.service.Links.ADD_OR_EDIT_STAKEHOLDER_LINK;
import static br.robhawk.android.comunicacoes.service.Links.GET_STAKEHOLDERS_LINK;
import static br.robhawk.android.comunicacoes.service.Links.REMOVE_STAKEHOLDERS_LINK;
import static br.robhawk.android.comunicacoes.service.Links.REMOVE_STAKEHOLDER_LINK;
import static br.robhawk.android.comunicacoes.service.StakeholderServices.ADD_OR_EDIT_STAKEHOLDER;
import static br.robhawk.android.comunicacoes.service.StakeholderServices.GET_STAKEHOLDERS;
import static br.robhawk.android.comunicacoes.service.StakeholderServices.REMOVE_STAKEHOLDER;
import static br.robhawk.android.comunicacoes.service.StakeholderServices.REMOVE_STAKEHOLDERS;
import static br.robhawk.android.comunicacoes.util.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.util.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.util.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.util.ResultCodes.STAKEHOLDERS_FOUND;
import static br.robhawk.android.comunicacoes.util.ResultCodes.STAKEHOLDER_ADDED;
import static br.robhawk.android.comunicacoes.util.ResultCodes.STAKEHOLDER_EDITED;
import static br.robhawk.android.comunicacoes.util.ResultCodes.STAKEHOLDER_REMOVED;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import br.robhawk.android.comunicacoes.activity.StakeholderActivity;
import br.robhawk.android.comunicacoes.dto.Stakeholder;
import br.robhawk.android.comunicacoes.service.StakeholderServices;
import br.robhawk.android.comunicacoes.util.ResultHandler;
import br.robhawk.android.comunicacoes.util.ResultHandler.Receiver;

public class StakeholderHelper implements Receiver {

	private StakeholderActivity activity;
	private ResultHandler receiver;

	public StakeholderHelper(StakeholderActivity activity) {
		this.activity = activity;

		receiver = new ResultHandler(this);
	}

	private Intent builder(String link) {
		return new Intent(activity, StakeholderServices.class).putExtra(LINK, link).putExtra(RECEIVER, receiver);
	}

	public void getStakeholders() {
		Intent findStakeholders = builder(GET_STAKEHOLDERS_LINK).setAction(GET_STAKEHOLDERS);
		activity.startService(findStakeholders);
	}

	public void addOrEdit(Stakeholder stakeholder) {
		Intent addStakeholder = builder(ADD_OR_EDIT_STAKEHOLDER_LINK).setAction(ADD_OR_EDIT_STAKEHOLDER).putExtra(DTO,
				stakeholder);
		activity.startService(addStakeholder);
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		switch (resultCode) {
		case STAKEHOLDER_ADDED:
			activity.notifyStakeholderAdded(resultData);
			break;

		case STAKEHOLDER_EDITED:
			activity.notifyStakeholderUpdated(resultData);
			break;

		case STAKEHOLDERS_FOUND:
			activity.buildStakeholdersList(resultData);
			break;

		case STAKEHOLDER_REMOVED:
			activity.notifyStakeholderRemoved(resultData);
			break;

		default:
			break;
		}
	}

	public void remove(Stakeholder stakeholder) {
		String link = String.format(REMOVE_STAKEHOLDER_LINK, stakeholder.getId());
		Intent removeStakeholder = builder(link).setAction(REMOVE_STAKEHOLDER);
		activity.startService(removeStakeholder);
	}

	/*public void addOrEdit(Endereco address) {
		Intent addOrEditAddress = builder(ADD_OR_EDIT_ADDRESS_LINK).setAction(ADD_OR_EDIT_ADDRESS).putExtra(DTO,
				address);
		activity.startService(addOrEditAddress);
	}*/

	public void remove(List<Stakeholder> stakeholders) {
		ArrayList<NameValuePair> ids = new ArrayList<NameValuePair>();
		for (Stakeholder stakeholder : stakeholders)
			ids.add(new BasicNameValuePair("stakeholders", String.valueOf(stakeholder.getId())));
		String params = URLEncodedUtils.format(ids, "UTF-8");

		Intent removeStakeholders = builder(REMOVE_STAKEHOLDERS_LINK + params).setAction(REMOVE_STAKEHOLDERS);
		activity.startService(removeStakeholders);
	}
}
