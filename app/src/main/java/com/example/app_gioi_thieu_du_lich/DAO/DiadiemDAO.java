package com.example.app_gioi_thieu_du_lich.DAO;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_gioi_thieu_du_lich.Adapter.DiadiemAdapter;
import com.example.app_gioi_thieu_du_lich.BitmapUtils;
import com.example.app_gioi_thieu_du_lich.DTO.Diadiem;
import com.example.app_gioi_thieu_du_lich.Database.Database;
import com.example.app_gioi_thieu_du_lich.R;

import java.util.ArrayList;
import java.util.List;

public class DiadiemDAO extends AppCompatActivity {
    ListView lvdiadiem;
    DiadiemAdapter adapter;
    List<Diadiem> diadiemList = new ArrayList<>();
    Database database;
    int matinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dia_diem);
        Anhxa();
        loaddiadiem();
        lvdiadiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DiadiemDAO.this,DiadiemchitietDAO.class);
                intent.putExtra("Tendiadiem",diadiemList.get(i).getTendiadiem());
                intent.putExtra("Mota", diadiemList.get(i).getMota());
                intent.putExtra("Linkvideo", diadiemList.get(i).getLinkvideo());
                startActivity(intent);
            }
        });
    }
    private void Anhxa(){
        lvdiadiem = findViewById(R.id.lvdiadiem);
        database = new Database(this);
    }
    private void loaddiadiem(){
        Intent intent = getIntent();
        matinh = intent.getIntExtra("Matinh", 0);
        diadiemList = database.getlistDiadiem(matinh);
        adapter = new DiadiemAdapter(this,R.layout.dong_dia_diem,diadiemList);
        lvdiadiem.setAdapter(adapter);
    }
}
