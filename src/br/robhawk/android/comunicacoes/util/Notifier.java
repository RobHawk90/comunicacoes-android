package br.robhawk.android.comunicacoes.util;

import android.app.AlertDialog;
import android.content.Context;

public class Notifier {

	private Notifier() {
	}

	public static void showMessage(Context context, int msgRes) {
		new AlertDialog.Builder(context).setMessage(msgRes).setNeutralButton("Ok", null).create().show();
	}

}
