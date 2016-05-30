package lk.meterialdesign.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lk.meterialdesign.R;

public class ChatActivity extends AppCompatActivity {

    private String[] msgArray = new String[]{"有大吗", "有！你呢？", "我也有", "那上吧",
    "打啊！你放大啊！", "你TM咋不放大呢？留大抢人头啊？CAO！你个菜B", "2B不解释", "尼滚...",
    "今晚去网吧包夜吧？", "有毛片吗？", "种子一大堆啊~还怕没片？", "OK,搞起！！"};

    private String[] dataArray = new String[]{"2012-09-22 18:00:02",
    "2012-09-22 18:10:22", "2012-09-22 18:11:24",
    "2012-09-22 18:20:23", "2012-09-22 18:30:31",
    "2012-09-22 18:35:37", "2012-09-22 18:40:13",
    "2012-09-22 18:50:26", "2012-09-22 18:52:57",
    "2012-09-22 18:55:11", "2012-09-22 18:56:45",
    "2012-09-22 18:57:33",};

    private final static int COUNT = 12;

    private List<ChatMsgEntity> mDataArrays = new ArrayList<>();

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initViews();
        initData();
    }

    private void initViews(){
        mListView = (ListView) findViewById(R.id.chatList);
    }

    private void initData(){
        for(int i=0;i<COUNT;i++){
            ChatMsgEntity chatMsgEntity = new ChatMsgEntity();
            chatMsgEntity.setDate(dataArray[i]);
            if(i%2==0){
                chatMsgEntity.setName("A");
                chatMsgEntity.setMsgType(true);
            }else{
                chatMsgEntity.setName("B");
                chatMsgEntity.setMsgType(false);
            }
            chatMsgEntity.setMessage(msgArray[i]);
            mDataArrays.add(chatMsgEntity);
        }
        ChatMsgViewAdapter chatMsgViewAdapter = new ChatMsgViewAdapter(this,mDataArrays);
        mListView.setAdapter(chatMsgViewAdapter);
    }



}
