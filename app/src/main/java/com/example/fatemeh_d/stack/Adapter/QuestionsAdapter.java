package com.example.fatemeh_d.stack.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.fatemeh_d.stack.Model.Question;
import com.example.fatemeh_d.stack.R;
import com.example.fatemeh_d.stack.Secondactivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuestionsAdapter extends  RecyclerView.Adapter<QuestionsAdapter.QuestionVeiwHolder>
{
    List<Question> question ;
    Context mcontex ;

    public QuestionsAdapter(List<Question> question, Context mcontex) {
        this.question = question;
        this.mcontex = mcontex;
    }

    @Override
    public QuestionVeiwHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_list_row,parent, false);
        return new QuestionVeiwHolder(view);

    }
    @Override
    public void onBindViewHolder(final QuestionVeiwHolder holder, final int position) {
        String prolink=question.get(position).getProImLink();
        Picasso.with(mcontex).load(prolink).error(R.drawable.error).placeholder(R.drawable.loading).into(holder.pro);
        if(question.get(position).isAnswered()==true)
        {
            holder.isanswer.setImageResource(R.drawable.ok2);
        }
        else if(question.get(position).isAnswered()==false)
        {
            holder.isanswer.setImageResource(R.drawable.no);
        }
        holder.ques.setText(question.get(position).getQuestion());
        holder.genre.setText(question.get(position).getGenre1());
        holder.tbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mcontex , Secondactivity.class);
                it.putExtra("name" , question.get(position).getText());
                it.putExtra("title" , question.get(position).getQuestion());
                it.putExtra("image" , question.get(position).getProImLink());
                it.putExtra("score1" , question.get(position).getScore());
                it.putExtra("link1" , question.get(position).getLink());
                it.putExtra("genre" , question.get(position).getGenre1());
               mcontex.startActivity(it);

            }
        });
    }

    @Override
    public int getItemCount() {
        return question.size();
    }

    public class QuestionVeiwHolder extends RecyclerView.ViewHolder
    {
        TextView ques ;
        TextView genre ;
        ImageView isanswer ;
        public TableLayout tbl ;
        CircleImageView pro ;
        public QuestionVeiwHolder(View itemView) {
            super(itemView);
            ques =(TextView) itemView.findViewById(R.id.qu);
            genre = (TextView) itemView.findViewById(R.id.genre);
            isanswer = (ImageView) itemView.findViewById(R.id.trfa);
            pro = (CircleImageView) itemView.findViewById(R.id.pro);
            tbl = (TableLayout) itemView.findViewById(R.id.lisro);
        }
    }
    public void removeItem(int position) {
        question.remove(position);
        notifyItemRemoved(position);
    }
}