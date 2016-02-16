package com.example.swipingapp.fragments.payment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.swipingapp.DTOs.payment.PaymentDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.adapters.ReceiptItemListAdapter;
import com.example.swipingapp.fragments.base.BaseFragment;
import com.example.swipingapp.services.cart.CartService;
import com.example.swipingapp.services.cart.ICartService;

public class CheckoutFragment extends BaseFragment {

    // region Constants

    public static final String TAG = CheckoutFragment.class.getSimpleName();

    // endregion

    // region Properties

    private ICartService mCartService;

    // endregion

    // region UI references

    private PaymentDTO mPaymentDto;
    private ListView mItemsListView;
    private Button mPaymentButton;
    private TextView mAmountTotalText;

    // endregion

    // region Override functions

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCartService = CartService.getInstance();
        mPaymentDto = mCartService.getCartItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_payment_checkout, container, false);

        mItemsListView = (ListView) view.findViewById(R.id.list_items);
        mPaymentButton = (Button) view.findViewById(R.id.btn_payment);
        mAmountTotalText = (TextView) view.findViewById(R.id.txt_amount_total);

        mAmountTotalText.setText(mPaymentDto.getFormattedAmount());
        mPaymentButton.setOnClickListener(new PaymentButtonClickListener());
        mItemsListView.setAdapter(new ReceiptItemListAdapter(getActivity().getApplicationContext(), mPaymentDto.items, mPaymentDto.currency));

        return view;
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_payment_checkout_title);
    }

    // endregion

    // region Listeners

    private class PaymentButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO: Fix so that only the main content slides, not the step indicators, attach them to the header layout?
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_right);
            fragmentTransaction.replace(R.id.fragment_container, PaymentFragment.newInstance(mPaymentDto, TAG), PaymentFragment.TAG);
            fragmentTransaction.addToBackStack(PaymentFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    // endregion
}
