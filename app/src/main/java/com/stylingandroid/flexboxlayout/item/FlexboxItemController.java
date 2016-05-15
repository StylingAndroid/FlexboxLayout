package com.stylingandroid.flexboxlayout.item;

public interface FlexboxItemController {
    String getText();

    void setText(String text);

    float getFlexGrow();

    void setFlexGrow(float flexGrow);

    float getFlexShrink();

    void setFlexShrink(float flexShrink);

    int getAlignSelf();

    void setAlignSelf(int alignSelf);

    float getFlexBasisPercent();

    void setFlexBasisPercent(float flexBasisPercent);
}
