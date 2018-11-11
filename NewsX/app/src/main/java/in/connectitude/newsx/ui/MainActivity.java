package in.connectitude.newsx.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.connectitude.newsx.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.registerFirebase)
    Button register;

    @BindView(R.id.username_txt)
    AppCompatEditText userNameEditText;
    @BindView(R.id.password_txt)
    AppCompatEditText passwordEditText;
    @BindView(R.id.alreadyRegistered)
    TextView alreadyRegistered;

    @BindView(R.id.register_ProgressBar)
    ProgressBar registerProgressbar;


    private FirebaseAuth auth;


    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        registerProgressbar.setVisibility(View.GONE);


        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, NewsMainActivity.class));
            finish();
        }


        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });


        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(MainActivity.this,NewsMainActivity.class);
                //startActivity(intent);
                registerProgressbar.setVisibility(View.VISIBLE);
                registerUser();


            }
        });


    }

    public void registerUser(){
        String email = userNameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), R.string.toast_enter_email_address, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), R.string.toast_enter_password, Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), R.string.toast_password_short, Toast.LENGTH_SHORT).show();
            return;
        }

        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this, getString(R.string.toast_create_user) + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                       // progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            registerProgressbar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, getString(R.string.toast_auth_failed) + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            registerProgressbar.setVisibility(View.VISIBLE);
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });


    }
}
