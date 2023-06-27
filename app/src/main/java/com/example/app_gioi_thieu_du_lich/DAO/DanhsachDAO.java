package com.example.app_gioi_thieu_du_lich.DAO;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_gioi_thieu_du_lich.Adapter.DanhsachAdapter;
import com.example.app_gioi_thieu_du_lich.Adapter.DiadiemAdapter;
import com.example.app_gioi_thieu_du_lich.DTO.Danhsach;
import com.example.app_gioi_thieu_du_lich.DTO.Diadiem;
import com.example.app_gioi_thieu_du_lich.Dangnhap;
import com.example.app_gioi_thieu_du_lich.Database.Database;
import com.example.app_gioi_thieu_du_lich.R;

import java.util.ArrayList;
import java.util.List;

public class DanhsachDAO extends AppCompatActivity {
    ListView lvdanhsach;
    DanhsachAdapter adapter;
    List<Danhsach> danhsachList = new ArrayList<>();
    Database database;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danh_sach);
        Anhxa();
        loaddanhsach();
    }

    private void Anhxa() {
        lvdanhsach = findViewById(R.id.lvdanhsach);
        database = new Database(this);
    }

    private void loaddanhsach() {
        danhsachList = database.getlistDanhsach(Dangnhap.id);
        adapter = new DanhsachAdapter(this, R.layout.dong_danh_sach, danhsachList);
        lvdanhsach.setAdapter(adapter);
    }

    public void xoadanhsach(String tendiadiem, int madiadiem) {
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Bạn có muốn xoá địa điểm " + tendiadiem + " này không?");
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.xoadanhsach(madiadiem);
                Toast.makeText(DanhsachDAO.this, "Đã xoá "+ tendiadiem , Toast.LENGTH_SHORT).show();
                loaddanhsach();
            }
        });
        dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }
}

