package com.mridul.retrofitdemoapplication.Network;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.mridul.retrofitdemoapplication.R;

/**
 * Created by mridul on 08/08/19.
 */

public class Progress extends Dialog {

    public Progress(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        setContentView(R.layout.progress_layout);
        super.setCancelable(false);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}


