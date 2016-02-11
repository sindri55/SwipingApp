package com.example.swipingapp.extensions;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    // region Properties

    private static Context mContext;

    // endregion

    // region Override functions

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    // endregion

    // region Public functions

    public static Context getContext(){
        return mContext;
    }

    // endregion
}
