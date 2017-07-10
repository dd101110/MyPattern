package com.yibin.mypattern.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.yibin.mypattern.R;
import com.yibin.mypattern.utils.ImageLoader;

public class MainActivity extends AppCompatActivity {

    public static final String IMG_URL = "https://m.360buyimg.com/live/jfs/t6073/305/4292747561/187253/897fc238/5961e051Nec2387b5.jpg!q70.jpg.webp";

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);

        displayImage();
    }

    private void displayImage() {
        ImageLoader imageLoader = new ImageLoader(image);
        imageLoader.displayImg(IMG_URL);
    }
}
