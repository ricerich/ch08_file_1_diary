package com.example.picture_viewer;

import java.io.File;

import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    myPictureView myPicture;

    int curNum;

    File[] imageFiles;

    String imageFname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");
        ActivityCompat.requestPermissions(
                this, new String[]
                        {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);

        myPicture = (myPictureView) findViewById(R.id.myPictureView1);

        TextView tv1 = findViewById(R.id.tv1);



        imageFiles = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/Pictures").listFiles();


        tv1.setText("1/" + (imageFiles.length-1));

        imageFname = imageFiles[0].toString();

        myPicture.imagePath=imageFname;

        btnPrev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curNum <= 0) {
//                    Toast.makeText(getApplicationContext(), "첫번째 그림입니다", Toast.LENGTH_SHORT)
//                            .show();
                    curNum = imageFiles.length-2;

                } else {
                    curNum--;
                }

                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
                tv1.setText((curNum+1) +"/" + (imageFiles.length-1));
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (curNum >= imageFiles.length - 2) {
//                    Toast.makeText(getApplicationContext(), "마지막 그림입니다", Toast.LENGTH_SHORT)
//                            .show();

                    curNum = 0;

                } else {
                    curNum++;
                }
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
                tv1.setText((curNum+1) +"/" + (imageFiles.length-1));
            }
        });

    }
}