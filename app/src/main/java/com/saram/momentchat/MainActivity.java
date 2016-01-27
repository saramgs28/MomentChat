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

public class MainActivity extends AppCompatActivity{ //implements OnSeekBarChangeListener {

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
       // playVideo();
    }

    public void init(){
        img = (ImageView) findViewById(R.id.imageview1);
      //  video = (VideoView) findViewById(R.id.video);
    }

    /* public void playVideo(){
        video.setVideoPath("http://techslides.com/demos/sample-videos/small.mp4");
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.requestFocus();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                video.start();
            }
        });
        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    VideoViewActivity.this);
            mediacontroller.setAnchorView(videoview);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }
*/
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
    //UNA SOLA IMAGENNNNNNNNNNNNNNN
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
//VIDEOOOOOOOOOOOOOOO
   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=(ImageView)findViewById(R.id.imageview1); //Para sobreescribir la imagen que viene por defecto.R me lleva a los recursos
        video=(VideoView)findViewById(R.id.video);
        //initialize our variable.  Seekbar para habilitar swipe
        seekBar = (SeekBar) findViewById(R.id.seekbar1);
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                //se tendria que desvanecer la imagen de fondo
                if (seekBar.getProgress() == seekBar.getMax()) {
                    //tienes que encender la camara
                    //Toast.makeText(getApplicationContext(), "CAMARA Y ACCION", Toast.LENGTH_SHORT).show();
                    i= new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //se crea un intent que hace referencia a una actividad de android camara
                    startActivityForResult(i, cons); //inicializa la actividad natural de la camara. La cons es porque me lo pide el metodo
                }
            }

            //para tener acceso a la informacion de la camara. metodo que hay que sobreescribir
            @Override
            protected void OnActivityResult(int requestCode, int resultCode, Intent data) {
                //para no alterar lo que ya esta escrito
                //super.OnActivityResult(requestCode, resultCode, data);
                if (resultCode == Activity.RESULT_OK) {
                    Bundle ext = data.getExtras();
                    //capturamos la foto. obtenemos la info de la imagen y la guarda en un bitmap
                    bmp =(Bitmap)ext.get("data");
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
}*/
