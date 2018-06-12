package com.roshan.dic.en_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import android.speech.tts.TextToSpeech;

import static android.R.attr.data;

/**
 * Created by Asus on 5/28/2018.
 */

public class AdapterWord extends RecyclerView.Adapter<AdapterWord.ViewHolder>
{
    public Context context;
    private Activity activity;
    List<Info_data> items = Collections.emptyList();
    private OnTopListener onTopListener;
    public TextToSpeech tts;


    public AdapterWord(Activity activity, List<Info_data> items)
    {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_word,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Info_data cur = items.get(position);
        holder.word.setText(cur.word);

        Log.d("word",cur.word);
        int resId = holder.img.getContext().getResources().getIdentifier(cur.word, "drawable", holder.img.getContext().getPackageName());
        Log.d("resId",""+resId);
        holder.img.setImageResource(resId);

        holder.meaning.setText(cur.meaning);

        holder.sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String txt = holder.word.getText().toString();
                tts = new TextToSpeech(holder.sound.getContext(),new TextToSpeech.OnInitListener()
                {
                    @Override
                    public void onInit(int status)
                    {
                        tts.setLanguage(Locale.US);
                        tts.speak(txt,TextToSpeech.QUEUE_FLUSH,null);
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView word;
        private TextView meaning;
        private ImageView img;
        private ImageButton sound;

        public ViewHolder(View itemView) {
            super(itemView);
            word = (TextView) itemView.findViewById(R.id.txtEn);
            meaning = (TextView) itemView.findViewById(R.id.txtFa);
            img = (ImageView) itemView.findViewById(R.id.img_view_word);
            sound = (ImageButton) itemView.findViewById(R.id.img_read_word);
        }
    }

    public void setOnTopListener(OnTopListener onTopListener){
        this.onTopListener = onTopListener;
    }
}


