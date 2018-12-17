package com.example.bk.textdetection;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CropImageActivity extends AppCompatActivity {
    ImageView imgViewCrop;
    File file;
    Uri uri;
    Intent CropIntent, CamIntent, movePageSearch, ShareIntent;
    Double requestCodeCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
        imgViewCrop = findViewById(R.id.imgViewCrop);
    }


    private void SearchTabOpen() {
        movePageSearch = new Intent(CropImageActivity.this, AdminActivity.class);
        startActivity(movePageSearch);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btnCamera)
            CameraOpen();
        else if (item.getItemId() == R.id.btnSearch)
            SearchTabOpen();
        else if (item.getItemId() == R.id.btnShare)
            Share();
        return true;
    }

    private void Share() {
        Bitmap bitmap = ((BitmapDrawable) imgViewCrop.getDrawable()).getBitmap();
        Bitmap bitmapShare = Bitmap.createBitmap(bitmap);
        FileOutputStream fileOutputStream = null;
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        if (!file.exists() && !file.mkdir())
            Toast.makeText(this, "Can't create directory to save image", Toast.LENGTH_SHORT).show();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmsshhmmss");
        String date = simpleDateFormat.format(new Date());
        String name = "Img" + date + ".jpg";
        String file_name = file.getAbsolutePath() + "/" + name;
        File new_file = new File(file_name);
        try {
            fileOutputStream = new FileOutputStream(new_file);
            Log.d("testFile", fileOutputStream.toString());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
            Toast.makeText(this, "Save image success", Toast.LENGTH_SHORT).show();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ShareIntent = new Intent(Intent.ACTION_SEND);
        ShareIntent.setType("image/jpeg");
        ShareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/" + name));
        startActivity(Intent.createChooser(ShareIntent, "Share Using:"));
    }


    private void CameraOpen() {
        CamIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory(),
                "file" + String.valueOf(System.currentTimeMillis() + ".jpg"));
        uri = Uri.fromFile(file);
        CamIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        CamIntent.putExtra("return-data", true);
        CamIntent.putExtra("request-code", 0);
        startActivityForResult(CamIntent, 0);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_crop, menu);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK)
            CropImage();
        else if (requestCode == 2) {
            if (data != null) {
                uri = data.getData();
                CropImage();
            }
        } else if (requestCode == 1) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = bundle.getParcelable("data");
            imgViewCrop.setImageBitmap(bitmap);

        }
    }

    private void CropImage() {
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");
            CropIntent.setDataAndType(uri, "image/*");
            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 180);
            CropIntent.putExtra("outputY", 180);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 4);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);
            startActivityForResult(CropIntent, 1);
        } catch (ActivityNotFoundException e) {
        }
    }
}
