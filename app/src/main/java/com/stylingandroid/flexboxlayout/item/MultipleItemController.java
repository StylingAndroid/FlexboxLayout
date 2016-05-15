package com.stylingandroid.flexboxlayout.item;

import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MultipleItemController implements FlexboxItemController {
    private static final String EMPTY_STRING = "";
    private static final float DEFAULT_FLEX_GROW = 0f;
    private static final float DEFAULT_FLEX_SHRINK = 1f;
    private static final int DEFAULT_ALIGN_SELF = -1;
    private static final int DEFAULT_FLAX_BASIS_PERCENT = -1;

    private Map<View, FlexboxItemController> controllers = new HashMap<>();

    public void addView(TextView view) {
        RealFlexboxItemController itemController = RealFlexboxItemController.newInstance(view);
        if (!controllers.isEmpty()) {
            itemController.setFlexGrow(getFlexGrow());
        }
        controllers.put(view, itemController);
    }

    public void removeView(TextView view) {
        controllers.remove(view);
    }

    @Override
    public String getText() {
        if (!controllers.isEmpty()) {
            return firstItemController().getText();
        }
        return EMPTY_STRING;
    }

    @Override
    public void setText(String text) {
        for (FlexboxItemController controller : controllers.values()) {
            controller.setText(text);
        }
    }

    @Override
    public float getFlexGrow() {
        if (!controllers.isEmpty()) {
            return firstItemController().getFlexGrow();
        }
        return DEFAULT_FLEX_GROW;
    }

    @Override
    public void setFlexGrow(float flexGrow) {
        for (FlexboxItemController controller : controllers.values()) {
            controller.setFlexGrow(flexGrow);
        }
    }

    @Override
    public float getFlexShrink() {
        if (!controllers.isEmpty()) {
            return firstItemController().getFlexShrink();
        }
        return DEFAULT_FLEX_SHRINK;
    }

    @Override
    public void setFlexShrink(float flexShrink) {
        for (FlexboxItemController controller : controllers.values()) {
            controller.setFlexShrink(flexShrink);
        }
    }

    @Override
    public int getAlignSelf() {
        if (!controllers.isEmpty()) {
            return firstItemController().getAlignSelf();
        }
        return DEFAULT_ALIGN_SELF;
    }

    @Override
    public void setAlignSelf(int alignSelf) {
        for (FlexboxItemController controller : controllers.values()) {
            controller.setAlignSelf(alignSelf);
        }
    }

    @Override
    public float getFlexBasisPercent() {
        if (!controllers.isEmpty()) {
            return firstItemController().getFlexBasisPercent();
        }
        return DEFAULT_FLAX_BASIS_PERCENT;
    }

    @Override
    public void setFlexBasisPercent(float flexBasisPercent) {
        for (FlexboxItemController controller : controllers.values()) {
            controller.setFlexBasisPercent(flexBasisPercent);
        }
    }

    private FlexboxItemController firstItemController() {
        return controllers.values().iterator().next();
    }

    public void removeAll() {
        controllers.clear();
    }
}
