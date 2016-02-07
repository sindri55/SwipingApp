package com.example.swipingapp.fragments.userAccount;

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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.swipingapp.R;

public class UserFragment extends Fragment {

    // region Constants

    public static final String TAG = UserFragment.class.getSimpleName();

    // endregion

    // region Properties

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    // endregion

    // region UI references

    private View view;
    private Button mButton;
    private LinearLayout profileBtn, settingsBtn, bankBtn;

    // endregion

    // region Constructors

    // endregion

    // region Override functions

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_user, container, false);


        // create bitmap from resource
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.sindri);

        //binding
        profileBtn = (LinearLayout) view.findViewById(R.id.profileBtn);
        settingsBtn = (LinearLayout) view.findViewById(R.id.settingsBtn);
        bankBtn = (LinearLayout) view.findViewById(R.id.bankBtn);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();


        // Set clicklistener
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
                fragmentTransaction.replace(R.id.fragment_container, new ProfileFragment());
                fragmentTransaction.commit();
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
                fragmentTransaction.replace(R.id.fragment_container, new SettingsFragment());
                fragmentTransaction.commit();
            }
        });

        bankBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
                fragmentTransaction.replace(R.id.fragment_container, new BankInformationFragment());
                fragmentTransaction.commit();
            }
        });


        // set circle bitmap
        ImageView mImage = (ImageView) view.findViewById(R.id.image);
        mImage.setImageBitmap(getCircleBitmap(bm));

        return view;
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
