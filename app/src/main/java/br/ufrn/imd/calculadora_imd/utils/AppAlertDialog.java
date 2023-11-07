package br.ufrn.imd.calculadora_imd.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AppAlertDialog {
    public static void showAlertDialog(String title, String text, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(title).setMessage(text);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
