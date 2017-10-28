package uottawa.core;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import uottawa.commonBean.Faces;

/**
 * Created by dunhaozhong on 10/11/17.
 */

public class FacesListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Faces> facesList;

    public FacesListAdapter(Context mContext, ArrayList<Faces> facesList) {
        this.mContext = mContext;
        this.facesList = facesList;
    }

    @Override
    public int getCount() {
        return facesList.size();
    }

    @Override
    public Object getItem(int i) {
        return facesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return facesList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView mTextView = new TextView(mContext);
        StringBuilder sb = new StringBuilder();
        sb.append(facesList.get(i).getName()).append(",");
        sb.append(facesList.get(i).getRatioEyesAndNoseMouth()).append(",");
        sb.append(facesList.get(i).getRatioLeftEyesAndNose()).append(",");
        sb.append(facesList.get(i).getRatioLeftEyesBottomMouth()).append(",");
        sb.append(facesList.get(i).getRatioRightEyesAndNose()).append(",");
        sb.append(facesList.get(i).getRatioRightEyesBottomMouth());
        mTextView.setText(sb.toString());
        mTextView.setTextSize(18);
        return mTextView;
    }
}
