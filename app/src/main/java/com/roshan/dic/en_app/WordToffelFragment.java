package com.roshan.dic.en_app;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SearchViewCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordToffelFragment extends Fragment {
    private Button playBtn;
    String[] fruitArray = {"apple","banana","chabbage","cherry",
            "eggplant","glans","kiwi","mellon","onion","orange","pomegranate","straberry","tomato"};

    public WordToffelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_word_toffel, container, false);


        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),R.layout.simple_list_item_1,fruitArray);

        ListView listView = (ListView) view.findViewById(R.id.fruit_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
            }

        });


        Button button = (Button) view.findViewById(R.id.play_btn);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();

                ImageView imageview = new ImageView(getActivity());
                LinearLayout linearlayout = (LinearLayout)getActivity().findViewById(R.id.for_game);
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 250);

                imageview.setImageResource(R.drawable.cherry);
                imageview.setLayoutParams(params);
                linearlayout.addView(imageview);

                ObjectAnimator animation = ObjectAnimator.ofFloat(imageview, "translationY", 1200f);
                animation.setDuration(5000);
                animation.start();
            }
        });


        return view;
    }
}
