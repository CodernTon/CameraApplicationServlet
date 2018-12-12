package se.ingoproduction.example.application_v1;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraLayout extends Activity {
    Camera camera;
    FrameLayout frameLayout;
    OpenTheCamera openTheCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        frameLayout = (FrameLayout)findViewById(R.id.frameLayout);
        openCam();
    }

    public void openCam(){
        camera=camera.open(); // Open the camera
        openTheCamera = new OpenTheCamera(this, camera);
        frameLayout.addView(openTheCamera);
    }

    public void closeTheCamera(View view) {
        Intent closeTheCamera = new Intent(this, MainActivity.class);
        startActivity(closeTheCamera);
    }

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback(){
        @Override
        public void onPictureTaken(byte[] data,Camera camera){
            File picture_file = getOutputMediaFile();

            if(picture_file == null){
                return;
            }
            else{
                try {
                    FileOutputStream fos = new FileOutputStream(picture_file);
                    fos.write(data);
                    fos.close();
                    camera.startPreview();

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    };

    public void takeThePicture(View view) {
        if (camera!=null){
            camera.takePicture(null,null, mPictureCallback);
        }
    }

    private File getOutputMediaFile(){
        String state = Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            return null;
        }
        else{
            File folder_gui = new File(Environment.getExternalStorageDirectory()+File.separator+"BilderApp");

            if(!folder_gui.exists()){
                folder_gui.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd:HHmmss");
            String timestamp = sdf.format(new Date());

            File outPutFile = new File(folder_gui,timestamp+".jpg");
            return outPutFile;
        }
    }
}
