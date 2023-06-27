package com.example.app_gioi_thieu_du_lich.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_gioi_thieu_du_lich.BitmapUtils;
import com.example.app_gioi_thieu_du_lich.DAO.DanhsachDAO;
import com.example.app_gioi_thieu_du_lich.DAO.TinhDAO;
import com.example.app_gioi_thieu_du_lich.DTO.Danhsach;
import com.example.app_gioi_thieu_du_lich.DTO.Diadiem;
import com.example.app_gioi_thieu_du_lich.Database.Database;
import com.example.app_gioi_thieu_du_lich.R;

import java.util.List;

public class DanhsachAdapter extends BaseAdapter {
    DanhsachDAO context;
    int layout;
    List<Danhsach> danhsachList;

    public DanhsachAdapter(DanhsachDAO context, int layout, List<Danhsach> danhsachList) {
        this.context = context;
        this.layout = layout;
        this.danhsachList = danhsachList;
    }

    @Override
    public int getCount() {
        return danhsachList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHoder {
        TextView tendiadiemds, vitrids;
        ImageView hinhdiadiemds,xoa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DanhsachAdapter.ViewHoder hoder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            hoder = new DanhsachAdapter.ViewHoder();
            hoder.tendiadiemds = view.findViewById(R.id.txttendiadiemds);
            hoder.vitrids = view.findViewById(R.id.txtvitrids);
            hoder.hinhdiadiemds = view.findViewById(R.id.imghinhds);
            hoder.xoa=view.findViewById(R.id.imgxoa);
            view.setTag(hoder);
        } else {
            hoder = (DanhsachAdapter.ViewHoder) view.getTag();
        }
        Danhsach danhsach = danhsachList.get(i);
        hoder.tendiadiemds.setText(danhsach.getTendiadiem());
        hoder.vitrids.setText(danhsach.getVitri());
        byte[] hinh = danhsach.getHinh();
        hoder.hinhdiadiemds.setImageBitmap(BitmapUtils.getImage(hinh));
        hoder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.xoadanhsach(danhsach.getTendiadiem(),danhsach.getMadiadiem());
            }
        });
        return view;
    }
}
