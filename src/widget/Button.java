package widget;

import br.robhawk.android.comunicacoes.R;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

public class Button extends android.widget.Button {

	public Button(Context context) {
		super(context);

		buildLayout();
	}

	public Button(Context context, AttributeSet attrs) {
		super(context, attrs);

		buildLayout();
	}

	private void buildLayout() {
		setBackgroundResource(R.drawable.selector_button);
		setTextColor(Color.WHITE);
	}

}
