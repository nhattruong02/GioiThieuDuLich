package com.example.app_gioi_thieu_du_lich.DAO;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_gioi_thieu_du_lich.Adapter.TinhAdapter;
import com.example.app_gioi_thieu_du_lich.BitmapUtils;
import com.example.app_gioi_thieu_du_lich.DTO.Tinh;
import com.example.app_gioi_thieu_du_lich.Database.Database;
import com.example.app_gioi_thieu_du_lich.R;

import java.util.ArrayList;
import java.util.List;

public class TinhDAO extends AppCompatActivity {
    TinhAdapter adapter;
    List<Tinh> tinhList;
    GridView grtinh;
    Database database;
    EditText txttimkiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tinh);
        Anhxa();
        loadTinh();
        grtinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TinhDAO.this,DiadiemDAO.class);
                intent.putExtra("Matinh",tinhList.get(i).getMatinh());
                startActivity(intent);
            }
        });
        txttimkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }
    private void filter(String text){
        ArrayList<Tinh> filteredList= new ArrayList<>();
        for (Tinh t : tinhList){
            if(t.getTentinh().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(t);
            }
        }
        adapter.filterList(filteredList);
    }
    private  void Anhxa(){
        grtinh = findViewById(R.id.gvtinh);
        txttimkiem=findViewById(R.id.txttimkiem);
        database = new Database(this);
    }
    private void loadTinh(){
        tinhList = new ArrayList<>();
        tinhList = database.getlistTinh();
        adapter = new TinhAdapter(this, R.layout.dong_tinh, tinhList);
        grtinh.setAdapter(adapter);
    }
}
