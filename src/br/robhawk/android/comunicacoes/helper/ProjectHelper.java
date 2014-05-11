package br.robhawk.android.comunicacoes.helper;

import static br.robhawk.android.comunicacoes.service.Links.REMOVE_PROJECT_LINK;
import static br.robhawk.android.comunicacoes.service.ProjectServices.ADD_OR_EDIT_PROJECT;
import static br.robhawk.android.comunicacoes.service.ProjectServices.GET_PROJECTS;
import static br.robhawk.android.comunicacoes.service.ProjectServices.REMOVE_PROJECT;
import static br.robhawk.android.comunicacoes.util.IntentExtras.DTO;
import static br.robhawk.android.comunicacoes.util.IntentExtras.LINK;
import static br.robhawk.android.comunicacoes.util.IntentExtras.RECEIVER;
import static br.robhawk.android.comunicacoes.util.ResultCodes.PROJECTS_FOUND;
import static br.robhawk.android.comunicacoes.util.ResultCodes.PROJECT_ADDED;
import static br.robhawk.android.comunicacoes.util.ResultCodes.PROJECT_REMOVED;
import static br.robhawk.android.comunicacoes.util.ResultCodes.STAKEHOLDERS_ATTACHED;
import android.content.Intent;
import android.os.Bundle;
import br.robhawk.android.comunicacoes.activity.ProjectActivity;
import br.robhawk.android.comunicacoes.dto.Projeto;
import br.robhawk.android.comunicacoes.service.ProjectServices;
import br.robhawk.android.comunicacoes.util.ResultHandler;
import br.robhawk.android.comunicacoes.util.ResultHandler.Receiver;

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
