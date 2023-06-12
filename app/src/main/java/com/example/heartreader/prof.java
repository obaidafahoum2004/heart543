package com.example.heartreader;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;
import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link prof#newInstance} factory method to
 * create an instance of this fragment.
 */
public class prof extends Fragment {
    private EditText name,nickname,phone;
    private ImageView img;
    private Button btn;
    FirebaseServices fbs ;
    private final int galerry = 1000;
    private static final int PICK_IMAGE_REQUEST = 1;
    private DatabaseReference mDatabase;
    Uri imageUri;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    StorageReference storageRef = storage.getReference();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public prof() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment prof.
     */
    // TODO: Rename and change types and number of parameters
    public static prof newInstance(String param1, String param2) {
        prof fragment = new prof();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prof, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connenctcomponents();
    }

    private void connenctcomponents() {
        name=getActivity().findViewById(R.id.etusername);
        phone=getActivity().findViewById(R.id.etphone);
        img=getActivity().findViewById(R.id.userimg);
        fbs = FirebaseServices.getInstance() ;
        btn= getActivity().findViewById(R.id.BTNDone);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty() || nickname.getText().toString().isEmpty() || phone.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(), "fill everything", Toast.LENGTH_SHORT).show();
                    return;
                }
                String path = uploadImageToFirebaseStorage();
                if (path == null)
                    return;
                User user = new User(name.getText().toString() , nickname.getText().toString() , phone.getText().toString() , path);
                fbs.getFire().collection("Users").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getActivity(), "good", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "bad", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get the selected image's URI
            imageUri = data.getData();
            img.setImageURI(imageUri);
            // Do something with the imageUri, such as upload it to Firebase Storage
        }
    }

    private String uploadImageToFirebaseStorage() {
        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
        Bitmap Image = drawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();
        StorageReference ref = fbs.getStorage().getReference("listingPictures/" + UUID.randomUUID().toString());
        UploadTask uploadTask = ref.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error with the picture", e);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            }
        });
        return ref.getPath();
    }
}