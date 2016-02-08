package com.example.swipingapp.fragments.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.example.swipingapp.listeners.IFragmentListener;
import com.example.swipingapp.utils.FragmentUtils;

public abstract class BaseFragment extends Fragment {

    // region Properties

    protected IFragmentListener mFragmentListener;
    protected FragmentManager mFragmentManager;

    // endregion

    // region Override functions

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(mFragmentListener != null) {
            mFragmentListener.setNavigationTitle(getTitle());
        }

        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IFragmentListener) {
            mFragmentListener = (IFragmentListener) context;
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

    // region Abstract functions

    public abstract String getTitle();

    // endregion
}
