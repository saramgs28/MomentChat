package com.saram.momentchat;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.IOException;

public class MainVideo extends AppCompatActivity implements SurfaceHolder.Callback{

    /**
     * Variables globales para administrar la grabaci—n y reproducci—n
     */
    private MediaRecorder mediaRecorder = null;
    private MediaPlayer mediaPlayer = null;

    /**
     * Variable que define el nombre para el archivo donde escribiremos el video a grabar
     */
    private String fileName = null;

    /**
     * Variable que indica cuando se est‡ grabado
     */
    private boolean recording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_video);

        playVideo();

    }
    public void playVideo() {
        /**
         * inicializamos la variable para el nombre del archivo
         */
        fileName = Environment.getExternalStorageDirectory() + "/test.mp4";

        /**
         * inicializamos la "superficie" donde se reproducir‡ la vista previa de la grabaci—n
         * y luego el video ya grabado
         */
        SurfaceView surface = (SurfaceView)findViewById(R.id.surface);
        SurfaceHolder holder = surface.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        /**
         * inicializamos los botones sobre los que vamos a trabajar su evento de click
         */
        final Button btnRec = (Button)findViewById(R.id.btnRec);
        final Button btnStop = (Button)findViewById(R.id.btnStop);
        final Button btnPlay = (Button)findViewById(R.id.btnPlay);

        /**
         * Bot—n para grabar
         */
        btnRec.setOnClickListener(new OnClickListener() {
            @Override

            public void onClick(View v) {
                /**
                 * Al iniciar grabaci—n deshabilitamos los botones de grabar y reproducir y
                 * habilitamos el de detener
                 */
                btnRec.setEnabled(false);
                btnStop.setEnabled(true);
                btnPlay.setEnabled(false);

                /**
                 * Llamamos el mŽtodo que configura el media recorder y le decimos el archivo de salida
                 */
                prepareRecorder();
                mediaRecorder.setOutputFile(fileName);
                try {
                    /**
                     * Una vez configurado todo llamamos al mŽtodo prepare que deja todo listo
                     * para iniciar la grabaci—n
                     */
                    mediaRecorder.prepare();
                } catch (IllegalStateException e) {
                } catch (IOException e) {
                }
                /**
                 * Iniciamos la grabaci—n y actualizamos el estatus de la variable recording
                 */
                mediaRecorder.start();
                recording = true;
            }
        });

        /**
         * Bot—n para detener
         */
        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Al iniciar detener habilitamos los botones de grabar y reproducir y
                 * deshabilitamos el de detener
                 */
                btnRec.setEnabled(true);
                btnStop.setEnabled(false);
                btnPlay.setEnabled(true);

                /**
                 * Si se est‡ grabando, detenemos la grabaci—n y reiniciamos la configuraci—n
                 * adem‡s de volver falsa la variable de estatus de grabaci—n
                 */
                if (recording) {
                    recording = false;
                    mediaRecorder.stop();
                    mediaRecorder.reset();
                    /**
                     * Si se est‡ reproduciendo, detenemos la reproducci—n y reiniciamos la configuraci—n
                     */
                } else if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            }
        });

        /**
         * Bot—n para reproducir
         */
        btnPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Al iniciar la reproducci—n deshabilitamos los botones de grabar y reproducir y
                 * habilitamos el de detener
                 */
                btnRec.setEnabled(false);
                btnStop.setEnabled(true);
                btnPlay.setEnabled(false);

                /**
                 * Al concluir la reproducci—n habilitamos los botones de grabar y reproducir y
                 * deshabilitamos el de detener
                 */
                mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        btnRec.setEnabled(true);
                        btnStop.setEnabled(false);
                        btnPlay.setEnabled(true);
                    }
                });

                /**
                 * Configuramos el archivo a partir del cual se reproducir‡, preparamos el Media Player
                 *  e iniciamos la reproducci—n
                 */
                try {
                    mediaPlayer.setDataSource(fileName);
                    mediaPlayer.prepare();
                } catch (IllegalStateException e) {
                } catch (IOException e) {
                }

                mediaPlayer.start();

            }
        });
    }
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }

    /**
     * Inicializamos los recursos asociados a las variables para administrar la grabaci—n y reproducci—n.
     * Se verifica si las variables son nulas (para ejecutar este c—digo solo una vez) y luego de
     * inicializarlas se coloca el SurfaceHolder como display para la vista previa de la grabaci—n y
     * para la vista de la reproducci—n
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mediaRecorder == null) {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setPreviewDisplay(holder.getSurface());
        }

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(holder);
        }
    }

    /**
     * Liberamos los recursos asociados a las variables para administrar la grabaci—n y reproducci—n
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        mediaRecorder.release();
        mediaPlayer.release();
    }


    /**
     * MŽtodo para preparar la grabaci—n, configurando los atributos de la fuente para audio y video,
     * el formado y el codificador.
     */
    public void prepareRecorder(){
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
    }
}
