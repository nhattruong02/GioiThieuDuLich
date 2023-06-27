package com.example.app_gioi_thieu_du_lich;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class BitmapUtils {
    public static byte[] getBytes(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);
        return stream.toByteArray();
    }
    public static Bitmap getImage(byte[] image){
        return BitmapFactory.decodeByteArray(image,0,image.length);
    }
}
