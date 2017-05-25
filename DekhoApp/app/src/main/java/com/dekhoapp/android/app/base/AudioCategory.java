package com.dekhoapp.android.app.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AudioCategory extends AppCompatActivity {

    private static final String TAG = "AudioCategory";

    private ImageView categoryImage;
    private TextView categoryText;
    private GridView category;

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_category);

        //finding the layout variables
        category = (GridView) findViewById(R.id.category);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Kartheek/Music/Category/");
        storageReference = FirebaseStorage.getInstance().getReference();

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                R.layout.grid_item,
                databaseReference
        ) {
            @Override
            protected void populateView(View v, String model, int position) {
                categoryImage = (ImageView) v.findViewById(R.id.category_img);
                categoryText = (TextView) v.findViewById(R.id.category_txt);

                //setting the category text value
                String filePathParsed = (model.split("/"))[1];
                final String categoryName = (filePathParsed.charAt(0)+"").toUpperCase() + filePathParsed.substring(1,filePathParsed.length()-4).toLowerCase();
                categoryText.setText(categoryName);

//                Log.e(TAG,model);
//                // Load from StorageReference mImageRef into ImageView mImageView
//                Glide.with(getApplicationContext())
//                        .using(new FirebaseImageLoader())
//                        .load(storageReference.child(model))
//                        .into(categoryImage);
                //setting the category image
//                storageReference.child(model).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        //loading the image using glide
//                        Log.e(TAG,uri.toString());
////                        if(uri != null) {
////                            GlideUtil.loadImage(uri.toString(), categoryImage);
////                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG,"failed to load the category image");
//                    }
//                });
                GlideUtil.loadImage(storageReference.child(model).getDownloadUrl().toString(),categoryImage);
            }
        };
        category.setAdapter(firebaseListAdapter);
    }
}
