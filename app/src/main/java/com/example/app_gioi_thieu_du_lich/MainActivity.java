package com.example.app_gioi_thieu_du_lich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app_gioi_thieu_du_lich.DAO.DanhsachDAO;
import com.example.app_gioi_thieu_du_lich.DAO.TinhDAO;

public class MainActivity extends AppCompatActivity {
    Button btnkhampha,btndanhsach,btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        btnkhampha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TinhDAO.class);
                startActivity(intent);
            }
        });
        btndanhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DanhsachDAO.class);
                startActivity(intent);
            }
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Dangnhap.class);
                startActivity(intent);
            }
        });
    }
    private void Anhxa(){
        btnkhampha = findViewById(R.id.btnkhampha);
        btndanhsach = findViewById(R.id.btndanhsach);
        btnthoat = findViewById(R.id.btnthoat);
    }
}