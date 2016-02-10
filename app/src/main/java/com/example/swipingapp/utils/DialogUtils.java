package com.example.swipingapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.swipingapp.R;

public class DialogUtils {

    // region Public functions

    public static void displayMessageDialog(Context context, String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(R.string.dialog_btn_cancel, new CancelButtonClickListener());

        AlertDialog dialog = alertDialogBuilder.create();

        // show it
        dialog.show();
    }

    // endregion

    // region Listeners

    private static class CancelButtonClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
        }
    }

    // endregion
}
