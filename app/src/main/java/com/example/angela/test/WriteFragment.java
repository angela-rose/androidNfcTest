package com.example.angela.test;

import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class WriteFragment extends Fragment {
    Button btnCheck;
    Button btnWrite;
    Button btnRead;
    View fragmentView;
    NfcUtils nfcUtils = new NfcUtils();
    MainActivity activity;
    private final String[][] techList = new String[][]{
            new String[]{
                    NfcA.class.getName(),
                    NfcB.class.getName(),
                    NfcF.class.getName(),
                    NfcV.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
                    MifareUltralight.class.getName(), Ndef.class.getName()
            }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment2.
     */
    public static WriteFragment newInstance() {
        return new WriteFragment();
    }

    public WriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_write, container, false);
        activity = (MainActivity) getActivity();
        // Inflate the layout for this fragment
        final DbAccess dbAccess = new DbAccess(activity.getBaseContext());

        btnCheck = (Button) fragmentView.findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(nfcUtils.writeTag(getActivity(), activity.tag, activity.currData, activity.location.name))
                if (dbAccess.insertTag(activity.uid)) {
                    activity.showToast(true);
                } else {
                    activity.showToast(false);
                }
            }
        });


        btnRead = (Button) fragmentView.findViewById(R.id.btnRead);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(nfcUtils.writeTag(getActivity(), activity.tag, activity.currData, activity.location.name))
               ArrayList<String> al = nfcUtils.readTag(activity.tag, activity.ntnt);
                if (!al.isEmpty()) {
                    activity.createDialog(activity, "Tag Contents", al.get(0), null).show();
                } else {
                    activity.showToast(false);
                }
            }
        });


        btnWrite = (Button) fragmentView.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nfcUtils.writeTag(activity, activity.tag, activity.currData, activity.location.name)) {
                    activity.showToast(true);
                } else {
                    activity.showToast(false);
                }
            }
        });
        return fragmentView;


    }

}
