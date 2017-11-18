/*
Depeloper: XUEJING MA
DATE:2017 Fall Term, Multimedia Communication
 */
package com.example.whoami;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MemorizeActivity extends AppCompatActivity {

    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorize);
        if( this.getIntent().hasExtra("Bitmap") ) {
            bitmap = this.getIntent().getParcelableExtra("Bitmap");
            imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);
        }
    }

}
