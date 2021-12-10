package com.example.worldrealestate.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.worldrealestate.R;

public class LogInActivity extends AppCompatActivity {

    private TextView registerTextView;
    private Button logInBtn;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView dontRememberPasswordNextView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setViews();
        setListeners();
    }

    private void setViews() {
        registerTextView = findViewById(R.id.activity_log_in_register_text_view);
        logInBtn = findViewById(R.id.activity_log_in_register_button);
        emailEditText = findViewById(R.id.activity_log_in_email_edit_text);
        passwordEditText = findViewById(R.id.activity_log_in_password_edit_text);
    }

    private void setListeners() {
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this
                        , RegisterActivity.class);
                startActivity(intent);
            }
        });

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                logInUser(email, password);
            }
        });
    }

    private void logInUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(LogInActivity.this
                                    , "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Intent intent = new Intent(LogInActivity.this, NavigationActivity.class);
            startActivity(intent);
        }
    }


}
