package se.ingoproduction.example.application_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openTheCamera(View view) {
        Intent openTheCameraIntent = new Intent
                (this, CameraLayout.class);
        startActivity(openTheCameraIntent);
    }

    public void getDataFromPic(View view) {
        Intent getInfoFromPicIntent= new Intent
                (this, ScanThePic.class);
        startActivity(getInfoFromPicIntent);
    }



/*
    public void getInfoFromPic(View view) {
        Intent getInfoFromThePic= new Intent
                (this, ScanThePic.class);
        startActivity(getInfoFromThePic);
    }

    public void startTheSearch(View view) {
        Intent startSearch = new Intent
                (this, SearchActivity.class);
        startActivity(startSearch);
    }*/
}
