package com.example.browseimages.utils;

import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.browseimages.R;

public class DialogUtil {

    public static void showOrHideKeyboard(Context ctx, EditText editText, boolean show) {
        final InputMethodManager imm = (InputMethodManager) ctx.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        if (show) {
            imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        } else {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
        editText.setFocusable(true);
    }

    public static void showNoNetworkAlert(Context ctx) {
        try {
            new android.app.AlertDialog.Builder(ctx).setTitle(R.string.app_name).setMessage(R.string.no_internet)
                    .setPositiveButton(R.string.ok, null).create().show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }
}
