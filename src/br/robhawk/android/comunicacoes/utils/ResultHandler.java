package br.robhawk.android.comunicacoes.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class ResultHandler extends ResultReceiver {

	private Receiver receiver;

	public interface Receiver {
		void onReceiveResult(int resultCode, Bundle resultData);
	}

	public ResultHandler(Receiver receiver) {
		super(new Handler());
		this.receiver = receiver;
	}

	@Override
	protected void onReceiveResult(int resultCode, Bundle resultData) {
		receiver.onReceiveResult(resultCode, resultData);
	}
}
