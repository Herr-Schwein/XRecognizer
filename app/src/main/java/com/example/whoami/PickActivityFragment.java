/*
Depeloper: XUEJING MA
DATE:2017 Fall Term, Multimedia Communication
 */
package com.example.whoami;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A placeholder fragment containing a simple view.
 */
public class PickActivityFragment extends Fragment {

    private static final int REQUEST_IMAGE_GET = 1;
    private static final int REQUEST_PICKTURE_TAKE = 2;
    private Intent intent = null;
    private String mCurrentPhotoPath;
    private Uri photoURI;


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
//                startActivityForResult(intent, REQUEST_PICKTURE_TAKE);
                File photoFile = null;
                try {
                    File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File image = File.createTempFile(
                            "image",  /* prefix */
                            ".jpg",         /* suffix */
                            storageDir      /* directory */
                    );
                    mCurrentPhotoPath = image.getAbsolutePath();
                    photoFile = image;
                } catch (IOException ex) { }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    photoURI = FileProvider.getUriForFile(getActivity(),"com.example.android.fileprovider",
                            photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, REQUEST_PICKTURE_TAKE);
                }
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
                Intent intent2 = new Intent(getActivity(), MemorizeActivity.class).putExtra(Intent.EXTRA_TEXT, selectedImage.toString());
                startActivity(intent2);
            }
            else if (intent.getStringExtra("M").equals("RE")) { // Recognize
                Intent intent2 = new Intent(getActivity(), RecognizeActivity.class).putExtra(Intent.EXTRA_TEXT, selectedImage.toString());
                startActivity(intent2);
            }
        } else if (requestCode == REQUEST_PICKTURE_TAKE && resultCode == RESULT_OK ) {
            if (intent.getStringExtra("M").equals("ME")) { // Memorize
//                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
//                Intent intent2 = new Intent(getActivity(), MemorizeActivity.class).putExtra("Bitmap",bitmap);
                Intent intent2 = new Intent(getActivity(), MemorizeActivity.class).putExtra(Intent.EXTRA_TEXT, photoURI.toString());
                startActivity(intent2);
            }
            else if (intent.getStringExtra("M").equals("RE")) { // Recognize
//                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
//                Bitmap bitmap = BitmapFactory.decodeFile(Environment.DIRECTORY_PICTURES+"/image.jpg");
//                Intent intent2 = new Intent(getActivity(), RecognizeActivity.class).putExtra("Bitmap",bitmap);
                Intent intent2 = new Intent(getActivity(), RecognizeActivity.class).putExtra(Intent.EXTRA_TEXT, photoURI.toString());
                startActivity(intent2);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

}
