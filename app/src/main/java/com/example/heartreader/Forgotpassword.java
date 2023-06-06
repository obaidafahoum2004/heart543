package com.example.heartreader;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Forgotpassword#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Forgotpassword extends Fragment {

    private EditText email ;
    private Button bt ;
    private ImageButton back ;
    private FirebaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Forgotpassword() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForgotPassword.
     */
    // TODO: Rename and change types and number of parameters
    public static Forgotpassword newInstance(String param1, String param2) {
        Forgotpassword fragment = new Forgotpassword();
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
        return inflater.inflate(R.layout.fragment_forgotpassword, container, false);
    }
    public void onStart()
    {
        super.onStart();
        connenctcomponents();
    }

    private void connenctcomponents() {
        back = getView().findViewById(R.id.imgbtnback);
        email = getView().findViewById(R.id.EmailForgotPassword);
        bt = getView().findViewById(R.id.btnsavepass);
        fbs = FirebaseServices.getInstance();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fbs.getAuth().sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "check your email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Wrong email ", Toast.LENGTH_SHORT).show();
                        }

                    }
                }) ;

            }
        } ) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameMain, new LoginFragment());
                ft.commit();
            }
        });
    }
}