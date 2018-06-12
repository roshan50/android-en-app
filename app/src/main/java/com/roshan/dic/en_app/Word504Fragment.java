package com.roshan.dic.en_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Word504Fragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterRecy504 adapterRecy504;

    public Word504Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word504, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recy504);
//        recyclerView.setHasFixedSize(true);
        adapterRecy504 = new AdapterRecy504(getActivity(),postData());
        recyclerView.setAdapter(adapterRecy504);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private List<InfoRecy> postData(){
        List<InfoRecy> data =  new ArrayList<>();
        String wordEn[] = {"color","fruit","animal","body","cloth","face"};
        String wordFa[] = {"رنگ","میوه","حیوان","اعضای بدن","لباس","حالت صورت"};

        for (int i=0; i<wordEn.length; i++){
            InfoRecy infoRecy = new InfoRecy();
            infoRecy.word50En = wordEn[i];
            infoRecy.word50Fa = wordFa[i];
            data.add(infoRecy);
        }
        return data;
    }

}
