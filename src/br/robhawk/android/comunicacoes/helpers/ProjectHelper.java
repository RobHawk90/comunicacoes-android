package br.robhawk.android.comunicacoes.helpers;

import static br.robhawk.android.comunicacoes.services.Links.REMOVE_PROJECT_LINK;
import static br.robhawk.android.comunicacoes.services.ProjectServices.ADD_OR_EDIT_PROJECT;
import static br.robhawk.android.comunicacoes.services.ProjectServices.GET_PROJECTS;
import static br.robhawk.android.comunicacoes.services.ProjectServices.REMOVE_PROJECT;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.utils.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.PROJECTS_FOUND;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.PROJECT_ADDED;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.PROJECT_REMOVED;
import static br.robhawk.android.comunicacoes.utils.ResultCodes.STAKEHOLDERS_ATTACHED;
import android.content.Intent;
import android.os.Bundle;
import br.robhawk.android.comunicacoes.activities.ProjectActivity;
import br.robhawk.android.comunicacoes.dtos.Projeto;
import br.robhawk.android.comunicacoes.services.ProjectServices;
import br.robhawk.android.comunicacoes.utils.ResultHandler;
import br.robhawk.android.comunicacoes.utils.ResultHandler.Receiver;

public class ProjectHelper implements Receiver {

	private ResultHandler receiver;
	private ProjectActivity activity;

	public ProjectHelper(ProjectActivity activity) {
		this.activity = activity;

		receiver = new ResultHandler(this);
	}

	public void addOrEdit(Projeto project) {
		Intent addOrEditProject = new Intent(activity, ProjectServices.class).setAction(ADD_OR_EDIT_PROJECT)
				.putExtra(RECEIVER, receiver).putExtra(DTO, project);
		activity.startService(addOrEditProject);
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		switch (resultCode) {
		case PROJECT_ADDED:
			activity.notifyProjectAdded();
			break;

		case PROJECTS_FOUND:
			activity.buildProjectList(resultData);
			break;

		case PROJECT_REMOVED:
			activity.notifyProjectRemoved();
			break;

		case STAKEHOLDERS_ATTACHED:
			activity.notifyStakeholdersAttached();
			break;

		default:
			break;
		}
	}

	public void getProjects() {
		Intent getProjects = new Intent(activity, ProjectServices.class).setAction(GET_PROJECTS).putExtra(RECEIVER,
				receiver);
		activity.startService(getProjects);
	}

	public void remove(Projeto project) {
		String link = String.format(REMOVE_PROJECT_LINK, project.getId());
		Intent removeProject = new Intent(activity, ProjectServices.class).setAction(REMOVE_PROJECT)
				.putExtra(RECEIVER, receiver).putExtra(LINK, link);
		activity.startService(removeProject);
	}

}
