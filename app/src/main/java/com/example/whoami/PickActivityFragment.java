package com.example.whoami;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


/**
 * A placeholder fragment containing a simple view.
 */
public class PickActivityFragment extends Fragment {

    private static final int REQUEST_IMAGE_GET = 1;
    private static final int REQUEST_PICKTURE_TAKE = 2;
    private Intent intent = null;



    public PickActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pick, container, false);
        intent = getActivity().getIntent();
        Button camera = (Button) rootView.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // take a photo
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_PICKTURE_TAKE);
            }
        });
        Button gallery = (Button) rootView.findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_GET);
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK ) {
            Uri selectedImage = data.getData();
            if (intent.getStringExtra("M").equals("ME")) { // Memorize
                Intent intent = new Intent(getActivity(), MemorizeActivity.class).putExtra(Intent.EXTRA_TEXT, selectedImage.toString());
                startActivity(intent);
            }
            else if (intent.getStringExtra("M").equals("RE")) { // Recognize
                Intent intent = new Intent(getActivity(), RecognizeActivity.class).putExtra(Intent.EXTRA_TEXT, selectedImage.toString());
                startActivity(intent);
            }
        } else if (requestCode == REQUEST_PICKTURE_TAKE && resultCode == RESULT_OK ) {
            if (intent.getStringExtra("M").equals("ME")) { // Memorize
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                Intent intent2 = new Intent(getActivity(), MemorizeActivity.class).putExtra("Bitmap",bitmap);
                startActivity(intent2);
            }
            else if (intent.getStringExtra("M").equals("RE")) { // Recognize
                Intent intent2 = new Intent(getActivity(), RecognizeActivity.class).putExtras(data);
                startActivity(intent2);
            }
        }
    }
}
