package com.bloodapp.blood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class postit extends AppCompatActivity {

    private static final int GALLERY_REQUEST =2;
    private Uri uri= null;
    private ImageButton imageButton;
    private EditText editName;
    private EditText editDsec;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postit);

        editName = findViewById(R.id.editName);
        editDsec = findViewById(R.id.editDesc);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = database.getInstance().getReference().child("InstaApp");
        mAuth= FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());

    }

    public void imageButtonClicked(View v) {
        Intent galleryintent= new Intent(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("Image/*");
        startActivityForResult(galleryintent,GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode== RESULT_OK ){
            uri = data.getData();
            imageButton = findViewById(R.id.imageButton);
            imageButton.setImageURI(uri);
        }
    }

    public void postimage(View v)
    {
        final String name = editName.getText().toString().trim();
        final String desc = editDsec.getText().toString().trim();

        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(desc))
        {
             StorageReference filepath = storageReference.child("Post_Image").child(uri.getLastPathSegment());
             filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                 @Override
                 public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                     final Task<Uri> downloadurl = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                     Toast.makeText(postit.this,"Upload Complete",Toast.LENGTH_LONG).show();
                     final DatabaseReference newPost = databaseReference.push();


                     mDatabaseUsers.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             newPost.child("title").setValue(name);
                             newPost.child("desc").setValue(desc);
                             newPost.child("image").setValue(downloadurl.toString());
                             newPost.child("uid").setValue(mCurrentUser.getUid());
                             newPost.child("username").setValue(dataSnapshot.child("userName").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if (task.isSuccessful())
                                     {
                                         Intent apple = new Intent(postit.this,camp.class);
                                         startActivity(apple);
                                     }
                                 }
                             });
                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError databaseError) {

                         }
                     });

                 }
             });
        }
    }
}
