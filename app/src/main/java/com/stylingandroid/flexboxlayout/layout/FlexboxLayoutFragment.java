package com.stylingandroid.flexboxlayout.layout;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.stylingandroid.flexboxlayout.MainActivity;
import com.stylingandroid.flexboxlayout.R;

import java.util.ArrayList;
import java.util.List;

public class FlexboxLayoutFragment extends Fragment implements View.OnLongClickListener, View.OnClickListener {
    private static final String TAG = FlexboxLayoutFragment.class.getSimpleName();

    private FlexboxLayout flexboxLayout;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flexbox_layout, container);
        flexboxLayout = (FlexboxLayout) view.findViewById(R.id.flexbox_layout);
        createChild();
        createChild();
        createChild();
        return view;
    }

    public void createChild() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        TextView item = (TextView) inflater.inflate(R.layout.flexbox_item, flexboxLayout, false);
        TextView textView = (TextView) item.findViewById(android.R.id.text1);
        textView.setText(getActivity().getString(R.string.item_default, flexboxLayout.getChildCount()));
        FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) item.getLayoutParams();
        item.setLayoutParams(layoutParams);
        item.setOnLongClickListener(this);
        item.setOnClickListener(this);
        flexboxLayout.addView(item);
    }

    public void deleteSelected() {
        List<CheckedTextView> selected = new ArrayList<>();
        for (int i = 0; i < flexboxLayout.getChildCount(); i++) {
            CheckedTextView view = (CheckedTextView) flexboxLayout.getChildAt(i);
            if (view.isChecked()) {
                selected.add(view);
                mainActivity.removeSelection(view);
            }
        }
        for (TextView textView : selected) {
            flexboxLayout.removeView(textView);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mainActivity != null) {
            Log.i(TAG, "onLongClick: ");
            mainActivity.startItemSelectionMode();
            onClick(v);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (mainActivity != null && mainActivity.isSelectingItems() && v instanceof CheckedTextView) {
            CheckedTextView textView = (CheckedTextView) v;
            textView.setChecked(!textView.isChecked());
            if (textView.isChecked()) {
                mainActivity.addSelection(textView);
            } else {
                mainActivity.removeSelection(textView);
                if (noItemsSelected()) {
                    mainActivity.endItemSelectionMode();
                }
            }
        }
    }

    private boolean noItemsSelected() {
        for (int i = 0; i < flexboxLayout.getChildCount(); i++) {
            CheckedTextView child = (CheckedTextView) flexboxLayout.getChildAt(i);
            if (child.isChecked()) {
                return false;
            }
        }
        return true;
    }

    public FlexboxLayoutController getFlexboxLayoutController() {
        return new RealFlexboxLayoutController(flexboxLayout);
    }

    public void endSelectionMode() {
        for (int i = 0; i < flexboxLayout.getChildCount(); i++) {
            CheckedTextView child = (CheckedTextView) flexboxLayout.getChildAt(i);
            child.setChecked(false);
        }
    }
}
