package lk.meterialdesign.ioc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import lk.meterialdesign.R;

@ContentView(value = R.layout.activity_ioc)
public class IOCActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewInject(R.id.ioc_tv)
    private TextView mIoctv;

    @ViewInject(R.id.ioc_bt)
    private Button mIocBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.init(this);
        mIocBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
