package com.example.jcproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jcproject.bean.BoardBean;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

public class BoardDetailActivity extends AppCompatActivity {

    private ImageView imgView;
    private TextView txtTitle, txtContents;
    public static BoardBean boardBean;
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;
    private String mPhotoPath;
    private Uri photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        mDatabase = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        boardBean = (BoardBean) getIntent().getSerializableExtra(BoardBean.class.getName());

        txtContents = findViewById(R.id.txtContents);
        txtTitle = findViewById(R.id.txtTitle);


        txtTitle.setText(boardBean.title);
        txtContents.setText(boardBean.contents);
        imgView = findViewById(R.id.imgView);

        // Reference to an image file in Cloud Storage
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://cjproject-65b24.appspot.com/");

        //생성된 FirebaseStorage를 참조하는 storage 생성
        StorageReference storageRef = storage.getReference();

        storageRef.child("images/"+boardBean.imgName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
                try {

                    Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    imgView.setImageBitmap(bm);

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

               /* mPhotoPath = getPath(uri);
                Bitmap bitmap = BitmapFactory.decodeFile(mPhotoPath);
                //Bitmap rotatedBmp = roate(resizedBmp, exifDegree);
                imgView.setImageBitmap(bitmap);  */


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });



    }


      public String getPath(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri,proj,null,null,null);
        cursor.moveToNext();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
        Uri uri1 = Uri.fromFile(new File(path));
        cursor.close();
        return path;
    }


    private void sendPicture(String imgFilePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(imgFilePath);
        //Bitmap rotatedBmp = roate(resizedBmp, exifDegree);
        imgView.setImageBitmap(bitmap);
    }
    private int exifOrientToDegree(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap roate(Bitmap bmp, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(),
                matrix, true);
    }
    public static Bitmap getResizedBitmap(Bitmap srcBmp, int size, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = size;
        Bitmap resized = Bitmap.createScaledBitmap(srcBmp, width, height, true);
        return resized;
    }




}
