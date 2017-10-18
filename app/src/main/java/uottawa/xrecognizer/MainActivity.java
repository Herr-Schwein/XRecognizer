package uottawa.xrecognizer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import java.util.ArrayList;

import uottawa.commonBean.Faces;
import uottawa.core.CreateUserDialog;
import uottawa.core.FacesDB;
import uottawa.core.FacesListAdapter;

public class MainActivity extends AppCompatActivity {

    ImageView imageView = null;
    Canvas canvas = null;
    Paint paint = new Paint();
    FacesDB facesDB = null;
    CreateUserDialog createUserDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnIdentify = (Button)findViewById(R.id.btnIdentify);
        Button btnNewFace = (Button)findViewById(R.id.btnNewFace);
        Button btnListFace = (Button)findViewById(R.id.btnListFace);
        imageView = (ImageView)findViewById(R.id.imageView);
        facesDB = new FacesDB(getApplicationContext());

        btnIdentify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

            }
        });

        btnNewFace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

        btnListFace.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ArrayList<Faces> facesRecords = facesDB.selectAll();
                updatelistview(facesRecords);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        switch(requestCode){
            //face identify
            case 0:
                identifyFaceAction(data);
                break;
            //new face
            case 1:
                ArrayList<Faces> facesRecords = recordNewFaceAction(data);
                addUserName(facesRecords);
        }

        Toast.makeText(MainActivity.this,"test!!!!",Toast.LENGTH_LONG).show();
    }

    private void identifyFaceAction(Intent data) {
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        Bitmap tmpBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.RGB_565);
        canvas = new Canvas(bitmap);
        canvas.drawBitmap(bitmap,0,0,null);

        FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                .setTrackingEnabled(false)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .setMode(FaceDetector.ACCURATE_MODE)
                .build();
        if(!faceDetector.isOperational()){
            Toast.makeText(MainActivity.this,"Wrong!",Toast.LENGTH_LONG).show();
            return;
        }

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Face> sparseArray = faceDetector.detect(frame);

        for(int i = 0; i < sparseArray.size(); i++){
            Face face = sparseArray.valueAt(i);
            detectLandmarks(face);
            drawRecOnFaceView(face);
        }

        imageView.setImageBitmap(bitmap);

    }

    private ArrayList<Faces> recordNewFaceAction(Intent data) {
        ArrayList<Faces> facesList = new ArrayList<>();
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        final Bitmap tmpBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.RGB_565);
        canvas = new Canvas(bitmap);
        canvas.drawBitmap(bitmap,0,0,null);

        FaceDetector faceDetector = new FaceDetector.Builder(getApplicationContext())
                .setTrackingEnabled(false)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .setMode(FaceDetector.ACCURATE_MODE)
                .build();
        if(!faceDetector.isOperational()){
            Toast.makeText(MainActivity.this,"Wrong!",Toast.LENGTH_LONG).show();
            return facesList;
        }

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Face> sparseArray = faceDetector.detect(frame);

        for(int i = 0; i < sparseArray.size(); i++){
            Face face = sparseArray.valueAt(i);
            Faces faces = detectLandmarks(face);
            facesList.add(faces);
            drawRecOnFaceView(face);
        }

        imageView.setImageBitmap(bitmap);
        return facesList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addUserName(ArrayList<Faces> facesRecords){
        for(final Faces faces : facesRecords) {
            createUserDialog = new CreateUserDialog(this, new View.OnClickListener() {
                public String userName = "";
                @Override
                public void onClick(View view) {
                    if (view.getId() == R.id.bnt_create_user) {
                        userName = createUserDialog.getTextName().getText().toString().trim();
                    }
                    faces.setName(userName);
                    facesDB.insert(faces);
                    createUserDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Add user " + faces.getName() + " success!", Toast.LENGTH_LONG).show();
                }
            });
            createUserDialog.show();
        }
    }

    private Faces detectLandmarks(Face face) {
        Faces faces = new Faces();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        for(Landmark landmark : face.getLandmarks()){
            int cx = (int)(landmark.getPosition().x);
            int cy = (int)(landmark.getPosition().y);
            switch(landmark.getType()){
                case Landmark.BOTTOM_MOUTH:
                    canvas.drawCircle(cx,cy,1,paint);
                    faces.setBottomMouthX(cx);
                    faces.setBottomMouthY(cy);
                    break;
                case Landmark.LEFT_EYE:
                    canvas.drawCircle(cx,cy,1,paint);
                    faces.setLeftEyeX(cx);
                    faces.setLeftEyeY(cy);
                    break;
                case Landmark.RIGHT_EYE:
                    canvas.drawCircle(cx,cy,1,paint);
                    faces.setRightEyeX(cx);
                    faces.setRightEyeY(cy);
                    break;
                case Landmark.NOSE_BASE:
                    canvas.drawCircle(cx,cy,1,paint);
                    faces.setNoseX(cx);
                    faces.setNoseY(cy);
                    break;
                case Landmark.LEFT_MOUTH:
                    canvas.drawCircle(cx,cy,1,paint);
                    faces.setLeftMouthX(cx);
                    faces.setLeftMouthY(cy);
                    break;
                case Landmark.RIGHT_MOUTH:
                    canvas.drawCircle(cx,cy,1,paint);
                    faces.setRightMouthX(cx);
                    faces.setRightMouthY(cy);
                    break;
            }
        }
        return faces;
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

    public void updatelistview(ArrayList<Faces> facesRecords) {
        FacesListAdapter facesListAdapter = new FacesListAdapter(getApplicationContext(),facesRecords);
        ListView listView = new ListView(this);
        listView.setAdapter(facesListAdapter);
        setContentView(listView);
    }

}
