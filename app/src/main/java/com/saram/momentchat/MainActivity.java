package com.saram.momentchat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity{

    private SeekBar seekBar;
    Intent i; //Porque se abrira una actividad
    final static int cons=0;
    Bitmap bmp;
    ImageView img;
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img=(ImageView)findViewById(R.id.imageview_1); //Para sobreescribir la imagen que viene por defecto.R me lleva a los recursos
        video=(VideoView)findViewById(R.id.video);
        //String urlpath = "android.resource://"+getPackageName()+"/"+R.raw.video//Para decir donde esta el video. Esta en la carpeta raw
        //initialize our variable.  Seekbar para habilitar swipe
        seekBar = (SeekBar) findViewById(R.id.seekbar1);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                //se tendria que desvanecer la imagen de fondo
                if (seekBar.getProgress()==seekBar.getMax())
                {
                    //tienes que encender la camara
                    //Toast.makeText(getApplicationContext(), "CAMARA Y ACCION", Toast.LENGTH_SHORT).show();
                    i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //se crea un intent que hace referencia a una actividad de android camara
                    startActivityForResult(i, cons); //inicializa la actividad natural de la camara. La cons es porque me lo pide el metodo
                }
            }

            //para tener acceso a la informacion de la camara. metodo que hay que sobreescribir
            // @Override
            protected void OnActivityResult(int requestCode, int resultCode, Intent data)
            {
                //para no alterar lo que ya esta escrito
                //super.OnActivityResult(requestCode, resultCode, data);
                if(resultCode== Activity.RESULT_OK){
                    Bundle ext=data.getExtras();
                    //capturamos la foto. obtenemos la info de la imagen y la guarda en un bitmap
                    bmp=(Bitmap)ext.get("data");
                    //ahora ponemos la imagen en el imageview creado accediendo a ello
                    img.setImageBitmap(bmp);
                    seekBar.setProgress(0); //inicializamos la barra otra vez
                }

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //cuando tocas tienes que ver la foto por 3 segundos
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //cuando sueltas tienes que dejar de ver la imagen otra vez
            }

        });
    }
}
