package lk.meterialdesign.code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ImageView;

import com.google.zxing.WriterException;

import lk.meterialdesign.R;

public class MakeCodeActivity extends AppCompatActivity {

    ImageView codeImg;
    private static final String TAG = "PengLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_code);
        initLayout();
    }


    private void initLayout() {
        codeImg = (ImageView) findViewById(R.id.code);
        try {
            codeImg.setImageBitmap(CodeUtils.Create2DCode("ZhangSan"));
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
