package com.himanshu.creditcard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText cardNumber, mMYY, cCv, fName, lName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardNumber = findViewById(R.id.card_number_editText);
        mMYY = findViewById(R.id.mm_yy_editText);
        cCv = findViewById(R.id.ccv_editText);
        fName = findViewById(R.id.fName_editText);
        lName = findViewById(R.id.lName_editText);

        findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment();
            }
        });

        mMYY.addTextChangedListener(new TextWatcher() {
            int first =0;
            int second;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
               second = first;
               first = s.length();
               if(mMYY.length() == 2 && first > second){
                   mMYY.append("/");
               }
            }
        });

    }

    private void submitPayment() {

        TextInputLayout textInputLayout;

        if (TextUtils.isEmpty(cardNumber.getText().toString().trim()) || cardNumber.getText().toString().trim().length() < 16) {
            textInputLayout = findViewById(R.id.outlinedTextCardNumber);
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Invalid card number");
        } else if (TextUtils.isEmpty(mMYY.getText().toString().trim()) || mMYY.getText().toString().trim().length() < 5) {
            textInputLayout = findViewById(R.id.outlinedTextMmYy);
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Invalid MM/YY");
        } else if (TextUtils.isEmpty(cCv.getText().toString().trim()) || cCv.getText().toString().trim().length() < 3) {
            textInputLayout = findViewById(R.id.outlinedTextCcv);
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Invalid ccv number");
        } else if (TextUtils.isEmpty(fName.getText().toString().trim())) {
            textInputLayout = findViewById(R.id.outlinedTextFName);
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Invalid name");
        } else if (TextUtils.isEmpty(lName.getText().toString().trim())) {
            textInputLayout = findViewById(R.id.outlinedTextLName);
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Invalid name");
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Payment Successful")
                    .setCancelable(false)
                    .setNegativeButton("Ok", null)
                    .create()
                    .show();
        }
    }
}