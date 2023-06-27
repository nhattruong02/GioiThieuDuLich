package com.example.app_gioi_thieu_du_lich.DAO;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.app_gioi_thieu_du_lich.Adapter.TinhAdapter;
import com.example.app_gioi_thieu_du_lich.BitmapUtils;
import com.example.app_gioi_thieu_du_lich.DTO.Tinh;
import com.example.app_gioi_thieu_du_lich.Database.Database;
import com.example.app_gioi_thieu_du_lich.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuanlytinhDAO extends AppCompatActivity {
    TinhAdapter adapter;
    List<Tinh> tinhList;
    GridView grqltinh;
    Database database;
    ImageButton btnimageadd,btnimageupdate;
    Button btnadd,btnexit,btnupdate;
    int REQUEST_CODE_FOLDER = 1;
    int REQUEST_CODE_FOLDER_1 = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlytinh_dao);
        Anhxa();
        loadTinh();
        grqltinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QuanlytinhDAO.this,QuanlydiadiemDAO.class);
                intent.putExtra("matinh",tinhList.get(i).getMatinh());
                startActivity(intent);
            }
        });
        registerForContextMenu(grqltinh);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            DialogthemTinh();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_1, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = i.position;
        switch (item.getItemId()) {
            case R.id.update:
                Tinh tinh = tinhList.get(index);
                int matinh = tinh.getMatinh();
                String tentinh = tinh.getTentinh();
                DialogsuaTinh(matinh,tentinh);
                return true;
            case R.id.delete:
                Tinh tinh1 = tinhList.get(index);
                int matinhxoa = tinh1.getMatinh();
                String tentinhxoa = tinh1.getTentinh();
                DialogXoa(tentinhxoa, matinhxoa);
                return true;
        }
        return super.onContextItemSelected(item);
    }
    private void DialogthemTinh(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_tinh);
        btnimageadd = dialog.findViewById(R.id.btnthemhinhtinh);
        EditText edttentinh = dialog.findViewById(R.id.edtthemtentinh);
        btnadd = dialog.findViewById(R.id.btnthemtinh);
        btnexit = dialog.findViewById(R.id.btnthoatthem);
        btnimageadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentinh = edttentinh.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) btnimageadd.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                byte[] hinh = BitmapUtils.getBytes(bitmap);
                database.insertTinh(tentinh, hinh);
                dialog.dismiss();
                loadTinh();
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void DialogsuaTinh(int matinh,String tentinh){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua_tinh);
        btnimageupdate = dialog.findViewById(R.id.btnsuahinhtinh);
        EditText edttentinh = dialog.findViewById(R.id.edtsuatentinh);
        edttentinh.setText(tentinh);
        btnupdate = dialog.findViewById(R.id.btnsuatinh);
        btnexit = dialog.findViewById(R.id.btnthoatsua);
        btnimageupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER_1);
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentinh = edttentinh.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) btnimageupdate.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                byte[] hinh = BitmapUtils.getBytes(bitmap);
                database.updateTinh(matinh, tentinh, hinh);
                dialog.dismiss();
                loadTinh();
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void DialogXoa(String tentinh,int matinh){
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Bạn có muốn xoá tỉnh "+tentinh+" này không?");
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.deleteTinh(matinh);
                Toast.makeText(QuanlytinhDAO.this, "Đã xoá tỉnh "+tentinh, Toast.LENGTH_SHORT).show();
                loadTinh();
            }
        });
        dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }
    private  void Anhxa(){
        grqltinh = findViewById(R.id.gvqltinh);
        database = new Database(this);
    }
    private void loadTinh(){
        tinhList = new ArrayList<>();
        tinhList = database.getlistTinh();
        adapter = new TinhAdapter(this, R.layout.dong_tinh, tinhList);
        grqltinh.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = (Bitmap) BitmapFactory.decodeStream(inputStream);
                btnimageadd .setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == REQUEST_CODE_FOLDER_1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = (Bitmap) BitmapFactory.decodeStream(inputStream);
                btnimageupdate .setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}