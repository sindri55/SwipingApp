package com.example.swipingapp.fragments.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.swipingapp.DTOs.payment.ReceiptDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.adapters.ReceiptItemListAdapter;
import com.example.swipingapp.fragments.base.BaseFragment;

public class ReceiptFragment extends BaseFragment {

    // region Constants

    public static final String TAG = ReceiptFragment.class.getSimpleName();
    private static final String ARG_RECEIPT_DTO = ReceiptDTO.class.getSimpleName();

    // endregion

    // region Properties
    private ListView mItemsListView;

    private ReceiptDTO mReceiptDto;

    // endregion

    // region UI references

    // endregion

    // region Constructors

    public ReceiptFragment() {  }

    public static ReceiptFragment newInstance(ReceiptDTO receiptDTO) {
        ReceiptFragment fragment = new ReceiptFragment();
        Bundle args = new Bundle();

        args.putParcelable(ARG_RECEIPT_DTO, receiptDTO);

        fragment.setArguments(args);
        return fragment;
    }

    // endregion

    // region Override functions

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mReceiptDto = getArguments().getParcelable(ARG_RECEIPT_DTO);
        } else {
            // TODO: Handle more elegantly
            throw new RuntimeException("ReceiptFragment: Unable to get arguments");
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

        mItemsListView = (ListView) view.findViewById(R.id.payment_receipt_listview);
        mItemsListView.setAdapter(new ReceiptItemListAdapter(getActivity().getApplicationContext(), mReceiptDto.getItems(), mReceiptDto.getCurrency()));

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