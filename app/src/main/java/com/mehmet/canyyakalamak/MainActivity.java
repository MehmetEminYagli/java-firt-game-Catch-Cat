package com.mehmet.canyyakalamak;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView ScoreText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] resimlerDizi;
    Handler handler;
    Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score =0;
        textView = findViewById(R.id.kalantime);
        ScoreText = findViewById(R.id.ScoreText);

        imageView  = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        resimlerDizi = new ImageView[] {imageView, imageView2, imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};


        ResimSakla();


                                                    //kaçtan başlasın ne kadar sürede azalsın
        new CountDownTimer(11000,1000)
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long l) {
                textView.setText("Left Time: " + l/1000);//1000 e bölerek milisaniyeyi saniyeye çeviriyoruz
            }

            @Override
            public void onFinish()
            {
                textView.setText("Time OFF");
                handler.removeCallbacks(runnable);

                //sonra bütün resimleri geri gizliyoruz ki kullanıcı bittiğini anlasın

                for (ImageView image :resimlerDizi){
                    image.setVisibility(View.INVISIBLE);
                }

                //şimdi ise alert ile oyuncuya oyun bitti diyeceğiz eğer başka leveler var ise next dedirtir yeni levele geçiririz yok ise tekrardan başlatırız

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Again");
                alert.setMessage("are you sure Restart Game?");
                alert.setPositiveButton("Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //restart

                        Intent intent = getIntent();
                        finish(); //bütün aktiviteleri bitirdik finishledik ki bir hata ile karşılaşmayalım
                        startActivity(intent); // ile de sayfamızı yeniden başlattık

                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //no
                        Toast.makeText(MainActivity.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

            }
        }.start();

    }

    public  void  ScorePlus(View view){
        score++;
        ScoreText.setText("Score :" + score);
    }

    public  void  ResimSakla(){

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run()
            {//hepsini görünmez yaptık
                for (ImageView image :resimlerDizi )
                {
                    image.setVisibility(View.INVISIBLE);
                }
                //şimdi ise rastgele bir resmi görünür yapıcaz her bilmem kaç saniyede bir
                Random random = new Random();
                int i = random.nextInt(9); //0 ile 8  arasında rastgele sayı oluşturucak YANİ BİR SAYI AŞAĞISINI SINIR KABUL EDER
                resimlerDizi[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500); //her 0,5 saniyde bir tekrarla diyoruz
            }
        };
        handler.post(runnable);


    }
}







































/*
    public  void  lose(){
        AlertDialog.Builder lose = new AlertDialog.Builder(getApplicationContext());

        lose.setTitle("Ha HA HA  LOSER");
        lose.setMessage("LOSE");

        lose.setPositiveButton("Again", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext() , "Tekrar başlatıldı",Toast.LENGTH_LONG).show();
            }
        });

        lose.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"iptal edildi",Toast.LENGTH_LONG).show();
            }
        });
        lose.show();
    }
*/