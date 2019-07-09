package com.example.gitbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton mToggle = null;
    private Switch mSwitch = null;
    private EditText mEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToggle = (ToggleButton) findViewById(R.id.toggleButton);
        mSwitch = (Switch) findViewById(R.id.switch1);
        mEditText = (EditText) findViewById(R.id.editText);
        mToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(MainActivity.this, "Toggle On!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Toggle Off!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(MainActivity.this, "Switch On!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Switch Off!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (mEditText != null) {
            mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    boolean mHandled = false;
                    if (i == EditorInfo.IME_ACTION_SEND) {
                        dialNumber();
                        mHandled = true;
                    }
                    return mHandled;
                }
            });
        }
    }

    private void dialNumber() {
        String phoneNumber = mEditText.getText().toString();
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Integer.valueOf(phoneNumber)));
        if (phoneIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(phoneIntent);
        }
    }
}