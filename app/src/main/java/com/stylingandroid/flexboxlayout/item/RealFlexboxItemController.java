package com.stylingandroid.flexboxlayout.item;

import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout.LayoutParams;

public class RealFlexboxItemController implements FlexboxItemController {
    private final TextView view;
    private final LayoutParams layoutParams;

    public static RealFlexboxItemController newInstance(TextView view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            throw new RuntimeException("View must be hosted within a FlexboxLayout");
        }
        return new RealFlexboxItemController(view, (LayoutParams) layoutParams);
    }

    public RealFlexboxItemController(TextView view, LayoutParams layoutParams) {
        this.view = view;
        this.layoutParams = layoutParams;
    }

    @Override
    public String getText() {
        return view.getText().toString();
    }

    @Override
    public void setText(String text) {
        view.setText(text);
    }

    @Override
    public float getFlexGrow() {
        return layoutParams.flexGrow;
    }

    @Override
    public void setFlexGrow(float flexGrow) {
        layoutParams.flexGrow = flexGrow;
        view.requestLayout();
    }

    @Override
    public float getFlexShrink() {
        return layoutParams.flexShrink;
    }

    @Override
    public void setFlexShrink(float flexShrink) {
        layoutParams.flexShrink = flexShrink;
        view.requestLayout();
    }

    @Override
    public int getAlignSelf() {
        return layoutParams.alignSelf;
    }

    @Override
    public void setAlignSelf(int alignSelf) {
        layoutParams.alignSelf = alignSelf;
        view.requestLayout();
    }

    @Override
    public float getFlexBasisPercent() {
        return layoutParams.flexBasisPercent;
    }

    @Override
    public void setFlexBasisPercent(float flexBasisPercent) {
        layoutParams.flexBasisPercent = flexBasisPercent;
        view.requestLayout();
    }
}
