package com.example.app_gioi_thieu_du_lich;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_gioi_thieu_du_lich.DAO.QuanlytinhDAO;
import com.example.app_gioi_thieu_du_lich.Database.Database;

public class Dangnhap extends AppCompatActivity {
    EditText edttaikhoan, edtmatkhau;
    Button btndangnhap, btndangky;
    Database database;
    String taikhoan, matkhau;
    Animation animation;
    LinearLayout linearLayout;
    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dang_nhap);
        linearLayout = findViewById(R.id.linearLayoutlogin);
        animation = AnimationUtils.loadAnimation(this, R.anim.uptodowndiaonal);
        linearLayout.startAnimation(animation);
        Anhxa();
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dangnhap();
            }
        });
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btndangky();
            }
        });
    }

    private void btndangky() {
        Intent intent = new Intent(Dangnhap.this, Dangky.class);
        startActivity(intent);
    }

    private void Anhxa() {
        edttaikhoan = (EditText) findViewById(R.id.edttaikhoan);
        edtmatkhau = (EditText) findViewById(R.id.edtmatkhau);
        btndangnhap = findViewById(R.id.btndangnhap);
        btndangky = findViewById(R.id.btndangky);
        database = new Database(this);
    }

    private void Dangnhap() {
        taikhoan = edttaikhoan.getText().toString();
        matkhau = edtmatkhau.getText().toString();
        if(taikhoan.equals("Admin") && matkhau.equals("1")){
            Intent intent = new Intent(Dangnhap.this, QuanlytinhDAO.class);
            startActivity(intent);
        }
        else if (database.ktdangnhap(taikhoan, matkhau)) {
            Intent intent = new Intent(Dangnhap.this, MainActivity.class);
            startActivity(intent);
            id = database.getIdtaikhoan(taikhoan);
        } else {
            Toast.makeText(Dangnhap.this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
        }
    }
}
