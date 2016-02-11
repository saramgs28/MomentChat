package com.saram.momentchat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{// implements SurfaceHolder.Callback{ //implements OnSeekBarChangeListener {

    private static SeekBar myseekBar;
    private static Intent i; //Porque se abrira una actividad
    final static int cons = 0;
    private static Bitmap bmp;
    private static ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        seekBar();
    }

    public void init(){
        img = (ImageView) findViewById(R.id.imageview1);
    }

    public void seekBar()
    {
        myseekBar = (SeekBar) findViewById(R.id.seekbar1);
        myseekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                        if (seekBar.getProgress() == myseekBar.getMax()) {
                            i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //Se abre la camara
                            //i= new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                            startActivityForResult(i, cons);//para recibir datos de esta actividad
                        }
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
    }
    //UNA SOLA IMAGEN
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data); //No alterar lo que esta escrito. Si no lo pongo, no hace el contenido
        ArrayList<Object> listImage = null;
        if (resultCode == Activity.RESULT_OK) {
            Bundle ext = data.getExtras();
            bmp =(Bitmap)ext.get("data"); //Guarda la info en un bitmap
            img.setImageBitmap(bmp);
            myseekBar.setProgress(0);
        }
    }
}

