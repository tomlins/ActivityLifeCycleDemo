package net.tomlins.demo.lifecycle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
/**
 * Created by jason on 18/11/13.
 */
public class MyAlertDialog extends DialogFragment {

    static MyAlertDialog newInstance(String message) {

        MyAlertDialog f = new MyAlertDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("message", message);
        f.setArguments(args);

        return f;
    }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            String message = getArguments().getString("message");
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(message)
                    .setPositiveButton(R.string.dialog_close, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }


}
