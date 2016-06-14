package lk.meterialdesign.color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import lk.meterialdesign.R;

public class BesselActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton mPopBt;
    PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bessel);
        mPopBt = (ImageButton) findViewById(R.id.pop_bt);
        mPopBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_bt:
                createPopupWindow(v);
                break;

            default:
                break;
        }
    }

    private View createView(){
        return LayoutInflater.from(this).inflate(R.layout.popup_layout,null);
    }



    private void createPopupWindow(View view) {
        View contentView = createView();
        TextView mText = (TextView) contentView.findViewById(R.id.pop_txt);
        mText.setText(R.string.app_name);
        mText.setGravity(Gravity.CENTER);
        mPopupWindow = new PopupWindow(contentView, RelativeLayout.LayoutParams.WRAP_CONTENT,
        RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
//        mPopupWindow.showAsDropDown();
        mPopupWindow.showAtLocation(view.getRootView(),Gravity.CENTER,0,0);
        Log.d("PengLog","show");
    }
}
