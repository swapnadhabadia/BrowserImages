package com.example.browseimages;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.browseimages.utils.DialogUtil;
import com.example.browseimages.view.ImageListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_query)
    EditText edit_query1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        edit_query1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(edit_query1.getText().toString().trim().length() > 0)
                    {
                        DialogUtil.showOrHideKeyboard(MainActivity.this, edit_query1, false);
                        searchQuery(edit_query1.getText().toString());
                    }

                    return true;
                }
                return false;
            }
        });



    }

    private void searchQuery(String s) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, ImageListFragment.newInstance(s), ImageListFragment.TAG).addToBackStack(ImageListFragment.TAG).commit();


    }
}
