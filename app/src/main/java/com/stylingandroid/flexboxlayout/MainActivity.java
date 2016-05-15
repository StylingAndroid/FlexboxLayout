package com.stylingandroid.flexboxlayout;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.stylingandroid.flexboxlayout.item.ItemConfigurationFragment;
import com.stylingandroid.flexboxlayout.layout.FlexboxLayoutController;
import com.stylingandroid.flexboxlayout.layout.FlexboxLayoutFragment;
import com.stylingandroid.flexboxlayout.layout.LayoutConfigurationFragment;

public class MainActivity extends AppCompatActivity {

    private FlexboxLayoutFragment flexboxLayoutFragment;
    private ItemSelectionModeCallback itemSelectionMode;

    private View container;
    private ItemConfigurationFragment itemConfigurationFragment;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        flexboxLayoutFragment = (FlexboxLayoutFragment) fragmentManager.findFragmentById(R.id.fragment_flexbox_layout);

        if (findViewById(R.id.configuration) != null) {
            setLayoutConfiguration();
        }
    }

    private void setLayoutConfiguration() {
        FragmentManager fragmentManager = getFragmentManager();
        FlexboxLayoutController controller = flexboxLayoutFragment.getFlexboxLayoutController();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        LayoutConfigurationFragment fragment = new LayoutConfigurationFragment();
        fragment.setController(controller);
        transaction.replace(R.id.configuration, fragment);
        transaction.commit();
    }

    private void setItemConfiguration() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        itemConfigurationFragment = new ItemConfigurationFragment();
        transaction.replace(R.id.configuration, itemConfigurationFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            flexboxLayoutFragment.createChild();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void confirmDelete() {
        snackbar = Snackbar.make(container, R.string.confirm_delete, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.yes, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemConfigurationFragment != null) {
                            flexboxLayoutFragment.deleteSelected();
                            itemSelectionMode.cancel();
                            dismissSnackbar();
                        }
                    }
                })
                .show();
    }

    private void dismissSnackbar() {
        if (snackbar != null) {
            snackbar.dismiss();
            snackbar = null;
        }
    }

    public void startItemSelectionMode() {
        itemSelectionMode = new ItemSelectionModeCallback();
        startActionMode(itemSelectionMode);
    }

    public void addSelection(TextView view) {
        itemConfigurationFragment.addSelection(view);
    }

    public void removeSelection(TextView view) {
        itemConfigurationFragment.removeSelection(view);
    }

    public void endItemSelectionMode() {
        if (itemSelectionMode != null) {
            itemSelectionMode.cancel();
        }
    }

    private void cleanupItemSelectionMode() {
        itemConfigurationFragment.endSelection();
        itemSelectionMode = null;
        flexboxLayoutFragment.endSelectionMode();
        if (snackbar != null) {
            dismissSnackbar();
        }
    }

    public boolean isSelectingItems() {
        return itemSelectionMode != null;
    }

    private class ItemSelectionModeCallback implements ActionMode.Callback {
        private ActionMode actionMode;

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            actionMode = mode;
            setItemConfiguration();
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.selection, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.menu_delete && itemConfigurationFragment != null) {
                confirmDelete();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            cleanupItemSelectionMode();
            setLayoutConfiguration();
            actionMode = null;
        }

        public void cancel() {
            if (actionMode != null) {
                actionMode.finish();
            }
        }
    }
}
