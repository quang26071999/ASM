package com.example.asm.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asm.R;
import com.example.asm.activity.LoginScreenActivity;
import com.example.asm.controller.SharedPreferencesUtils;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {
    Button logout;
    TextView tv1,tv2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        logout = view.findViewById(R.id.logout_button);
        tv1 = view.findViewById(R.id.username_textview);
        tv2 = view.findViewById(R.id.password_textview);
        tv1.setText(SharedPreferencesUtils.getName(view.getContext()));
        tv2.setText(SharedPreferencesUtils.getUrl(view.getContext()));
        Log.d("111111111111111", "onCreateView: "+SharedPreferencesUtils.getName(view.getContext()));
        Log.d("111111111111111", "onCreateView: "+SharedPreferencesUtils.getUrl(view.getContext()));
        logout.setOnClickListener(view1 -> {
            SharedPreferencesUtils.clearUserData(getContext());
            Intent intent = new Intent(getActivity(), LoginScreenActivity.class);
            startActivity(intent);
            getActivity().finish();
        });

        return view;
    }
}