package widget;

import br.robhawk.android.comunicacoes.R;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

public class EditText extends android.widget.EditText {

	public EditText(Context context) {
		super(context);

		buildLayout();
	}

	public EditText(Context context, AttributeSet attrs) {
		super(context, attrs);

		buildLayout();
	}

	private void buildLayout() {
		setBackgroundResource(R.drawable.selector_edit_text);
		setTextColor(Color.BLACK);
	}

}
