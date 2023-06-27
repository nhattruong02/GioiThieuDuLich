package com.example.app_gioi_thieu_du_lich.DAO;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_gioi_thieu_du_lich.BitmapUtils;
import com.example.app_gioi_thieu_du_lich.R;
import com.example.app_gioi_thieu_du_lich.Xemvideo;

public class DiadiemchitietDAO extends AppCompatActivity {
    TextView txtten,txtmota;
    ImageButton imgvideo;
    String ten,mota,linkvideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chi_tiet_dia_diem);
        Anhxa();
        loadnoidung();
        txtmota.setMovementMethod(new ScrollingMovementMethod());
        imgvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiadiemchitietDAO.this, Xemvideo.class);
                intent.putExtra("Linkvideo",linkvideo);
                startActivity(intent);
            }
        });
    }
    private void Anhxa(){
        txtten = findViewById(R.id.txttenctdiadiem);
        txtmota = findViewById(R.id.txtmota);
        imgvideo = findViewById(R.id.btnxemvideo);
        Intent intent = getIntent();
        ten = intent.getStringExtra("Tendiadiem");
        mota = intent.getStringExtra("Mota");
        linkvideo =intent.getStringExtra("Linkvideo");
    }
    private void loadnoidung(){
        txtten.setText(ten);
        txtmota.setText(mota);
    }
}
