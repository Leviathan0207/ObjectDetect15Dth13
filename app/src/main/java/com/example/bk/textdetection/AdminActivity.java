package com.example.bk.textdetection;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bk.textdetection.Model.BienSoXe;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {
    Intent getMainActivityData;
    EditText edit_id, edit_hoten, edit_diachi, edit_ngaysinh, edit_ngaydangky, edit_quequan, edit_cmnd;
    Button btn_add;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseRecyclerAdapter<BienSoXe, MyRecycleViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        edit_id = (EditText) findViewById(R.id.edit_id);
        edit_hoten = (EditText) findViewById(R.id.edit_hovaten);
        edit_diachi = (EditText) findViewById(R.id.edit_diachi);
        edit_ngaydangky = (EditText) findViewById(R.id.edit_ngaydangky);
        edit_ngaysinh = (EditText) findViewById(R.id.edit_ngaysinh);
        edit_quequan = (EditText) findViewById(R.id.edit_quequan);
        edit_cmnd = (EditText) findViewById(R.id.edit_cmnd);
        btn_add = (Button) findViewById(R.id.btn_post);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMaBienSoXe();
            }
        });
    }

    private void sendMaBienSoXe() {
        final ShowDataActivity show = new ShowDataActivity();
        String Id = edit_id.getText().toString();
        String HoTen = edit_hoten.getText().toString();
        String DiaChi = edit_diachi.getText().toString();
        String NgaySinh = edit_ngaysinh.getText().toString();
        String NgayDangKy = edit_ngaydangky.getText().toString();
        String QueQuan = edit_quequan.getText().toString();
        String CMND = edit_cmnd.getText().toString();
        BienSoXe bienSoXe = new BienSoXe(Id, HoTen, DiaChi, NgaySinh, NgayDangKy, QueQuan, CMND);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("BIKE SCANNING");
        databaseReference.push().setValue(bienSoXe);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(AdminActivity.this,"Thêm dữ liệu thành công",Toast.LENGTH_SHORT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //adapter.notifyDataSetChanged();
    }

}
