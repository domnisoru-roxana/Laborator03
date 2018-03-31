package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialerActivity extends AppCompatActivity {

    EditText phoneNumberEditText;

    class NumberButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            phoneNumberEditText.setText(phoneNumberEditText.getText().toString() +
                    ((Button)view).getText().toString());
        }
    }

    class BackSpaceButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String phoneNumber = phoneNumberEditText.getText().toString();
            if (phoneNumber.length() > 0) {
                phoneNumberEditText.setText(phoneNumber.substring(0, phoneNumber.length() - 1));
            }
        }
    }

    class DialButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        PhoneDialerActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        Constants.PERMISSION_REQUEST_CALL_PHONE);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                startActivity(intent);
            }
        }
    }

    class ExitButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
     //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        NumberButtonClickListener numberButtonClickListener = new NumberButtonClickListener();
        phoneNumberEditText = (EditText)findViewById(R.id.phone_number_edit_text);
        ((Button)findViewById(R.id.number_1_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.number_2_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.number_3_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.number_4_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.number_5_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.number_6_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.number_7_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.number_8_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.number_9_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.number_0_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.star_button)).setOnClickListener(numberButtonClickListener);
        ((Button)findViewById(R.id.hash_button)).setOnClickListener(numberButtonClickListener);

        ((ImageButton)findViewById(R.id.backpace_image_button))
                .setOnClickListener(new BackSpaceButtonClickListener());

        ((ImageButton)findViewById(R.id.call_image_button))
                .setOnClickListener(new DialButtonClickListener());

        ((ImageButton)findViewById(R.id.hang_up_image_button))
                .setOnClickListener(new ExitButtonClickListener());
    }
}
