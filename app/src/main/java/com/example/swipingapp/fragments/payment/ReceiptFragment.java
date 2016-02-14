package com.example.swipingapp.fragments.payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.swipingapp.DTOs.payment.ReceiptDTO;
import com.example.swipingapp.R;
import com.example.swipingapp.adapters.ReceiptItemListAdapter;
import com.example.swipingapp.fragments.base.BaseFragment;

import java.text.NumberFormat;

public class ReceiptFragment extends BaseFragment {

    // region Constants

    public static final String TAG = ReceiptFragment.class.getSimpleName();
    private static final String ARG_RECEIPT_DTO = ReceiptDTO.class.getSimpleName();

    // endregion

    // region Properties

    private ReceiptDTO mReceiptDto;

    // endregion

    // region UI references

    private TextView mTransactionIdText;
    private TextView mOrderDateText;
    private TextView mAmountText;
    private TextView mAmountTotalText;
    private ListView mItemsListView;

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

        NumberFormat formatter = mReceiptDto.paymentDto.currency.getFormatter();

        mTransactionIdText = (TextView) view.findViewById(R.id.txt_transaction_Id);
        mOrderDateText = (TextView) view.findViewById(R.id.txt_order_date);
        mAmountText = (TextView) view.findViewById(R.id.txt_amount);
        mAmountTotalText = (TextView) view.findViewById(R.id.txt_amount_total);
        mItemsListView = (ListView) view.findViewById(R.id.list_items);

        mTransactionIdText.setText(mReceiptDto.paymentConfirmedDto.orderId);
        mOrderDateText.setText(mReceiptDto.paymentConfirmedDto.orderDate);
        mAmountText.setText(formatter.format(mReceiptDto.paymentConfirmedDto.amount));
        mAmountTotalText.setText(formatter.format(mReceiptDto.paymentDto.amount));

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