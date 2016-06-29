package lk.meterialdesign.ioc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import lk.meterialdesign.R;

@ContentView(value = R.layout.activity_ioc)
public class IOCActivity extends AppCompatActivity{

    @ViewInject(R.id.ioc_tv)
    private TextView mIoctv;

    @ViewInject(R.id.ioc_bt)
    private Button mIocBt;

    @ViewInject(R.id.ioc_bt2)
    private Button mIocBt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.init(this);
    }

    @OnClick({R.id.ioc_bt,R.id.ioc_bt2})
    public void  clickBtnInvoked(View view){
        switch (view.getId()){
            case R.id.ioc_bt:

                break;

            case R.id.ioc_bt2:

                break;
            default:
                break;
        }
    }
}
