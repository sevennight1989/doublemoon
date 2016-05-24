package lk.meterialdesign;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Outline;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView mImg;
    Button mRecycleBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        testNotification();

    }

    private void initViews(){
        mImg = (ImageView) findViewById(R.id.img);
        mRecycleBt = (Button) findViewById(R.id.recycleView);
        mRecycleBt.setOnClickListener(this);
    }

    private void testNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Material");
        builder.setContentText("New Message");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        builder.setContentIntent(pendingIntent);
        Notification nf = builder.build();
        NotificationManager nfm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nfm.notify(1, nf);
    }

    private void testCamear() {
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            String[] camears = cameraManager.getCameraIdList();
            for (String camear : camears) {
                Log.d("meterlog", camear);
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void testOutLine() {
        ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int margin = Math.min(mImg.getWidth(), mImg.getHeight()) / 10;
                outline.setRoundRect(margin, margin, mImg.getWidth()
                - margin, mImg.getHeight() - margin, margin / 2);
            }
        };
        mImg.setOutlineProvider(viewOutlineProvider);
        mImg.setClipToOutline(true);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recycleView:
                startActivity(makeRecycleViewIntent());
                break;

            default:
                break;
        }
    }

    private Intent makeRecycleViewIntent(){
        Intent mIntent = new Intent();
        mIntent.setClass(this,RecycleViewActivity.class);
        return mIntent;
    }
}
