package com.huazhu.share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.huazhu.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by uiprj on 7/13/16.
 */
@ContentView(R.layout.fragment_share)
public class ShareFragment extends DialogFragment {

    @ViewInject(R.id.sinaweibo)
    Button mShareWeibo;
    @ViewInject(R.id.qq)
    Button mqq;
    @ViewInject(R.id.qzon)
    Button mQzon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        return view;
    }


    @Event(type = View.OnClickListener.class, value = R.id.sinaweibo)
    private void shareWeibo(View v) {
        String url = "http://www.umeng.com";
        UMImage image = new UMImage(getActivity(), "http://www.umeng.com/images/pic/social/integrated_3.png");
        new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.SINA).setCallback(umShareListener)
        .withText("Umeng Share")
        .withTitle("this is title")
        .withMedia(image)
        .withTargetUrl(url)
        .share();
    }

    @Event(type = View.OnClickListener.class, value = R.id.qq)
    private void shareQQ(View v) {
        UMImage image = new UMImage(getActivity(), "http://www.umeng.com/images/pic/social/integrated_3.png");
        new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener)
        .withMedia(image)
        .withText("hello umeng")
        .share();
    }

    @Event(type = View.OnClickListener.class, value = R.id.qzon)
    private void shareQzon(View v) {
        UMImage image = new UMImage(getActivity(), "http://www.umeng.com/images/pic/social/integrated_3.png");
        new ShareAction(getActivity()).setPlatform(SHARE_MEDIA.QZONE).setCallback(umShareListener)
        .withText("空间")
        .withTitle("分享到空间")
        .withMedia(image)
        .share();
    }


    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(getContext(),platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getContext(),platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
//                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getContext(),platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
