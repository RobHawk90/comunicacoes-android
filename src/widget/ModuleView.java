package widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import br.robhawk.android.comunicacoes.R;

public class ModuleView extends TextView {

	public ModuleView(Context context) {
		super(context);

		buildLayout();
	}

	public ModuleView(Context context, AttributeSet attrs) {
		super(context, attrs);

		buildLayout();
	}

	private void buildLayout() {
		setGravity(Gravity.CENTER_HORIZONTAL);
		setClickable(true);
		setTextColor(Color.WHITE);
		setTypeface(getTypeface(), Typeface.BOLD);
		setBackgroundResource(R.drawable.selector_module_view);
	}
}
