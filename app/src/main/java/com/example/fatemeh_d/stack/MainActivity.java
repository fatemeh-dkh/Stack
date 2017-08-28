package com.example.fatemeh_d.stack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fatemeh_d.stack.Adapter.QuestionsAdapter;
import com.example.fatemeh_d.stack.Model.Question;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    //*****************************************************************************************
    MyJsonResponse model;
    //**********************************************************************************************
    private List<Question> questions = new ArrayList<>();
    RecyclerView recyclerView;
    QuestionsAdapter questionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException a) {
                a.printStackTrace();
            }
            recyclerView = (RecyclerView) findViewById(R.id.recview);
            questionsAdapter = new QuestionsAdapter(questions, MainActivity.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(questionsAdapter);
            OkHttpClient client = new OkHttpClient();
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.stackexchange.com/2.2/").client(client).addConverterFactory(GsonConverterFactory.create()).build();
            APIService service = retrofit.create(APIService.class);
            Call<MyJsonResponse> serviceSlackmodels = service.readjson();
            serviceSlackmodels.enqueue(new Callback<MyJsonResponse>() {
                @Override
                public void onResponse(Call<MyJsonResponse> call, Response<MyJsonResponse> response) {
                    model = response.body();
                    int i = 0, j;
                    String genre;
                    for (i = 0; i < 10; i++) {
                        genre = "";
                        for (j = 0; j < model.items.get(i).tags.size(); j++) {
                            if (j > 0)
                                genre = genre + " , " + model.items.get(i).tags.get(j);
                            else
                                genre = genre + model.items.get(i).tags.get(j);
                        }
                        questions.add(new Question(model.items.get(i).title, model.items.get(i).owner.getProfile_image(), model.items.get(i).is_answered, genre, model.items.get(i).score, model.items.get(i).link, model.items.get(i).owner.getDisplay_name()));
                    }
                    questionsAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<MyJsonResponse> call, Throwable t) {

                }
            });
            Button btn = (Button) findViewById(R.id.search);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText et = (EditText) findViewById(R.id.editText);
                    String searchs = et.getText().toString();
                    int j, i, p = 0, q = 0, counter = 0;
                    int flag25 = 0;
                    questions.remove(0);
                    int[] array = new int[10];
                    for (i = 0; i < 10; i++) {
                        flag25=0 ;
                        for (j = 0; j < model.items.get(i).tags.size(); j++) {
                            if (searchs.equals(model.items.get(i).tags.get(j))) {
                                flag25 = 1;
                                array[counter]=i ;
                                counter++ ;
                                String genre;
                                for (p = 0; p < 10; p++) {
                                    genre = "";
                                    for (q = 0; q < model.items.get(p).tags.size(); q++) {
                                        if (q > 0)
                                            genre = genre + " , " + model.items.get(p).tags.get(q);
                                        else
                                            genre = genre + model.items.get(p).tags.get(q);
                                    }


                                }
                            }
                        }

                    }


                   for(i=0 ; i<10 ; i++)
                    {
                        boolean flag2 = false ;
                        for(j=0 ; j<counter ; j++)
                        {
                            if (array[j]==i);
                            flag2=true ;
                        }
                        if(flag2==false)
                        { ((QuestionsAdapter)recyclerView.getAdapter()).removeItem(i);}
                    }

                }

            });
        }

    }

}