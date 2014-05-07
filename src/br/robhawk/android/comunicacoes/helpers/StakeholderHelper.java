package br.robhawk.android.comunicacoes.helpers;

import static br.robhawk.android.comunicacoes.services.Links.ADD_OR_EDIT_STAKEHOLDER_LINK;
import static br.robhawk.android.comunicacoes.services.Links.GET_STAKEHOLDERS_LINK;
import static br.robhawk.android.comunicacoes.services.Links.REMOVE_STAKEHOLDER_LINK;
import static br.robhawk.android.comunicacoes.services.StakeholderServices.ADD_OR_EDIT_STAKEHOLDER;
import static br.robhawk.android.comunicacoes.services.StakeholderServices.GET_STAKEHOLDERS;
import static br.robhawk.android.comunicacoes.services.StakeholderServices.REMOVE_STAKEHOLDER;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.STAKEHOLDERS_FOUND;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.STAKEHOLDER_ADDED;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.STAKEHOLDER_REMOVED;
import android.content.Intent;
import android.os.Bundle;
import br.robhawk.android.comunicacoes.activities.StakeholderActivity;
import br.robhawk.android.comunicacoes.dtos.Stakeholder;
import br.robhawk.android.comunicacoes.services.StakeholderServices;
import br.robhawk.android.comunicacoes.utils.ResultHandler;
import br.robhawk.android.comunicacoes.utils.ResultHandler.Receiver;

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
			// TODO notify stakeholder added
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
