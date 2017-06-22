package travelapp.tamasya.view.startup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import travelapp.tamasya.R;

public class AfterSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_splash);
    }

    public void doLogin(View view) {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void doRegistration(View view) {

        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
