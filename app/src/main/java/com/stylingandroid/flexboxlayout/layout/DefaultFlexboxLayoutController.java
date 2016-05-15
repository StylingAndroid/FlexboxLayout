package com.stylingandroid.flexboxlayout.layout;

import com.google.android.flexbox.FlexboxLayout;

public class DefaultFlexboxLayoutController implements FlexboxLayoutController {
    @Override
    public int getFlexDirection() {
        return FlexboxLayout.FLEX_DIRECTION_ROW;
    }

    @Override
    public void setFlexDirection(int flexDirection) {

    }

    @Override
    public int getFlexWrap() {
        return FlexboxLayout.FLEX_WRAP_NOWRAP;
    }

    @Override
    public void setFlexWrap(int flexWrap) {

    }

    @Override
    public int getJustifyContent() {
        return FlexboxLayout.JUSTIFY_CONTENT_FLEX_START;
    }

    @Override
    public void setJustifyContent(int justifyContent) {

    }

    @Override
    public int getAlignItems() {
        return FlexboxLayout.ALIGN_ITEMS_STRETCH;
    }

    @Override
    public void setAlignItems(int alignItems) {

    }

    @Override
    public int getAlignContent() {
        return FlexboxLayout.ALIGN_CONTENT_STRETCH;
    }

    @Override
    public void setAlignContent(int alignContent) {

    }
}
