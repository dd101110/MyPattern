package com.yibin.mypattern.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.yibin.mypattern.R;
import com.yibin.mypattern.utils.ImageLoader;

public class MainActivity extends AppCompatActivity {

    public static final String IMG_URL = "http://car2.autoimg.cn/cardfs/product/g19/M0C/CE/71/1024x0_1_q87_autohomecar__wKjBxFekCjSAPXRuAAIDA2tihQs156.jpg";

    private ImageView image;
    private ImageLoader mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);

        displayImage();
    }

    private void displayImage() {
        if (null == mLoader) mLoader = new ImageLoader(image);
        mLoader.displayImg(IMG_URL);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        displayImage();
    }
}
