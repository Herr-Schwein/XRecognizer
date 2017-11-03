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
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.whoami.service.FaceDetectorService;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * A placeholder fragment containing a simple view.
 */
public class RecognizeActivityFragment extends Fragment {

    private Bitmap bitmap = null;
    private Canvas canvas = null;
    private Frame frame = null;
    private Paint paint = new Paint();
    private final float RIDUS = 2;

    FaceDetectorService faceDetectorService;

    public RecognizeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recognize, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);

        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
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

            frame = new Frame.Builder().setBitmap(bitmap).build();
        }

        imageView.setImageBitmap(bitmap);

        this.faceDetectorService = new FaceDetectorService(getActivity(), paint, canvas);

        Button recognize = rootView.findViewById(R.id.recognize);
        recognize.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // do recogition
                String res = faceDetectorService.identifyFace(frame);

                AlertDialog.Builder showResDialog = new AlertDialog.Builder(getActivity());
                showResDialog.setTitle("I guess you are: ");
                showResDialog.setMessage(res);
                showResDialog.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {dialog.cancel();}
                        });
                showResDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {dialog.cancel();}
                        });
                showResDialog.show();

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
