package com.lhy.jnidemo01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    static {
        System.loadLibrary("hello-jni");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public native String helloFromC();

    public void click(View v) {
        String str = helloFromC();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
