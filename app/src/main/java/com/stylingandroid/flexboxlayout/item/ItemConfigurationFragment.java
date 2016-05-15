package com.stylingandroid.flexboxlayout.item;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.stylingandroid.flexboxlayout.R;

public class ItemConfigurationFragment extends Fragment {

    private MultipleItemController itemController = new MultipleItemController();

    private EditText textView;
    private EditText flexGrowView;
    private EditText flexShrinkView;
    private Spinner alignSelfSpinner;
    private EditText flexBasisPercentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = inflater.getContext();
        View layout = inflater.inflate(R.layout.fragment_flexbox_item_configuration, container, false);

        setupTextView(layout);
        setupFlexGrow(layout);
        setupFlexShrink(layout);
        setupAlignSelfSpinner(context, layout);
        setupFlexBasisPercentView(layout);
        updateValues();

        return layout;
    }

    private void setupTextView(View layout) {
        textView = (EditText) layout.findViewById(R.id.item_text);
        textView.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                itemController.setText(s.toString());
            }
        });
    }

    private void setupFlexGrow(View layout) {
        flexGrowView = (EditText) layout.findViewById(R.id.item_flex_grow);
        flexGrowView.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                float value = 0f;
                if (editable.length() > 0) {
                    if (editable.charAt(0) == '.') {
                        editable.insert(0, "0");
                        return;
                    }
                    value = Float.parseFloat(editable.toString());
                }
                itemController.setFlexGrow(value);
            }
        });
    }

    private void setupFlexShrink(View layout) {
        flexShrinkView = (EditText) layout.findViewById(R.id.item_flex_shrink);
        flexShrinkView.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                float value = 1f;
                if (editable.length() > 0) {
                    if (editable.charAt(0) == '.') {
                        editable.insert(0, "0");
                        return;
                    }
                    value = Float.parseFloat(editable.toString());
                }
                itemController.setFlexShrink(value);
            }
        });
    }

    private void setupAlignSelfSpinner(Context context, View layout) {
        alignSelfSpinner = (Spinner) layout.findViewById(R.id.select_align_self);
        alignSelfSpinner.setAdapter(getAdapter(context, R.array.array_align_self));
        alignSelfSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemController.setAlignSelf(position - 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                itemController.setAlignSelf(-1);
            }
        });
    }

    private void setupFlexBasisPercentView(View layout) {
        flexBasisPercentView = (EditText) layout.findViewById(R.id.item_flex_basis_percent);
        flexBasisPercentView.addTextChangedListener(new DefaultTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                float value = -1f;
                if (editable.length() > 0) {
                    if (editable.charAt(0) == '.') {
                        editable.insert(0, "0");
                        return;
                    }
                    value = Float.parseFloat(editable.toString());
                }
                itemController.setFlexBasisPercent(value);
            }
        });
    }

    private void updateValues() {
        if (textView != null) {
            textView.setText(itemController.getText());
        }
        if (flexGrowView != null) {
            String text = Float.toString(itemController.getFlexGrow());
            flexGrowView.setText(text);
        }
        if (flexShrinkView != null) {
            String text = Float.toString(itemController.getFlexShrink());
            flexShrinkView.setText(text);
        }
        if (alignSelfSpinner != null) {
            alignSelfSpinner.setSelection(itemController.getAlignSelf() + 1);
        }
        if (flexBasisPercentView != null) {
            float value = itemController.getFlexBasisPercent();
            if (value < 0f) {
                flexBasisPercentView.setText("");
            } else {
                String text = Float.toString(value);
                flexBasisPercentView.setText(text);
            }
        }
    }

    public void addSelection(TextView view) {
        itemController.addView(view);
        updateValues();
    }

    public void removeSelection(TextView view) {
        itemController.removeView(view);
    }

    public void endSelection() {
        itemController.removeAll();
    }

    private SpinnerAdapter getAdapter(Context context, @ArrayRes int resId) {
        return ArrayAdapter.createFromResource(context, resId, android.R.layout.simple_spinner_dropdown_item);
    }

    private class DefaultTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // NO-OP
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // NO-OP
        }

        @Override
        public void afterTextChanged(Editable s) {
            // NO-OP
        }
    }
}
