package com.shiran.qqsharedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String APP_ID = "1105602574"; //获取的APPID
    private ShareUiListener mIUiListener;
    private Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //传入参数APPID
        mTencent = Tencent.createInstance(APP_ID, MainActivity.this.getApplicationContext());
    }

    /**
     * 分享到QQ
     * @param v
     */
    public void qqShare(View v) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);//分享的类型
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "然了个然CSDN博客");//分享标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,"不管是怎样的过程,最终目的还是那个理想的结果。");//要分享的内容摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,"http://blog.csdn.net/sandyran");//内容地址
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"http://avatar.csdn.net/B/3/F/1_sandyran.jpg");//分享的图片URL
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试");//应用名称
        mTencent.shareToQQ(MainActivity.this, params, new ShareUiListener());
    }

    /**
     * 分享到QQ空间
     * @param v
     */
    public void qqQzoneShare(View v) {
        int QzoneType = QzoneShare.SHARE_TO_QZONE_TYPE_NO_TYPE;
        Bundle params = new Bundle();
        params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneType);
        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, "然了个然CSDN博客");//分享标题
        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, "不管是怎样的过程,最终目的还是那个理想的结果。");//分享的内容摘要
        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, "http://blog.csdn.net/sandyran/article/details/53204529");//分享的链接
        //分享的图片, 以ArrayList<String>的类型传入，以便支持多张图片（注：图片最多支持9张图片，多余的图片会被丢弃）
        ArrayList<String> imageUrls = new ArrayList<String>();
        imageUrls.add("http://avatar.csdn.net/B/3/F/1_sandyran.jpg");//添加一个图片地址
        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);//分享的图片URL
        mTencent.shareToQzone(MainActivity.this, params, new ShareUiListener());
    }

    /**
     * 分享音乐
     * @param v
     */
    public void qqMusicShare(View v) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);//分享的类型
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "歌曲名：说穿");//分享的标题
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "专辑名：最好的我们，歌手：谢楠");//分享的摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "https://y.qq.com/n/yqq/song/004Dle9I3TaSai.html");//分享的链接
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "https://y.gtimg.cn/music/photo_new/T002R300x300M000000kbocv24CRbE.jpg?max_age=2592000");//分享的图片URL
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "测试");//应用名称
        params.putString(QQShare.SHARE_TO_QQ_EXT_INT, String.valueOf(QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN));//分享时自动打开分享到QZone的对话框
        mTencent.shareToQQ(MainActivity.this, params, new ShareUiListener());

    }

    /**
     * 自定义监听器实现IUiListener，需要3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class ShareUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            //分享成功

        }

        @Override
        public void onError(UiError uiError) {
            //分享失败

        }

        @Override
        public void onCancel() {
            //分享取消

        }
    }

    /**
     * 回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != mTencent) {
            mTencent.onActivityResult(requestCode, resultCode, data);
        }
    }

}
