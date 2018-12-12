package se.ingoproduction.example.application_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ScanThePic extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_thepic);
    }

    public void goBackToMain(View view) {
        Intent goBackToMainIntent = new Intent
                (this, MainActivity.class);
        startActivity(goBackToMainIntent);

    }
}
