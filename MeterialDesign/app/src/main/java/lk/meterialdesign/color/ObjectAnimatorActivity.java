package lk.meterialdesign.color;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lk.meterialdesign.R;
import lk.meterialdesign.color.view.ObjectAnimatorView;

public class ObjectAnimatorActivity extends AppCompatActivity {

    ObjectAnimatorView mObjectAnimatorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        mObjectAnimatorView = (ObjectAnimatorView) findViewById(R.id.objani);
        startAnimatio();
    }


    private void startAnimatio(){
        ObjectAnimator animator = ObjectAnimator.ofInt(mObjectAnimatorView,"pointRadius",0,500,300);
        animator.setDuration(2000);
        animator.start();
    }
}
