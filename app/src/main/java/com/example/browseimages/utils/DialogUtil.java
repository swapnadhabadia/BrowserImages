package com.example.browseimages.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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
}
