package com.example.fatemeh_d.stack;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Secondactivity extends AppCompatActivity {

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String text = intent.getStringExtra("title");
        int score = intent.getIntExtra("score1" , 12);
        final String link = intent.getStringExtra("link1");
        String image = intent.getStringExtra("image");
        String genre = intent.getStringExtra("genre");
        TextView namet = (TextView) findViewById(R.id.name);
        TextView textt = (TextView) findViewById(R.id.soal);
        TextView scoret = (TextView) findViewById(R.id.score);
        Button linkt = (Button) findViewById(R.id.link);
        CircleImageView imaget = (CircleImageView) findViewById(R.id.image2);
        TextView genret = (TextView) findViewById(R.id.tag);
        namet.setText("asker name : " + name);
       String s = String.valueOf(score);
        textt.setText(text);
        scoret.setText("Score is " + s);
       genret.setText(genre);
        linkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2 = new Intent(Intent.ACTION_VIEW) ;
                it2.setData(Uri.parse(link)) ;
                startActivity(it2);
            }

        });


        Picasso.with(this).load(image).error(R.drawable.error).placeholder(R.drawable.loading).into(imaget);
    }
}
