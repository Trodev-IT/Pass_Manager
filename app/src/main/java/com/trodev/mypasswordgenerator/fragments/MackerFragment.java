package com.trodev.mypasswordgenerator.fragments;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.trodev.mypasswordgenerator.R;
import com.trodev.mypasswordgenerator.activity.LoginActivity;
import com.trodev.mypasswordgenerator.models.database.DatabaseHelper;
import com.trodev.mypasswordgenerator.models.database.SavePassword;
import com.trodev.mypasswordgenerator.models.generators.LowerCaseGenerator;
import com.trodev.mypasswordgenerator.models.generators.NumericGenerator;
import com.trodev.mypasswordgenerator.models.generators.PasswordGenerator;
import com.trodev.mypasswordgenerator.models.generators.SpecialCharGenerator;
import com.trodev.mypasswordgenerator.models.generators.UpperCaseGenerator;
import com.trodev.mypasswordgenerator.models.password.Password;

import java.util.List;

public class MackerFragment extends Fragment {

    private EditText editPasswordSize;
    private TextView textPasswordGenerated, textErrorMessage;
    private CheckBox checkLower, checkUpper, checkSpecialChar, checkNumeric;
    private Button btnGenerate, btnCopy, btnSave, btn_logout;

    public MackerFragment() {
        // Required empty public constructor
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
        btnSave = view.findViewById(R.id.btn_save);
        btn_logout = view.findViewById(R.id.btn_logout);

        btnSave.setEnabled(false);

        clickListeners();

        displaySavedPasswords();

        return view;

    }

    private void displaySavedPasswords() {
        DatabaseHelper db = new DatabaseHelper(getActivity());
        List<Password> passwordList = db.getPasswordList();
        Log.e("PWD_LIST", passwordList.toString());
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
                btnSave.setEnabled(true);
            }
        });

        btnCopy.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setPrimaryClip(ClipData.newPlainText("password", textPasswordGenerated.getText().toString()));
            Toast.makeText(getContext(), "Password Copied", Toast.LENGTH_SHORT).show();
        });

        btnSave.setOnClickListener(view -> {
            String genPwd = textPasswordGenerated.getText().toString();
            Intent intent = new Intent(getContext(), SavePassword.class);
            intent.putExtra("pwd", genPwd);
            startActivity(intent);
        });

    }
}