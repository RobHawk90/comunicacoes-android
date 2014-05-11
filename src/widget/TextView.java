package widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import br.robhawk.android.comunicacoes.R;

public class TextView extends android.widget.TextView {

	public TextView(Context context) {
		super(context);

		setTextSize(TypedValue.DENSITY_DEFAULT, getResources().getDimension(R.dimen.text_medium));
		setTextColor(Color.BLACK);
	}

	public TextView(Context context, AttributeSet attrs) {
		super(context, attrs);

		String size = attrs.getAttributeValue("http://schemas.android.com/apk/res/android", "textSize");

		if (size == null)
			setTextSize(TypedValue.DENSITY_DEFAULT, getResources().getDimension(R.dimen.text_medium));

		setTextColor(Color.BLACK);
	}

}
