package br.robhawk.android.comunicacoes.helper;

import static br.robhawk.android.comunicacoes.service.Links.ADD_OR_EDIT_STAKEHOLDER_LINK;
import static br.robhawk.android.comunicacoes.service.Links.GET_STAKEHOLDERS_LINK;
import static br.robhawk.android.comunicacoes.service.Links.REMOVE_STAKEHOLDER_LINK;
import static br.robhawk.android.comunicacoes.service.StakeholderServices.ADD_OR_EDIT_STAKEHOLDER;
import static br.robhawk.android.comunicacoes.service.StakeholderServices.GET_STAKEHOLDERS;
import static br.robhawk.android.comunicacoes.service.StakeholderServices.REMOVE_STAKEHOLDER;
import static br.robhawk.android.comunicacoes.util.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.util.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.util.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.util.ResultCodes.STAKEHOLDERS_FOUND;
import static br.robhawk.android.comunicacoes.util.ResultCodes.STAKEHOLDER_ADDED;
import static br.robhawk.android.comunicacoes.util.ResultCodes.STAKEHOLDER_EDITED;
import static br.robhawk.android.comunicacoes.util.ResultCodes.STAKEHOLDER_REMOVED;
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

	public void getStakeholders() {
		Intent findStakeholders = new Intent(activity, StakeholderServices.class).setAction(GET_STAKEHOLDERS)
				.putExtra(RECEIVER, receiver).putExtra(LINK, GET_STAKEHOLDERS_LINK);
		activity.startService(findStakeholders);
	}

	public void addOrEdit(Stakeholder stakeholder) {
		Intent addStakeholder = new Intent(activity, StakeholderServices.class).setAction(ADD_OR_EDIT_STAKEHOLDER)
				.putExtra(RECEIVER, receiver).putExtra(DTO, stakeholder).putExtra(LINK, ADD_OR_EDIT_STAKEHOLDER_LINK);
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
		Intent removeStakeholder = new Intent(activity, StakeholderServices.class).setAction(REMOVE_STAKEHOLDER)
				.putExtra(LINK, link).putExtra(RECEIVER, receiver);
		activity.startService(removeStakeholder);
	}
}
