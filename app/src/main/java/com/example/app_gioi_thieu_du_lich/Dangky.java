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

import com.example.app_gioi_thieu_du_lich.Database.Database;


public class Dangky extends AppCompatActivity {
    EditText edttaikhoan, edtmatkhau, edthoten, edtgioitinh, edtnamsinh, edtsdt;
    Button btndangky, btnthoat,btnnhaplai;
    Database database;
    LinearLayout linearLayout;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dang_ky);
        linearLayout=findViewById(R.id.linearLayoutDK);
        animation= AnimationUtils.loadAnimation(this,R.anim.uptodowndiaonal);
        linearLayout.startAnimation(animation);
        Anhxa();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dangkytk();
            }
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnthoat();
            }
        });
        btnnhaplai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edttaikhoan.setText("");
                edtmatkhau.setText("");
                edthoten.setText("");
                edtgioitinh.setText("");
                edtnamsinh.setText("");
                edtsdt.setText("");
            }
        });
    }

    private void btnthoat() {
        Intent intent = new Intent(Dangky.this, Dangnhap.class);
        startActivity(intent);
    }


    private void Anhxa() {
        edttaikhoan = (EditText) findViewById(R.id.edttaikhoandk);
        edtmatkhau = (EditText) findViewById(R.id.edtmatkhaudk);
        edthoten = (EditText) findViewById(R.id.edthotendk);
        edtgioitinh = (EditText) findViewById(R.id.edtgioitinhdk);
        edtnamsinh = (EditText) findViewById(R.id.edtnamsinhdk);
        edtsdt = (EditText) findViewById(R.id.edtsdtdk);
        btndangky = findViewById(R.id.btndangkytk);
        btnthoat = findViewById(R.id.btnthoatdk);
        database = new Database(this);
        btnnhaplai=findViewById(R.id.btnnhaplai);
    }

    private void Dangkytk() {
        String taikhoan = edttaikhoan.getText().toString();
        String matkhau = edtmatkhau.getText().toString();
        String hoten = edthoten.getText().toString();
        String gioitinh = edtgioitinh.getText().toString();
        String namsinh = edtnamsinh.getText().toString();
        String sdt = edtsdt.getText().toString();
        if (database.ktDangky(taikhoan)) {
            Toast.makeText(Dangky.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
        }else if (hoten.equals("")) {
            Toast.makeText(Dangky.this, "Họ tên không được để trống !", Toast.LENGTH_LONG).show();
        } else if (hoten.matches((".*[0-9].*"))) {
            Toast.makeText(Dangky.this, "Họ tên chỉ được nhâp chuỗi !", Toast.LENGTH_LONG).show();
        } else if (gioitinh.equals("")) {
            Toast.makeText(Dangky.this, "Giới tính không được để trống 1", Toast.LENGTH_LONG).show();
        } else if (namsinh.equals("")) {
            Toast.makeText(Dangky.this, "Năm sinh không đươc để trống !", Toast.LENGTH_LONG).show();
        } else if (sdt.equals("")) {
            Toast.makeText(Dangky.this, "Số điện thoại không được để trống !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(Dangky.this, "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
            database.Dangky(taikhoan, matkhau, hoten, gioitinh, namsinh, sdt);
            Intent intent = new Intent(Dangky.this, Dangnhap.class);
            startActivity(intent);
        }
    }
}
