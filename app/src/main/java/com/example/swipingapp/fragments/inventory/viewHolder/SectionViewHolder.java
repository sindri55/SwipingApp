package com.example.swipingapp.fragments.inventory.viewHolder;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swipingapp.DTOs.inventory.CategoryDTO;
import com.example.swipingapp.R;

public class SectionViewHolder extends ParentViewHolder {

    // region Constants

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    // endregion

    // region Properties

    private CategoryButtonListener mCategoryButtonListener;

    // endregion

    // region UI references

    private final ImageView mArrowExpandImageView;
    private TextView mSectionTextView;
    private Button mDeleteButton;

    // endregion

    // region Constructors

    public SectionViewHolder(View itemView, CategoryButtonListener categoryButtonListener) {
        super(itemView);

        mCategoryButtonListener = categoryButtonListener;

        mSectionTextView = (TextView) itemView.findViewById(R.id.txt_category_description);
        mDeleteButton = (Button) itemView.findViewById(R.id.btn_delete);
        mArrowExpandImageView = (ImageView) itemView.findViewById(R.id.parent_list_item_expand_arrow);
    }

    // endregion

    // region Override functions

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
                mArrowExpandImageView.setRotation(ROTATED_POSITION);
            } else {
                mArrowExpandImageView.setRotation(INITIAL_POSITION);
            }
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            RotateAnimation rotateAnimation;
            if (expanded) { // rotate clockwise
                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            } else { // rotate counterclockwise
                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }

            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
            mArrowExpandImageView.startAnimation(rotateAnimation);
        }
    }

    // endregion

    // region Public functions

    public void bind(final CategoryDTO category) {
        mSectionTextView.setText(category.description);
        mDeleteButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(mCategoryButtonListener != null) {
                    mCategoryButtonListener.onDeleteCategory(category.categoryId);
                }
            }
        });
    }

    // endregion

    // region Listeners


    // endregion

    // region Interfaces

    // TODO: This needs some rethinking
    public interface CategoryButtonListener {
        void onDeleteCategory(int categoryId);
    }

    // endregion
}
