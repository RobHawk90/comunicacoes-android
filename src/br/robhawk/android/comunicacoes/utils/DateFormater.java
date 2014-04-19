package br.robhawk.android.comunicacoes.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormater {

	private DateFormater() {
	}

	public static final Locale local = new Locale("pt, BR");
	public static final SimpleDateFormat formatoServidor = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", local);
	public static final SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy", local);
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd", local);
	public static final SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm", local);
	public static final SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss", local);

	public static final String servidor(Date dataHora) {
		return formatoServidor.format(dataHora);
	}

	public static final Date servidor(String dataHoraString) {
		Date dataHora = null;

		try {
			dataHora = formatoServidor.parse(dataHoraString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dataHora;
	}

	public static final String ddMMyyyy(Date data) {
		return ddMMyyyy.format(data);
	}

	public static final Date ddMMyyyy(String dataString) {
		Date data = null;

		try {
			data = ddMMyyyy.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static final String yyyyMMdd(Date data) {
		return yyyyMMdd.format(data);
	}

	public static final Date yyyyMMdd(String dataString) {
		Date data = null;

		try {
			data = yyyyMMdd.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static final String HHmm(Date hora) {
		return HHmm.format(hora);
	}

	public static final Date HHmm(String horaString) {
		Date hora = null;

		try {
			hora = HHmm.parse(horaString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return hora;
	}

	public static final String HHmmss(Date hora) {
		return HHmmss.format(hora);
	}

	public static final Date HHmmss(String horaString) {
		Date hora = null;

		try {
			hora = HHmmss.parse(horaString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return hora;
	}
}
