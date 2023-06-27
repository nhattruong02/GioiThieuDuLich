package com.example.app_gioi_thieu_du_lich.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_gioi_thieu_du_lich.BitmapUtils;
import com.example.app_gioi_thieu_du_lich.DAO.DiadiemDAO;
import com.example.app_gioi_thieu_du_lich.DAO.TinhDAO;
import com.example.app_gioi_thieu_du_lich.DTO.Danhsach;
import com.example.app_gioi_thieu_du_lich.DTO.Diadiem;
import com.example.app_gioi_thieu_du_lich.DTO.Tinh;
import com.example.app_gioi_thieu_du_lich.MainActivity;
import com.example.app_gioi_thieu_du_lich.R;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String Name_Database = "gtdulich.sqlite";
    private static final String Table_Name = "Tinh";
    private static final String Table_Name_1 = "Diadiem";
    private static final String Table_Name_2 = "Taikhoan";
    private static final String Table_Name_3 = "Danhsach";
    public Database(@Nullable Context context) {
        super(context, Name_Database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +Table_Name_2+ "(" +
                "mataikhoan INTEGER PRIMARY KEY AUTOINCREMENT," +
                "taikhoan TEXT, " +
                "matkhau TEXT," +
                "hoten TEXT," +
                "gioitinh TEXT,"+
                "namsinh DATE," +
                "sdt TEXT )");
        db.execSQL("CREATE TABLE " +Table_Name + "(" +
                "matinh INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tentinh TEXT ," +
                "hinhtinh BLOB)");
        db.execSQL("CREATE TABLE " +Table_Name_1 + "(" +
                "madiadiem INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tendiadiem TEXT," +
                "vitri TEXT," +
                "mota TEXT," +
                "hinhdiadiem BLOB," +
                "linkvideo TEXT," +
                "matinh INTEGER, FOREIGN KEY(matinh) REFERENCES "+Table_Name+" (matinh) ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE " +Table_Name_3 + "(" +
                "madiadiem INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tendiadiem TEXT," +
                "vitri TEXT," +
                "hinhdiadiem BLOB," +
                "mataikhoan INTEGER, FOREIGN KEY(mataikhoan) REFERENCES "+Table_Name_2+" (mataikhoan) ON DELETE CASCADE )");
        db.execSQL("INSERT INTO " + Table_Name_2 + " VALUES ('1','Admin','1','Admin','Nam','24/05/2002','123');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name_1);
        db.execSQL("DROP TABLE IF EXISTS " +Table_Name_2);
        db.execSQL("DROP TABLE IF EXISTS " +Table_Name_3);
    }
    public boolean insertTinh(String tentinh,byte[] hinhtinh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tentinh",tentinh);
        contentValues.put("hinhtinh",hinhtinh);
        long result =db.insert(Table_Name,null,contentValues);
        if(result == -1){
            return true;
        }else{
            return false;
        }
    }
    public boolean updateTinh(int matinh,String tentinh,byte[] hinhtinh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matinh",matinh);
        contentValues.put("tentinh",tentinh);
        contentValues.put("hinhtinh",hinhtinh);
        db.update(Table_Name,contentValues,"matinh = '"+matinh+"'",null);
        return true;
    }
    public Integer deleteTinh(int matinh){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name,"matinh='"+matinh+"'",null);
    }
    public List<Tinh> getlistTinh(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Tinh> tinhList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM Tinh",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Tinh tinh = new Tinh(cursor.getInt(0),cursor.getString(1),cursor.getBlob(2));
            tinhList.add(tinh);
            cursor.moveToNext();
        }
        return tinhList;
    }
    public void insertDiadiem(String tendiadiem,String vitri,String mota,byte[] hinhdiadiem,String linkvideo,int matinh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tendiadiem",tendiadiem);
        contentValues.put("vitri",vitri);
        contentValues.put("mota",mota);
        contentValues.put("hinhdiadiem",hinhdiadiem);
        contentValues.put("linkvideo",linkvideo);
        contentValues.put("matinh",matinh);
        db.insert(Table_Name_1,null,contentValues);
    }
    public boolean updateDiadiem(int madd,String tendd,String vitri,String mota,byte[] hinh,String link){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("madiadiem",madd);
        contentValues.put("tendiadiem",tendd);
        contentValues.put("vitri",vitri);
        contentValues.put("mota",mota);
        contentValues.put("hinhdiadiem",hinh);
        contentValues.put("linkvideo",link);
        db.update(Table_Name_1,contentValues,"madiadiem = '"+madd+"'",null);
        return true;
    }
    public Integer deleteDiadiem(int madd){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name_1,"madiadiem='"+madd+"'",null);
    }
    public List<Diadiem> getlistDiadiem(int matinh){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Diadiem> diadiemList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +Table_Name_1 + " WHERE matinh= '"+matinh+"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Diadiem diadiem = new Diadiem(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getBlob(4),cursor.getString(5),cursor.getInt(6));
            diadiemList.add(diadiem);
            cursor.moveToNext();
        }
        return diadiemList;
    }
    public boolean ktdangnhap(String tk,String mk){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM "  +Table_Name_2 + " WHERE taikhoan='"+tk+"' AND matkhau='"+mk+"'",null);
        if(cursor.getCount() != 0) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean Dangky(String taikhoan, String matkhau, String hoten, String gioitinh, String namsinh,String sdt){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("taikhoan", taikhoan);
        contentValues.put("matkhau", matkhau);
        contentValues.put("hoten", hoten);
        contentValues.put("gioitinh", gioitinh);
        contentValues.put("namsinh", namsinh);
        contentValues.put("sdt", sdt);
        long result = db.insert(Table_Name_2,null,contentValues);
        if(result == -1){
            return true;
        }else{
            return false;
        }
    }
    public boolean ktDangky(String taikhoan){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +Table_Name_2+ " WHERE taikhoan='"+taikhoan+"'",null);
        if(cursor.getCount()!=0) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean ktDiadiem(String tendiadiem){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +Table_Name_3+ " WHERE tendiadiem='"+tendiadiem+"'",null);
        if(cursor.getCount()!=0) {
            return true;
        }
        else{
            return false;
        }
    }
    public void insertDanhsach(String tendiadiem,String vitri,byte[] hinhdiadiem, int mataikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tendiadiem",tendiadiem);
        contentValues.put("vitri",vitri);
        contentValues.put("hinhdiadiem",hinhdiadiem);
        contentValues.put("mataikhoan",mataikhoan);
        db.insert(Table_Name_3,null,contentValues);
    }
    public List<Danhsach> getlistDanhsach(int mataikhoan){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Danhsach> danhsachList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +Table_Name_3 + " WHERE mataikhoan = '"+mataikhoan+"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Danhsach danhsach = new Danhsach(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3));
            danhsachList.add(danhsach);
            cursor.moveToNext();
        }
        return danhsachList;
    }
    public Integer xoadanhsach(int madiadiem){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name_3,"madiadiem='"+madiadiem+"'",null);
    }
    public int getIdtaikhoan(String taikhoan){
        int id=0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " +Table_Name_2+ " WHERE taikhoan='"+taikhoan+"'",null);
        cursor.moveToFirst();
        id = cursor.getInt(0);
        return id;
    }
}
