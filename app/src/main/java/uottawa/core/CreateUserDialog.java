package uottawa.core;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import uottawa.xrecognizer.R;

/**
 * Created by ZDH on 10/17/17.
 */

public class CreateUserDialog extends Dialog {

    private Activity context;
    private EditText textName;
    private Button bnt_save;
    private View.OnClickListener clickListener;

    public CreateUserDialog(Activity context, View.OnClickListener clickListener){
        super(context);
        this.context = context;
        this.clickListener = clickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.create_user_dialog);

        textName = (EditText) findViewById(R.id.text_name);
        Window dialogWindow = this.getWindow();

        WindowManager wm = context.getWindowManager();
        Display display = wm.getDefaultDisplay();
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.height = (int) (display.getHeight() * 0.4);
        params.width = (int) (display.getWidth() * 0.8);
        dialogWindow.setAttributes(params);

        bnt_save = findViewById(R.id.bnt_create_user);

        bnt_save.setOnClickListener(clickListener);

        this.setCancelable(true);
    }



    public EditText getTextName() {
        return textName;
    }
}
