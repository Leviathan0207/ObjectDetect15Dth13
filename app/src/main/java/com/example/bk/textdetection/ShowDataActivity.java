package com.example.bk.textdetection;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bk.textdetection.Interface.IItemClickListener;
import com.example.bk.textdetection.Model.BienSoXe;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class ShowDataActivity extends AppCompatActivity {
    TextView txtData;
    RecyclerView recyclerView;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<BienSoXe> options;
    FirebaseRecyclerAdapter<BienSoXe, MyRecycleViewHolder> adapter;
    BienSoXe selectedBien;
    String selectedKey;
    Intent getMainActivityData;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("BIKE SCANNING");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                displayBienSo();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStop() {
        if (adapter != null)
            adapter.stopListening();
        super.onStop();
    }


    public String getDataFromMain() {
        getMainActivityData = getIntent();
        String getKey = getMainActivityData.getStringExtra("sendStringToShowDataActivity");
        return getKey;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btn_setting) {
            Intent intentMoveSetting = new Intent(ShowDataActivity.this, AdminActivity.class);
            startActivity(intentMoveSetting);
        }
        return true;
    }

    public void displayBienSo() {
        key = getDataFromMain();
        Log.d("testMessage", key);
        Query query = databaseReference.orderByChild("id").equalTo(key);
        options = new FirebaseRecyclerOptions.Builder<BienSoXe>()
                .setQuery(query, BienSoXe.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<BienSoXe, MyRecycleViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyRecycleViewHolder holder, int position, @NonNull final BienSoXe model) {
                holder.txt_id.setText("Mã số" + "\t" + model.getID());
                holder.txt_hoten.setText("Họ và tên:" + "\t" + model.getHoTen());
                holder.txt_diachi.setText("Địa chỉ" + "\t" + model.getDiaChi());
                holder.txt_ngaysinh.setText("Ngày sinh" + "\t" + model.getNgaySinh());
                holder.txt_ngaydangky.setText("Ngày đăng ký" + "\t" + model.getNgayDangKy());
                holder.txt_quequan.setText("Quê quán" + "\t" + model.getQueQuan());
                holder.txt_cmnd.setText("CMT" + "\t" + model.getSoCMND());
            }

            @NonNull
            @Override
            public MyRecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View itemView = LayoutInflater.from(getBaseContext()).inflate(R.layout.post_item, viewGroup, false);
                return new MyRecycleViewHolder(itemView);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}
