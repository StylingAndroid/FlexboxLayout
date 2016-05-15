package com.stylingandroid.flexboxlayout.layout;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.stylingandroid.flexboxlayout.R;

public class LayoutConfigurationFragment extends Fragment {
    private static final FlexboxLayoutController DEFAULT_CONTROLLER = new DefaultFlexboxLayoutController();

    private FlexboxLayoutController flexboxLayoutController = DEFAULT_CONTROLLER;
    private Spinner flexDirectionSpinner;
    private Spinner flexWrapSpinner;
    private Spinner justifyContentSpinner;
    private Spinner alignItemsSpinner;
    private Spinner alignContentSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = inflater.getContext();
        View layout = inflater.inflate(R.layout.fragment_flexbox_layout_configuration, container, false);

        setupFlexDirectionSpinner(context, layout);
        setupFlexWrapSpinner(context, layout);
        setupJustifyContentSpinner(context, layout);
        setupAlignItemsSpinner(context, layout);
        setupAlignContentSpinner(context, layout);
        updateSpinners();

        return layout;
    }

    private void setupFlexDirectionSpinner(Context context, View layout) {
        flexDirectionSpinner = (Spinner) layout.findViewById(R.id.select_flex_direction);
        flexDirectionSpinner.setAdapter(getAdapter(context, R.array.array_flex_direction));
        flexDirectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flexboxLayoutController.setFlexDirection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                flexboxLayoutController.setFlexDirection(0);
            }
        });
    }

    private void setupFlexWrapSpinner(Context context, View layout) {
        flexWrapSpinner = (Spinner) layout.findViewById(R.id.select_flex_wrap);
        flexWrapSpinner.setAdapter(getAdapter(context, R.array.array_flex_wrap));
        flexWrapSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flexboxLayoutController.setFlexWrap(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                flexboxLayoutController.setFlexWrap(0);
            }
        });
    }

    private void setupJustifyContentSpinner(Context context, View layout) {
        justifyContentSpinner = (Spinner) layout.findViewById(R.id.select_justify_content);
        justifyContentSpinner.setAdapter(getAdapter(context, R.array.array_justify_content));
        justifyContentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flexboxLayoutController.setJustifyContent(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                flexboxLayoutController.setJustifyContent(0);
            }
        });
    }

    private void setupAlignItemsSpinner(Context context, View layout) {
        alignItemsSpinner = (Spinner) layout.findViewById(R.id.select_align_items);
        alignItemsSpinner.setAdapter(getAdapter(context, R.array.array_align_items));
        alignItemsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flexboxLayoutController.setAlignItems(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                flexboxLayoutController.setAlignItems(0);
            }
        });
    }

    private void setupAlignContentSpinner(Context context, View layout) {
        alignContentSpinner = (Spinner) layout.findViewById(R.id.select_align_content);
        alignContentSpinner.setAdapter(getAdapter(context, R.array.array_align_content));
        alignContentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                flexboxLayoutController.setAlignContent(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                flexboxLayoutController.setAlignContent(0);
            }
        });
    }

    private SpinnerAdapter getAdapter(Context context, @ArrayRes int resId) {
        return ArrayAdapter.createFromResource(context, resId, android.R.layout.simple_spinner_dropdown_item);
    }

    public void setController(@NonNull FlexboxLayoutController controller) {
        this.flexboxLayoutController = controller;
        updateSpinners();
    }

    private void updateSpinners() {
        if (flexDirectionSpinner != null) {
            flexDirectionSpinner.setSelection(flexboxLayoutController.getFlexDirection());
        }
        if (flexWrapSpinner != null) {
            flexWrapSpinner.setSelection(flexboxLayoutController.getFlexWrap());
        }
        if (justifyContentSpinner != null) {
            justifyContentSpinner.setSelection(flexboxLayoutController.getJustifyContent());
        }
        if (alignItemsSpinner != null) {
            alignItemsSpinner.setSelection(flexboxLayoutController.getAlignItems());
        }
        if (alignContentSpinner != null) {
            alignContentSpinner.setSelection(flexboxLayoutController.getAlignContent());
        }
    }
}
