package com.example.whoami;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.whoami.service.FaceDetectorService;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A placeholder fragment containing a simple view.
 */
public class MemorizeActivityFragment extends Fragment {

    private Bitmap bitmap = null;
    private Canvas canvas = null;
    private Paint paint = new Paint();
    private final float RIDUS = 2;
    private String name = "";
    private Frame frame = null;
    private int left_eye_x=0;
    private int left_eye_y=0;
    private int right_eye_x=0;
    private int right_eye_y=0;

    FaceDetectorService faceDetectorService;

    public MemorizeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_memorize, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT) ) {
            String selectImage = intent.getStringExtra(Intent.EXTRA_TEXT);
            InputStream imageStream = null;
            Uri uri = Uri.parse(selectImage);

            try {
                imageStream = getActivity().getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inMutable=true;
            Rect rect = new Rect(-1,-1,-1,-1);
            bitmap = BitmapFactory.decodeStream(imageStream,rect,opt);
            canvas = new Canvas(bitmap);
            canvas.drawBitmap(bitmap,0,0,null);

        } else if(intent != null && intent.hasExtra("Bitmap")) {
            bitmap = intent.getParcelableExtra("Bitmap");
            canvas = new Canvas(bitmap);
            canvas.drawBitmap(bitmap,0,0,null);
        }

        frame = new Frame.Builder().setBitmap(bitmap).build();
        this.faceDetectorService = new FaceDetectorService(getActivity(), paint, canvas);

        imageView.setImageBitmap(bitmap);

        Button memorize = rootView.findViewById(R.id.memorize);
        memorize.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Please input the name:");
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_TEXT );
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = input.getText().toString();
                        faceDetectorService.saveNewFace(name, frame);
                        Toast.makeText(getActivity(), "Add " + name + " successfully!", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        return rootView;
    }


    private void drawRecOnFaceView(Face face) {
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        float left = (float)(face.getPosition().x);
        float top = (float)(face.getPosition().y);
        float right = (float)(left + face.getWidth());
        float bottom = (float)(top + face.getHeight());
        canvas.drawRect(left,top,right,bottom,paint);
    }
}
