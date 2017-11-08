package com.example.whoami;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.whoami.commonBean.FaceBean;
import com.example.whoami.service.FacesDBService;

import java.util.ArrayList;


public class MainFragment extends Fragment {


    FacesDBService facesDBService;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button memorize = (Button) rootView.findViewById(R.id.memorize);
        memorize.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PickActivity.class).putExtra("M", "ME");
                startActivity(intent);
            }
        });

        Button recognize = (Button) rootView.findViewById(R.id.recognize);
        recognize.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PickActivity.class).putExtra("M", "RE");
                startActivity(intent);
            }
        });

        Button dataManager = (Button) rootView.findViewById(R.id.browse);
        dataManager.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                facesDBService = new FacesDBService(getActivity());
                ArrayList<FaceBean> faceBeanRecords = facesDBService.selectAll();
                final String[] items = new String[faceBeanRecords.size()];
                final Boolean[] initChoiceSets = new Boolean[faceBeanRecords.size()];

                for(int i = 0; i < items.length; i++){
                    items[i] = faceBeanRecords.get(i).getName();
                }

                AlertDialog.Builder showListDialog = new AlertDialog.Builder(getActivity());
                final ArrayList<String> yourChoices = new ArrayList<>();
                showListDialog.setTitle("I know these faces: ");
                showListDialog.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            yourChoices.add(items[which]);
                        }else {
                            yourChoices.remove(items[which]);
                        }

                    }
                });
                showListDialog.setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(yourChoices.size()<=0){
                                    Toast.makeText(getActivity(), "Please select at least one face!", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                for(String name : yourChoices){
                                    facesDBService.delete(name);
                                }
                                Toast.makeText(getActivity(), "Delete successfully!", Toast.LENGTH_LONG).show();
                            }
                        });
                showListDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                showListDialog.show();
            }
        });

        return rootView;
    }
}
