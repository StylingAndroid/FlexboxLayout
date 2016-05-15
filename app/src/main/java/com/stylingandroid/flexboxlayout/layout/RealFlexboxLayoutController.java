package com.stylingandroid.flexboxlayout.layout;

import com.google.android.flexbox.FlexboxLayout;

class RealFlexboxLayoutController implements FlexboxLayoutController {
    private final FlexboxLayout flexboxLayout;

    public RealFlexboxLayoutController(FlexboxLayout flexboxLayout) {
        this.flexboxLayout = flexboxLayout;
    }

    @Override
    public int getFlexDirection() {
        return flexboxLayout.getFlexDirection();
    }

    @Override
    public void setFlexDirection(int flexDirection) {
        flexboxLayout.setFlexDirection(flexDirection);
    }

    @Override
    public int getFlexWrap() {
        return flexboxLayout.getFlexWrap();
    }

    @Override
    public void setFlexWrap(int flexWrap) {
        flexboxLayout.setFlexWrap(flexWrap);
    }

    @Override
    public int getJustifyContent() {
        return flexboxLayout.getJustifyContent();
    }

    @Override
    public void setJustifyContent(int justifyContent) {
        flexboxLayout.setJustifyContent(justifyContent);
    }

    @Override
    public int getAlignItems() {
        return flexboxLayout.getAlignItems();
    }

    @Override
    public void setAlignItems(int alignItems) {
        flexboxLayout.setAlignItems(alignItems);
    }

    @Override
    public int getAlignContent() {
        return flexboxLayout.getAlignContent();
    }

    @Override
    public void setAlignContent(int alignContent) {
        flexboxLayout.setAlignContent(alignContent);
    }
}
