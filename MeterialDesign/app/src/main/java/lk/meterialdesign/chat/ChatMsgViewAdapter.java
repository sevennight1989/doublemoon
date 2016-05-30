package lk.meterialdesign.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import lk.meterialdesign.R;

/**
 * Created by Percy on 2016/5/27.
 */
public class ChatMsgViewAdapter extends BaseAdapter {


    private List<ChatMsgEntity> mColl;
    private LayoutInflater mInflater;

    public static interface IMsgViewType {
        int IMVT_COM_MSG = 0;
        int IMVT_TO_MSG = 1;
    }

    private static final int ITEMCOUNT = 2;

    public ChatMsgViewAdapter(Context context, List<ChatMsgEntity> coll) {
        mColl = coll;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        ChatMsgEntity entity = mColl.get(position);
        if (entity.getMsgType()) {
            return IMsgViewType.IMVT_COM_MSG;
        } else {
            return IMsgViewType.IMVT_TO_MSG;
        }
    }

    @Override
    public int getViewTypeCount() {
        return ITEMCOUNT;
    }

    @Override
    public int getCount() {
        return mColl.size();
    }

    @Override
    public Object getItem(int position) {
        return mColl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ChatMsgEntity chatMsgEntity = mColl.get(position);
        boolean isMsg = chatMsgEntity.getMsgType();

        if (convertView == null) {
            if (isMsg) {
                convertView = mInflater.inflate(R.layout.char_item_from, null);
            } else {
                convertView = mInflater.inflate(R.layout.char_item_to, null);
            }
            viewHolder = new ViewHolder();
            viewHolder.mName = (TextView) convertView.findViewById(R.id.chat_name);
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.chat_time);
            viewHolder.mInfo = (TextView) convertView.findViewById(R.id.chat_info);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mName.setText(chatMsgEntity.getName());
        viewHolder.mTime.setText(chatMsgEntity.getDate());
        viewHolder.mInfo.setText(chatMsgEntity.getMessage());
        return convertView;
    }


    private class ViewHolder {
        TextView mName;
        TextView mTime;
        TextView mInfo;
    }

}
