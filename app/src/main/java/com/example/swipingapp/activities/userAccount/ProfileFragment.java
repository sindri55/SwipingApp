package com.example.swipingapp.activities.userAccount;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.swipingapp.R;

/**
 * Created by Sindri on 26/01/16.
 */
public class ProfileFragment extends Fragment {

    // Properties
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    // UI references
    private View view;
    private ImageView backBtn;
    // Constructors


    // Override functions
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        backBtn = (ImageView) view.findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.main_container, new UserFragment());
                fragmentTransaction.commit();
            }
        });

        return view;

    }
}