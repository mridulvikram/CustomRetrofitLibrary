package com.mridul.customretrofitlibrary.Network;

import android.app.Dialog;
import android.content.Context;

import com.mridul.customretrofitlibrary.R;

/**
 * Created by mridul on 08/08/19.
 */

public class Progress extends Dialog {

    Progress(Context context) {
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


