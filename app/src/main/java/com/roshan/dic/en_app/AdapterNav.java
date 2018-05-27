package com.roshan.dic.en_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.Collections;
import java.util.List;

/**
 * Created by Asus on 5/22/2018.
 */

public class AdapterNav extends RecyclerView.Adapter<AdapterNav.ViewHolder>
{
    private Context context;
    private LayoutInflater inflater;
    List<InfoNav> data = Collections.emptyList();

    public AdapterNav(Context context, List<InfoNav> data)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.sample_recy_nav,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InfoNav cur = data.get(position);
        holder.title.setText(cur.title);
        holder.image.setImageResource(cur.iconId);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView image;

        MaterialRippleLayout materialRippleLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_nav);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple_nav_item);

            materialRippleLayout.setOnClickListener(new View.OnClickListener()
            {
               @Override
                public void onClick(View view){
                    int position = getAdapterPosition();
                    switch (position)
                    {
                        case 0:
                            context.startActivity(new Intent(context,Activity_setting.class));
                            break;
                        case 1:
                            context.startActivity(new Intent(context,Activity_Contact.class));
                            break;
                        case 2:
                            final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(context);
                            dialogBuilder.withTitle(context.getString(R.string.txtNavDialogAbout))
                                    .withMessage(context.getString(R.string.txtNavDialogAboutMsg))
                                    .withDuration(700)
                                    .withEffect(Effectstype.Fadein)
                                    .withButton1Text("click")
                                    .setButton1Click(new View.OnClickListener(){

                                        @Override
                                        public void onClick(View v) {
                                            dialogBuilder.cancel();
                                        }
                                    });
                            dialogBuilder.show();
                            break;
                        case 3:
                            final NiftyDialogBuilder ExitdialogBuilder = NiftyDialogBuilder.getInstance(context);
                            ExitdialogBuilder.withTitle(context.getString(R.string.txtNavDialogExit))
                                    .withMessage(context.getString(R.string.txtNavDialogExitMsg))
                                    .withDuration(700)
                                    .withEffect(Effectstype.Shake)
                                    .withButton1Text("No")
                                    .setButton1Click(new View.OnClickListener(){

                                        @Override
                                        public void onClick(View v) {
                                            ExitdialogBuilder.cancel();
                                        }
                                    })
                                    .withButton2Text("Yes")
                                    .setButton2Click(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            System.exit(0);
                                        }
                                    });
                            ExitdialogBuilder.show();
                            break;

                    }
               }
            });
        }
    }
}
