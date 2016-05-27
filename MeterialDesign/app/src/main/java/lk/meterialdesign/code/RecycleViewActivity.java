package lk.meterialdesign.code;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.WriterException;

import java.util.ArrayList;
import java.util.List;

import lk.meterialdesign.PermissionUtils;
import lk.meterialdesign.R;

public class RecycleViewActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    HomeAdapter mHomeAdapter;
    private static final String TAG = "PengLog";

    private String PERMISSIONS[] = {Manifest.permission.READ_CONTACTS};
    private static final int PERMISSION_REQUEST_CODE = 1;
    ContentResolver resolver;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        resolver = getContentResolver();
        int sdkVersion = Integer.parseInt(Build.VERSION.SDK);
        if(sdkVersion>=23){
            if (PermissionUtils.checkPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE)) {
                initLayout();
            }
        }
        else {
            initLayout();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (PermissionUtils.checkPermissionResult(permissions, grantResults)) {
            initLayout();
        }
    }

    private List<PersonInfo> handleContacts() {
        List<PersonInfo> list = new ArrayList<>();
        PersonInfo p;
        Cursor c = getContants();
        while (c.moveToNext()) {
            String id = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String email = getEmailInfo(id);
            p = new PersonInfo();
            p.setId(id);
            p.setName(name);
            p.setNumber(number);
            p.setEmail(email);
            list.add(p);
            Log.d(TAG, "id: " + id + "  name " + name + "  number: " + number + "  email: " + email);
        }
        return list;
    }

    private Cursor getContants() {

        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        new String[]{
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
        },
        null, null, null);
        return phoneCursor;
    }

    private String getEmailInfo(String contactId) {
        String email = "";
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
        null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contactId,
        null, null);
        while (cursor.moveToNext()) {
            email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
        }
        return email;
    }


    private void initLayout() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        mHomeAdapter = new HomeAdapter(handleContacts());
        mHomeAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(TAG, "onlick : " + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Log.d(TAG, "onlonglick : " + position);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL,
        30, getResources().getColor(android.R.color.white)));
        mRecyclerView.setAdapter(mHomeAdapter);

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        List<PersonInfo> mP;

        private HomeAdapter(List<PersonInfo> p) {
            mP = p;
        }


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

            holder.name.setText(mP.get(position).getName());
            holder.phone.setText(getResources().getString(R.string.phone) + mP.get(position)
            .getNumber());
            holder.email.setText(getResources().getString(R.string.email) + mP.get(position)
            .getEmail());
            try {
                holder.code.setImageBitmap(CodeUtils.Create2DCode(mP.get(position).getName()));
            } catch (WriterException e) {
                e.printStackTrace();
            }
            holder.card_top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLitener.onItemClick(v, position);
                }
            });
            holder.card_top.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickLitener.onItemLongClick(v, position);
                    return true;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mP.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout card_top;
            TextView name;
            TextView phone;
            TextView email;
            ImageView code;

            public MyViewHolder(View itemView) {
                super(itemView);
                card_top = (RelativeLayout) itemView.findViewById(R.id.card_top);
                name = (TextView) itemView.findViewById(R.id.name);
                phone = (TextView) itemView.findViewById(R.id.phone);
                email = (TextView) itemView.findViewById(R.id.email);
                code = (ImageView) itemView.findViewById(R.id.code);
            }
        }
    }


}
