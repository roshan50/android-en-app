package com.roshan.dic.en_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.Collections;
import java.util.List;

/**
 * Created by Asus on 5/28/2018.
 */

public class AdapterRecy504 extends RecyclerView.Adapter<AdapterRecy504.ViewHolder>
{
    public Context context;
    public LayoutInflater layoutInflater;
    List<InfoRecy> data = Collections.emptyList();

    public AdapterRecy504(Context context, List<InfoRecy> data)
    {
        this.context = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.sample_504,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InfoRecy cur = data.get(position);
        holder.word504En.setText(cur.word50En);
        holder.word504Fa.setText(cur.word50Fa);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView word504En;
        private TextView word504Fa;
        private MaterialRippleLayout materialRippleLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            word504En = (TextView) itemView.findViewById(R.id.txtEn);
            word504Fa = (TextView) itemView.findViewById(R.id.txtFa);
            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.mat_504);

            materialRippleLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    switch (position)
                    {
                        case 0://color
                            Intent i = new Intent(context, Activity_word.class);
                            i.putExtra("table", "fruit");
                            context.startActivity(i);
//                            context.startActivity(new Intent(context,Activity_word.class));
                            break;
                        case 1://fruit
                            context.startActivity(new Intent(context,Activity_word.class));
                            break;
                        case 2://animal
                            context.startActivity(new Intent(context,Activity_word.class));
                            break;
                        case 3://body
                            context.startActivity(new Intent(context,Activity_word.class));
                            break;
                        case 4://cloth
                            context.startActivity(new Intent(context,Activity_word.class));
                            break;
                        case 5://face
                            context.startActivity(new Intent(context,Activity_word.class));
                            break;
                    }
                }
            });
        }
    }
}
