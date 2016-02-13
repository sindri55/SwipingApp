package com.example.swipingapp.fragments.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.swipingapp.DTOs.ReceiptDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.fragments.base.BaseFragment;

public class ReceiptFragment extends BaseFragment {

    // region Constants

    public static final String TAG = ReceiptFragment.class.getSimpleName();
    private static final String ARG_PAYMENT_DTO = "paymentDto";

    // endregion

    // region Properties
    private ListView mItemsListview;

    private ReceiptDTO mReceiptDto;

    // endregion

    // region UI references

    // endregion

    // region Constructors

    public ReceiptFragment() {  }

    public static ReceiptFragment newInstance(ReceiptDTO receiptDTO) {
        ReceiptFragment fragment = new ReceiptFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_PAYMENT_DTO, receiptDTO);

        fragment.setArguments(args);
        return fragment;
    }

    // endregion

    // region Override functions

    public static String [] items = {
        "Pylsa", "Buxur", "Nammi", "Sófi", "Kynlíf", "Sumardekk"
    };

    public static String [] amounts = {
        "$1.11", "$5.99", "$0.99", "$299.59", "$59.99", "59.29"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mReceiptDto = getArguments().getParcelable(ARG_PAYMENT_DTO);
        } else {
            // TODO: Handle more elegantly
            mReceiptDto = new ReceiptDTO(123, "Unknown");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_payment_receipt, container, false);

        // Hide the back button
        if(mFragmentListener != null) {
            mFragmentListener.setShowNavigationBackButton(false);
        }

        mItemsListview = (ListView) view.findViewById(R.id.payment_receipt_listview);
        mItemsListview.setAdapter(new customAdapterForReceiptFragment(getActivity().getApplicationContext(), items, amounts));

        return view;
    }

    @Override
    public String getTitle() {
        return getString(R.string.fragment_payment_receipt_title);
    }

    // endregion

    // region Listeners

    // endregion
}