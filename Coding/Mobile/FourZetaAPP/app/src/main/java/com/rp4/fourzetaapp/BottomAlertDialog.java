package com.rp4.fourzetaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BottomAlertDialog extends BottomSheetDialogFragment{

    private Button btnOk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_alert, container, false);

        btnOk = v.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnOk:
                        Intent it = new Intent(v.getContext(), MainActivity.class);
                        startActivity(it);
                        break;
                }
            }
        });

        return v;
        //        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
