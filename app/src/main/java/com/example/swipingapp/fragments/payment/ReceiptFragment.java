package com.example.swipingapp.fragments.payment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.example.swipingapp.R;
import com.example.swipingapp.listeners.IFragmentListener;
import com.example.swipingapp.utils.FragmentUtils;

public class ReceiptFragment extends Fragment {

    // region Constants

    public static final String TAG = ReceiptFragment.class.getSimpleName();

    // endregion

    // region Properties

    private IFragmentListener mFragmentListener;
    private FragmentManager mFragmentManager;

    // endregion

    // region UI references

    private View view;

    // endregion

    // region Constructors

    // endregion

    // region Override functions

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Get arguments and stuff

        mFragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        view = inflater.inflate(R.layout.fragment_payment_receipt, container, false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IFragmentListener) {
            mFragmentListener = (IFragmentListener) context;
            mFragmentListener.setShowNavigationBackButton(false);
        } else {
            Log.e("onAttach", "context not instance of IFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentListener = null;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (FragmentUtils.sDisableFragmentAnimations) {
            Animation a = new Animation() {};
            a.setDuration(0);
            return a;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    // endregion

    // region Listeners

    // endregion
}