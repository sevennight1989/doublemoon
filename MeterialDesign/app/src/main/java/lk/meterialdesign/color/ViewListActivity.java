package lk.meterialdesign.color;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lk.meterialdesign.R;
import lk.meterialdesign.code.RecycleViewDivider;
import lk.meterialdesign.color.view.GuaGuaCardViewActivity;

public class ViewListActivity extends AppCompatActivity {

    RecyclerView viewList;
    ListAdapter mListAdapter;
    String[] mDatas;

    private static final int GUAGUACARDVIEW = 0;
    private static final int HEARTVIEW = 1;
    private static final int WAVEVIEW = 2;
    private static final int BESSEL = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        viewList = (RecyclerView) findViewById(R.id.viewlist);
        viewList.setLayoutManager(new LinearLayoutManager(this));
        viewList.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 30,
        getResources().getColor(android.R.color.white)));
        mDatas = getResources().getStringArray(R.array.viewlist);
        mListAdapter = new ListAdapter();
        viewList.setAdapter(mListAdapter);
    }


    class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(ViewListActivity.this).inflate(R.layout
            .view_list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(rootView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.mViewName.setText(mDatas[position]);
            holder.mViewName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mIntent = new Intent();
                    switch (position) {
                        case GUAGUACARDVIEW:
                            mIntent.setClass(ViewListActivity.this, GuaGuaCardViewActivity.class);
                            startActivity(mIntent);
                            break;
                        case HEARTVIEW:
                            mIntent.setClass(ViewListActivity.this, HeartActivity.class);
                            startActivity(mIntent);
                            break;
                        case WAVEVIEW:
                            mIntent.setClass(ViewListActivity.this, WaveActivity.class);
                            startActivity(mIntent);
                            break;
                        case BESSEL:
                            mIntent.setClass(ViewListActivity.this, BesselActivity.class);
                            startActivity(mIntent);
                            break;
                        default:
                            break;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView mViewName;

            public ViewHolder(View itemView) {
                super(itemView);
                mViewName = (TextView) itemView.findViewById(R.id.viewname);
            }
        }
    }
}
