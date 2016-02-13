package com.example.swipingapp.fragments.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.swipingapp.R;
import com.example.swipingapp.fragments.base.BaseFragment;

public class ProfileFragment extends BaseFragment {

    // region Constants

    public static final String TAG = ProfileFragment.class.getSimpleName();

    // region Properties

    // endregion

    // region UI references

    private LinearLayout mProfileSettingsButton;
    private LinearLayout mBankInformationButton;
    private LinearLayout mVerifiedButton;

    // endregion

    // region Override functions

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_profile_profile, container, false);

        // create bitmap from resource
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.sindri);

        //binding
        mProfileSettingsButton = (LinearLayout) view.findViewById(R.id.btn_profile_settings);
        mBankInformationButton = (LinearLayout) view.findViewById(R.id.btn_bank_information);
        mVerifiedButton = (LinearLayout) view.findViewById(R.id.btn_verification_settings);

        // TODO: Refactor
        // Set click listener
        mProfileSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = mFragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.fragment_container, new SettingsFragment(), SettingsFragment.TAG);
                ft.addToBackStack(SettingsFragment.TAG);
                ft.commit();
            }
        });

        mBankInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = mFragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.fragment_container, new BankInformationFragment(), BankInformationFragment.TAG);
                ft.addToBackStack(BankInformationFragment.TAG);
                ft.commit();
            }
        });

        mVerifiedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = mFragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_right);
                ft.replace(R.id.fragment_container, new VerificationFragment(), VerificationFragment.TAG);
                ft.addToBackStack(BankInformationFragment.TAG);
                ft.commit();
            }
        });



        // set circle bitmap
        ImageView mImage = (ImageView) view.findViewById(R.id.image_profile);
        mImage.setImageBitmap(getCircleBitmap(bm));

        return view;
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_profile_profile_title);
    }

    // endregion

    // region Private functions

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }

    // endregion
}
