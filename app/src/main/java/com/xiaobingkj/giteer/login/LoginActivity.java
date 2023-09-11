package com.xiaobingkj.giteer.login;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.xiaobingkj.giteer.base.BaseActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;

import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginPersonalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(LoginActivity.this)
                        .setMessage(R.string.login_personal_title)
                        .setView(R.layout.edit_text)
                        .setPositiveButton(R.string.dialog_login_accept, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TextView input = ((AlertDialog) dialogInterface).findViewById(android.R.id.text1);
                                Log.d("LoginActivity", input.toString());
                            }
                        })
                        .setNegativeButton(R.string.dialog_common_cancel, null)
                        .show();
            }
        });

        binding.loginLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, WebActivity.class);
                startActivity(intent);
            }
        });
    }
}