package br.robhawk.android.comunicacoes.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import br.robhawk.android.comunicacoes.R;
import br.robhawk.android.comunicacoes.dtos.Stakeholder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

public class ImageEditor {

	private ImageEditor() {
	}

	public static Bitmap buildPhoto(Intent choosen) {
		// remove the file:/// pattern from path
		String path = choosen.getDataString().substring(8);
		Drawable photo = Drawable.createFromPath(path);
		Bitmap photoFinal = ((BitmapDrawable) photo).getBitmap();
		photoFinal = Bitmap.createScaledBitmap(photoFinal, 100, 100, false);

		return photoFinal;
	}

	public static String encode(Bitmap photo) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		photo.compress(CompressFormat.JPEG, 50, out);
		byte[] byteArray = out.toByteArray();
		return Base64.encodeToString(byteArray, Base64.NO_WRAP);
	}

	public static Bitmap decodeFrom(Stakeholder stakeholder, Context context, int size) {
		String photo = stakeholder.getFoto();

		if (photo == null || photo.isEmpty()) {
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_photo);
			return Bitmap.createScaledBitmap(bitmap, size, size, false);
		}

		ByteArrayInputStream in = new ByteArrayInputStream(Base64.decode(photo, Base64.NO_WRAP));
		Bitmap decodedPhoto = BitmapFactory.decodeStream(in);
		decodedPhoto = Bitmap.createScaledBitmap(decodedPhoto, size, size, false);

		return decodedPhoto;
	}
}
