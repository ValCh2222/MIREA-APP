package com.mirea.chubuka_v_a.mireaapp_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mirea.chubuka_v_a.mireaapp_android.databinding.ActivityFireBase2Binding;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class FireBaseActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView statusTextView;
    private EditText emailEditText;
    private EditText passwordEditText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);

        statusTextView = findViewById(R.id.statusTextView);
        emailEditText = findViewById(R.id.idVklEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        findViewById(R.id.signInBtn).setOnClickListener(this);
        findViewById(R.id.createAccountBtn).setOnClickListener(this);
        findViewById(R.id.signOutBtn).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user){
        if (user != null){
            String status = getString(R.string.emailpassword_status_fmt, user.getEmail(), user.isEmailVerified());
            statusTextView.setText(status);

            findViewById(R.id.idVklEditText).setVisibility(View.GONE);
            findViewById(R.id.passwordEditText).setVisibility(View.GONE);
            findViewById(R.id.signInBtn).setVisibility(View.GONE);
            findViewById(R.id.createAccountBtn).setVisibility(View.GONE);
            findViewById(R.id.signOutBtn).setVisibility(View.GONE);

            Intent intent = new Intent(FireBaseActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            String status = getString(R.string.signed_out);
            statusTextView.setText(status);

            findViewById(R.id.idVklEditText).setVisibility(View.VISIBLE);
            findViewById(R.id.passwordEditText).setVisibility(View.VISIBLE);
            findViewById(R.id.signInBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.createAccountBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.signOutBtn).setVisibility(View.VISIBLE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Required.");
            valid = false;
        } else {
            emailEditText.setError(null);
        }
        String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Required.");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }
        return valid;
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(FireBaseActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(FireBaseActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        if (!task.isSuccessful()) {
                            statusTextView.setText(R.string.auth_failed);
                        }
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.createAccountBtn) {
            createAccount(emailEditText.getText().toString(), passwordEditText.getText().toString());
        } else if (i == R.id.signInBtn) {
            signIn(emailEditText.getText().toString(), passwordEditText.getText().toString());
        } else if (i == R.id.signOutBtn) {
            signOut();
        }
    }
}