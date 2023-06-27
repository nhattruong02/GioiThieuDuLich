package com.example.app_gioi_thieu_du_lich.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_gioi_thieu_du_lich.BitmapUtils;
import com.example.app_gioi_thieu_du_lich.DTO.Diadiem;
import com.example.app_gioi_thieu_du_lich.DTO.Tinh;
import com.example.app_gioi_thieu_du_lich.Dangnhap;
import com.example.app_gioi_thieu_du_lich.Database.Database;
import com.example.app_gioi_thieu_du_lich.R;

import java.util.List;

public class DiadiemAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Diadiem> diadiemList;
    public DiadiemAdapter(Context context, int layout, List<Diadiem> diadiemList) {
        this.context = context;
        this.layout = layout;
        this.diadiemList = diadiemList;
    }

    @Override
    public int getCount() {
        return diadiemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHoder{
        TextView tendiadiem,vitri;
        ImageView hinhdiadiem,imgthem;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DiadiemAdapter.ViewHoder hoder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(layout,null);
            hoder = new DiadiemAdapter.ViewHoder();
            hoder.tendiadiem = view.findViewById(R.id.txttendiadiem);
            hoder.vitri = view.findViewById(R.id.txtvitri);
            hoder.hinhdiadiem = view.findViewById(R.id.imghinhdd);
            hoder.imgthem = view.findViewById(R.id.imgthem);
            view.setTag(hoder);
        }else{
            hoder = (DiadiemAdapter.ViewHoder) view.getTag();
        }
        Diadiem diadiem = diadiemList.get(i);
        hoder.tendiadiem.setText(diadiem.getTendiadiem());
        hoder.vitri.setText(diadiem.getVitri());
        byte[] hinh = diadiem.getHinhdiadiem();
        hoder.hinhdiadiem.setImageBitmap(BitmapUtils.getImage(hinh));
        hoder.imgthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database database = new Database(context);
                if(database.ktDiadiem(diadiem.getTendiadiem()) == true){
                    Toast.makeText(context, diadiem.getTendiadiem() +" đã có trong danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
                else{
                    database.insertDanhsach(diadiem.getTendiadiem(),diadiem.getVitri(),diadiem.getHinhdiadiem(), Dangnhap.id);
                    Toast.makeText(context, "Đã thêm " +diadiem.getTendiadiem() +" vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
