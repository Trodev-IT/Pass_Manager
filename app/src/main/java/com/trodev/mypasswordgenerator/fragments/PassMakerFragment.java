package com.trodev.mypasswordgenerator.fragments;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.password_generate_models.generators.LowerCaseGenerator;
import com.trodev.mypasswordgenerator.password_generate_models.generators.NumericGenerator;
import com.trodev.mypasswordgenerator.password_generate_models.generators.PasswordGenerator;
import com.trodev.mypasswordgenerator.password_generate_models.generators.SpecialCharGenerator;
import com.trodev.mypasswordgenerator.password_generate_models.generators.UpperCaseGenerator;

public class PassMakerFragment extends Fragment {

    private EditText editPasswordSize;
    private TextView textPasswordGenerated, textErrorMessage;
    private CheckBox checkLower, checkUpper, checkSpecialChar, checkNumeric;
    private Button btnGenerate, btnCopy;

    public PassMakerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_macker, container, false);

        editPasswordSize = view.findViewById(R.id.edit_pwd_size);
        textPasswordGenerated = view.findViewById(R.id.text_password_result);
        textErrorMessage = view.findViewById(R.id.text_error);
        checkLower = view.findViewById(R.id.check_lower);
        checkUpper = view.findViewById(R.id.check_upper);
        checkSpecialChar = view.findViewById(R.id.check_special_char);
        checkNumeric = view.findViewById(R.id.check_numeric);
        btnGenerate = view.findViewById(R.id.btn_generate);
        btnCopy = view.findViewById(R.id.btn_copy);
        clickListeners();
        return view;
    }

    @SuppressLint("SetTextI18n")
    private void clickListeners() {
        btnGenerate.setOnClickListener(view -> {

            int passwordSize = Integer.parseInt(editPasswordSize.getText().toString());

            textErrorMessage.setText("");

            if (passwordSize < 6 || passwordSize >= 20) {
                textErrorMessage.setText("password size must be greater than 6 and less than 20 characters");
            } else {

                PasswordGenerator.clear();
                if (checkLower.isChecked()) PasswordGenerator.add(new LowerCaseGenerator());
                if (checkNumeric.isChecked()) PasswordGenerator.add(new NumericGenerator());
                if (checkUpper.isChecked()) PasswordGenerator.add(new UpperCaseGenerator());
                if (checkSpecialChar.isChecked()) PasswordGenerator.add(new SpecialCharGenerator());

                if (PasswordGenerator.isEmpty()) {
                    textErrorMessage.setText("Please select at least one password content type");
                    return;
                }
                String password = PasswordGenerator.generatePassword(passwordSize);
                textPasswordGenerated.setText(password);
            }
        });

        btnCopy.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setPrimaryClip(ClipData.newPlainText("password", textPasswordGenerated.getText().toString()));
            Toast.makeText(getContext(), "Copy Successful", Toast.LENGTH_SHORT).show();
        });

    }
}