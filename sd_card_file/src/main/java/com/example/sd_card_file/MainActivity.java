package com.example.sd_card_file;

import java.io.FileInputStream;
import java.io.IOException;

import android.os.Environment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        Button btnRead;
        final EditText edtSD;
        btnRead = (Button) findViewById(R.id.btnRead);
        edtSD = (EditText) findViewById(R.id.edtSD);

        btnRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    FileInputStream inFs = new FileInputStream( "/storage/emulated/0/sd_test2.txt");

//                    String path = Environment.getExternalStorageDirectory().getAbsolutePath();
//                    FileInputStream inFs = new FileInputStream( path+"/sd_test.txt");

                    byte[] txt = new byte[inFs.available()];
                    inFs.read(txt);
                    edtSD.setText(new String(txt));
                    inFs.close();
                } catch (IOException e) {
                }
            }
        });
    }

}
