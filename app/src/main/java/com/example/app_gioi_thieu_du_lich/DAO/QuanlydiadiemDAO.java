package com.example.app_gioi_thieu_du_lich.DAO;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_gioi_thieu_du_lich.Adapter.DiadiemAdapter;
import com.example.app_gioi_thieu_du_lich.BitmapUtils;
import com.example.app_gioi_thieu_du_lich.DTO.Diadiem;
import com.example.app_gioi_thieu_du_lich.Database.Database;
import com.example.app_gioi_thieu_du_lich.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuanlydiadiemDAO extends AppCompatActivity {
    ImageButton btnimageadd, btnimageupdate;
    Button btnexit, btnupdate, btnadd;
    ListView lvdiadiem;
    DiadiemAdapter adapter;
    List<Diadiem> diadiemList;
    Database database;
    int matinh;
    private int REQUEST_CODE_FOLDER = 1;
    private int REQUEST_CODE_FOLDER_1 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlydiadiem_dao);
        Anhxa();
        loadDiadiem();
        registerForContextMenu(lvdiadiem);
    }

    private void loadDiadiem() {
        Intent intent = getIntent();
        matinh = intent.getIntExtra("matinh", 0);
        diadiemList = new ArrayList<>();
        diadiemList = database.getlistDiadiem(matinh);
        adapter = new DiadiemAdapter(this, R.layout.dong_dia_diem, diadiemList);
        lvdiadiem.setAdapter(adapter);
    }

    private void Anhxa() {
        lvdiadiem = findViewById(R.id.lvdiadiem);
        database = new Database(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Dialogthemdiadiem();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_2, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo i = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = i.position;
        switch (item.getItemId()) {
            case R.id.update:
                Diadiem diadiem = diadiemList.get(index);
                int madd = diadiem.getMadiadiem();
                String tendd = diadiem.getTendiadiem();
                String vitridd = diadiem.getVitri();
                String motadd = diadiem.getMota();
                String linkvideo = diadiem.getLinkvideo();
                SuaDiadiemDialog(madd, tendd, vitridd, motadd, linkvideo);
                return true;
            case R.id.delete:
                Diadiem diadiem1 = diadiemList.get(index);
                int madd1 = diadiem1.getMadiadiem();
                String tendd1 = diadiem1.getTendiadiem();
                XoaDiadiemDialog(tendd1, madd1);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void Dialogthemdiadiem() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_dia_diem);
        btnimageadd = dialog.findViewById(R.id.btnthemhinhdd);
        EditText edttendd = dialog.findViewById(R.id.edtthemtendd);
        EditText edtvitri = dialog.findViewById(R.id.edtthemvitridd);
        EditText edtmota = dialog.findViewById(R.id.edtthemmotadd);
        EditText edtlink = dialog.findViewById(R.id.edtthemlinkdd);
        btnadd = dialog.findViewById(R.id.btnthemdd);
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
                String tendd = edttendd.getText().toString();
                String vitridd = edtvitri.getText().toString();
                String motadd = edtmota.getText().toString();
                String linkdd = edtlink.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) btnimageadd.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                byte[] hinh = BitmapUtils.getBytes(bitmap);
                database.insertDiadiem(tendd, vitridd, motadd, hinh, linkdd, matinh);
                Toast.makeText(QuanlydiadiemDAO.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                loadDiadiem();
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

    private void SuaDiadiemDialog(int madiadiem, String tendd, String vitridd, String motadd, String linkdd) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua_dia_diem);
        btnimageupdate = dialog.findViewById(R.id.btnsuahinhdd);
        EditText tendiaddiem = dialog.findViewById(R.id.edtsuatendd);
        EditText vitridiaddiem = dialog.findViewById(R.id.edtsuavitridd);
        EditText motadiaddiem = dialog.findViewById(R.id.edtsuamotadd);
        EditText linkdiaddiem = dialog.findViewById(R.id.edtsualinkdd);
        tendiaddiem.setText(tendd);
        vitridiaddiem.setText(vitridd);
        motadiaddiem.setText(motadd);
        linkdiaddiem.setText(linkdd);
        btnupdate = dialog.findViewById(R.id.btnsuadd);
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
                String tendd = tendiaddiem.getText().toString();
                String vitridd = vitridiaddiem.getText().toString();
                String motadd = motadiaddiem.getText().toString();
                String linkdd = linkdiaddiem.getText().toString();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) btnimageupdate.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                byte[] hinh = BitmapUtils.getBytes(bitmap);
                database.updateDiadiem(madiadiem, tendd, vitridd, motadd, hinh, linkdd);
                dialog.dismiss();
                loadDiadiem();
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

    private void XoaDiadiemDialog(String tendiadiem, int madiadiem) {
        AlertDialog.Builder dialogxoa = new AlertDialog.Builder(this);
        dialogxoa.setMessage("Bạn có muốn xoá địa điểm " + tendiadiem + " này không?");
        dialogxoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                database.deleteDiadiem(madiadiem);
                loadDiadiem();
            }
        });
        dialogxoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogxoa.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = (Bitmap) BitmapFactory.decodeStream(inputStream);
                btnimageadd.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == REQUEST_CODE_FOLDER_1 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = (Bitmap) BitmapFactory.decodeStream(inputStream);
                btnimageupdate.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
