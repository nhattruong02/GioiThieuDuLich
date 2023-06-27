package com.example.app_gioi_thieu_du_lich.Adapter;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_gioi_thieu_du_lich.BitmapUtils;
import com.example.app_gioi_thieu_du_lich.DTO.Tinh;
import com.example.app_gioi_thieu_du_lich.R;

import java.util.ArrayList;
import java.util.List;

public class TinhAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Tinh> tinhList;

    public TinhAdapter(Context context, int layout, List<Tinh> tinhList) {
        this.context = context;
        this.layout = layout;
        this.tinhList = tinhList;
    }
    public void filterList(ArrayList<Tinh> filterlist) {
        tinhList = filterlist;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return tinhList.size();
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
        TextView tentinh;
        ImageView hinhtinh;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TinhAdapter.ViewHoder hoder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(layout,null);
            hoder = new TinhAdapter.ViewHoder();
            hoder.tentinh = view.findViewById(R.id.tentinh);
            hoder.hinhtinh = view.findViewById(R.id.hinhtinh);
            view.setTag(hoder);
        }else{
            hoder = (ViewHoder) view.getTag();
        }
        Tinh tinh = tinhList.get(i);
        hoder.tentinh.setText(tinh.getTentinh());
        byte[] hinh = tinh.getHinh();
        hoder.hinhtinh.setImageBitmap(BitmapUtils.getImage(hinh));
        return view;
    }
}
