package lk.meterialdesign;

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

public class RecycleViewActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    HomeAdapter mHomeAdapter;
    List<String> mInfoList = new ArrayList<>();
    private static final String TAG = "PengLog";


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initData();
        initLayout();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mInfoList.add("ID： " + i);
        }
    }


    private void initLayout() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mHomeAdapter = new HomeAdapter();
        mHomeAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG,"onlick : "+position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d(TAG,"onlonglick : "+position);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL,
        30, getResources().getColor(android.R.color.white, getTheme())));
        mRecyclerView.setAdapter(mHomeAdapter);

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
            this.onItemClickLitener = onItemClickLitener;
        }

        OnItemClickLitener onItemClickLitener;


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(RecycleViewActivity.this).inflate(R.layout
            .recycleview_item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(rootView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.mInfoView.setText(mInfoList.get(position));
            holder.mInfoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLitener.onItemClick(v,position);
                }
            });
            holder.mInfoView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickLitener.onItemLongClick(v,position);
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mInfoList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder  {

            TextView mInfoView;

            public MyViewHolder(View itemView) {
                super(itemView);
                mInfoView = (TextView) itemView.findViewById(R.id.textinfo);
            }
        }
    }


}
