package views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.nffs.performax.R;

import net.qiujuer.genius.widget.GeniusEditText;

/**
 * Created by ammon on 2/19/15.
 */
public class EmailDialog extends DialogFragment {

    Button cancel,submit;
    TextView title;
    GeniusEditText first_name, last_name, phone_number, email, comments;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.custom_dialog, null);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(rootView);

        first_name = (GeniusEditText) dialog.findViewById(R.id.first_name);
        last_name = (GeniusEditText) dialog.findViewById(R.id.last_name);
        phone_number = (GeniusEditText) dialog.findViewById(R.id.phone_number);
        email = (GeniusEditText) dialog.findViewById(R.id.email);
        comments = (GeniusEditText) dialog.findViewById(R.id.comments);





        title = (TextView) dialog.findViewById(R.id.title_view);



        submit = (Button)dialog.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Biophase Inquiry");
                i.putExtra(Intent.EXTRA_TEXT   , "Inquiry for Biophase from:" + first_name.getText().toString() + "" + last_name.getText().toString() + "\n" + phone_number.getText().toString() + "\n" + email.getText().toString() + "\n\n" + comments.getText().toString());
                try {
                    startActivity(Intent.createChooser(i, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }

        });

        cancel = (Button)dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                dismiss();

            }
        });



        // Do something else
        return dialog;
    }










}