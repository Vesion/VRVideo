package com.xiangxu.vrvideo.viewer.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.xiangxu.vrvideo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment
        implements View.OnFocusChangeListener {

    @BindView(R.id.user_name_edit) EditText mUserName;
    @BindView(R.id.user_password_edit) EditText mUserPassword;
    @BindView(R.id.user_name_underline) View mNameLine;
    @BindView(R.id.user_password_underline) View mPasswordLine;
    @BindView(R.id.login_button) TextView mLoginButton;

    InputMethodManager mImm;

    public LoginFragment() { }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        // under line change
        mUserName.setOnFocusChangeListener(this);
        mUserPassword.setOnFocusChangeListener(this);

        // keyboard
        mImm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        // login button
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserName.requestFocus();
        mImm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    @Override
    public void onPause() {
        super.onPause();
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            mImm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    void initUnderLines() {
        mNameLine.setBackgroundColor(getResources().getColor(R.color.line_primary));
        mPasswordLine.setBackgroundColor(getResources().getColor(R.color.line_primary));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        initUnderLines();
        if (hasFocus) {
            switch (v.getId()) {
                case R.id.user_name_edit:
                    mNameLine.setBackgroundColor(getResources().getColor(R.color.button_primary));
                    break;
                case R.id.user_password_edit:
                    mPasswordLine.setBackgroundColor(getResources().getColor(R.color.button_primary));
                    break;
            }
        }
    }
}
