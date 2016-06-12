package lk.meterialdesign;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Outline;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;

import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

import lk.meterialdesign.chat.ChatActivity;
import lk.meterialdesign.code.MakeCodeActivity;
import lk.meterialdesign.code.RecycleViewActivity;
import lk.meterialdesign.color.ColorListActivity;
import lk.meterialdesign.color.ViewListActivity;
import lk.meterialdesign.push.Utils;
import lk.meterialdesign.settings.SettingActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView mImg;
    Button mRecycleBt;
    Button mcodeBt;
    Button mChat;
    Button mSettings;
    Button mColor;
    Button mViews;
    int akBtnId = 0;
    int initBtnId = 0;
    int richBtnId = 0;
    int setTagBtnId = 0;
    int delTagBtnId = 0;
    int clearLogBtnId = 0;
    int showTagBtnId = 0;
    int unbindBtnId = 0;
    int setunDisturbBtnId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPushCOnfig();
        initViews();
//        testNotification();


    }


    private void initPushCOnfig() {
        // checkApikey();
        Utils.logStringCache = Utils.getLogText(getApplicationContext());

        Resources resource = this.getResources();
        String pkgName = this.getPackageName();

        akBtnId = resource.getIdentifier("btn_initAK", "id", pkgName);
        initBtnId = resource.getIdentifier("btn_init", "id", pkgName);
        richBtnId = resource.getIdentifier("btn_rich", "id", pkgName);
        setTagBtnId = resource.getIdentifier("btn_setTags", "id", pkgName);
        delTagBtnId = resource.getIdentifier("btn_delTags", "id", pkgName);
        clearLogBtnId = resource.getIdentifier("btn_clear_log", "id", pkgName);
        showTagBtnId = resource.getIdentifier("btn_showTags", "id", pkgName);
        unbindBtnId = resource.getIdentifier("btn_unbindTags", "id", pkgName);
        setunDisturbBtnId = resource.getIdentifier("btn_setunDisturb", "id",
        pkgName);


        // Push: 以apikey的方式登录，一般放在主Activity的onCreate中。
        // 这里把apikey存放于manifest文件中，只是一种存放方式，
        // 您可以用自定义常量等其它方式实现，来替换参数中的Utils.getMetaValue(PushDemoActivity.this,
        // "api_key")
//        ！！ 请将AndroidManifest.xml 122行 api_key 字段值修改为自己的 api_key 方可使用 ！！
//        ！！ ATTENTION：You need to modify the value of api_key to your own at row 122 in AndroidManifest.xml to use this Demo !!
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,
        Utils.getMetaValue(MainActivity.this, "api_key"));
        // Push: 如果想基于地理位置推送，可以打开支持地理位置的推送的开关
        // PushManager.enableLbs(getApplicationContext());

        // Push: 设置自定义的通知样式，具体API介绍见用户手册，如果想使用系统默认的可以不加这段代码
        // 请在通知推送界面中，高级设置->通知栏样式->自定义样式，选中并且填写值：1，
        // 与下方代码中 PushManager.setNotificationBuilder(this, 1, cBuilder)中的第二个参数对应
        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
        resource.getIdentifier(
        "notification_custom_builder", "layout", pkgName),
        resource.getIdentifier("notification_icon", "id", pkgName),
        resource.getIdentifier("notification_title", "id", pkgName),
        resource.getIdentifier("notification_text", "id", pkgName));
        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
        cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);
        cBuilder.setLayoutDrawable(resource.getIdentifier(
        "simple_notification_icon", "drawable", pkgName));
        cBuilder.setNotificationSound(Uri.withAppendedPath(
        MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
        // 推送高级设置，通知栏样式设置为下面的ID
        PushManager.setNotificationBuilder(this, 1, cBuilder);
    }

    private void initViews() {
        mImg = (ImageView) findViewById(R.id.img);
        mImg.setVisibility(View.GONE);
        mRecycleBt = (Button) findViewById(R.id.recycleView);
        mRecycleBt.setOnClickListener(this);
        mcodeBt = (Button) findViewById(R.id.makecode);
        mcodeBt.setOnClickListener(this);
        mChat = (Button) findViewById(R.id.chat);
        mChat.setOnClickListener(this);
        mSettings = (Button) findViewById(R.id.settings);
        mSettings.setOnClickListener(this);
        mColor = (Button) findViewById(R.id.color);
        mColor.setOnClickListener(this);
        mViews = (Button) findViewById(R.id.views);
        mViews.setOnClickListener(this);
    }

    private void testNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Material");
        builder.setContentText("New Message");
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
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
        switch (v.getId()) {
            case R.id.recycleView:
                startActivity(makeRecycleViewIntent());
                break;

            case R.id.makecode:
                startActivity(makeCodeIntent());
                break;

            case R.id.chat:
                startActivity(makeCharIntent());
                break;

            case R.id.settings:
                startActivity(makeSettingIntent());
                break;

            case R.id.color:
                startActivity(makeColorActivity());
                break;

            case R.id.views:
                startActivity(makeViewsActivity());
                break;

            default:
                break;
        }
    }

    private Intent makeRecycleViewIntent() {
        Intent mIntent = new Intent();
        mIntent.setClass(this, RecycleViewActivity.class);
        return mIntent;
    }

    private Intent makeCodeIntent() {
        Intent mIntent = new Intent();
        mIntent.setClass(this, MakeCodeActivity.class);
        return mIntent;
    }

    private Intent makeCharIntent() {
        Intent mIntent = new Intent();
        mIntent.setClass(this, ChatActivity.class);
        return mIntent;
    }

    private Intent makeSettingIntent() {
        Intent mIntent = new Intent();
        mIntent.setClass(this, SettingActivity.class);
        return mIntent;
    }

    private Intent makeColorActivity() {
        Intent mIntent = new Intent();
        mIntent.setClass(this, ColorListActivity.class);
        return mIntent;
    }

    private Intent makeViewsActivity() {
        Intent mIntent = new Intent();
        mIntent.setClass(this, ViewListActivity.class);
        return mIntent;
    }

}
