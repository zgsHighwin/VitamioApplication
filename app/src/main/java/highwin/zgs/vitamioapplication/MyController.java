package highwin.zgs.vitamioapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import io.vov.vitamio.widget.MediaController;

public class MyController extends MediaController {
    public MyController(Context context) {
        super(context);
    }

    public MyController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View makeControllerView() {
        View view = super.makeControllerView();

        return view;
    }
}
