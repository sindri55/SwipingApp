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
    private ReceiptItemListAdapter mReceiptItemListAdapter;

    // endregion

    // region UI references

    private PaymentDTO mPaymentDto;
    private ListView mItemsListView;
    private TextView mAmountTotalText;

    private Button mPaymentButton;
    private Button mClearCartButton;

    // endregion

    // region Private functions

    private void updateView() {
        if(mReceiptItemListAdapter != null) {
            mReceiptItemListAdapter.setItems(mPaymentDto.items);
        }

        mAmountTotalText.setText(mPaymentDto.getFormattedAmount());
    }

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
        mAmountTotalText = (TextView) view.findViewById(R.id.txt_amount_total);
        mPaymentButton = (Button) view.findViewById(R.id.btn_payment);
        mClearCartButton = (Button) view.findViewById(R.id.btn_clear_cart);

        mPaymentButton.setOnClickListener(new PaymentButtonClickListener());
        mClearCartButton.setOnClickListener(new ClearCartButtonClickListener());

        mReceiptItemListAdapter = new ReceiptItemListAdapter(getActivity().getApplicationContext(), mPaymentDto.items, mPaymentDto.currency);
        mAmountTotalText.setText(mPaymentDto.getFormattedAmount());
        mItemsListView.setAdapter(mReceiptItemListAdapter);

        return view;
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_payment_checkout_title);
    }

    // endregion

    // region Listeners

    private class ClearCartButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            mCartService.clearCart();
            mPaymentDto = mCartService.getCartItems();

            updateView();
        }
    }

    private class PaymentButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            if(mPaymentDto.items.size() > 0) {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.fragment_container, PaymentFragment.newInstance(mPaymentDto, TAG), PaymentFragment.TAG);
                fragmentTransaction.addToBackStack(PaymentFragment.TAG);
                fragmentTransaction.commit();
            }
        }
    }

    // endregion
}
