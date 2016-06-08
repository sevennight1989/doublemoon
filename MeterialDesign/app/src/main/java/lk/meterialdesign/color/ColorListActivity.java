package lk.meterialdesign.color;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lk.meterialdesign.R;

public class ColorListActivity extends AppCompatActivity implements ColorMatrixFragment.OnFragmentInteractionListener{

    Fragment colorMatrixFragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_list);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        colorMatrixFragment = ColorMatrixFragment.newInstance("color1","color2");
        ft.add(R.id.colortop,colorMatrixFragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
