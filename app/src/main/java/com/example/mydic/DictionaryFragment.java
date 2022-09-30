package com.example.mydic;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class DictionaryFragment extends Fragment {

    private FragmentListener listener;
    private String value = "Hello everyone";
    ArrayAdapter<String> adapter;
    ListView diclist;
    private ArrayList<String> mSource = new ArrayList<String>();

    public DictionaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Button button = (Button) view.findViewById(R.id.myBtn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null)
//                    listener.onItemClick(value);
//            }
//        });
        diclist = view.findViewById(R.id.dictionaryList);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mSource);
        diclist.setAdapter(adapter);
        diclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null)
                    listener.onItemClick(mSource.get(position));
            }
        });
    }

    public void resetDataSource(ArrayList<String> source) {
        mSource = source;
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mSource);
        diclist.setAdapter(adapter);
    }

    public void filiterValue(String value) {
        // adapter.getFilter().filter(value);
        int size = adapter.getCount();
        for (int i = 0; i < size; i++) {
            if (adapter.getItem(i).startsWith(value)) {
                diclist.setSelection(i);
                break;
            }
        }
    }

    String[] getListOfWords() {
        String[] source = new String[]{
                "A",
                "Abeba",
                "የማነ",
                "kemey",
                "Hey",
                "what",
                "Why",
                "What hoe",
                "Restfull",
                "beauty",
                "up",
                "in",
                "out",
                "for",
                "ደስታ",
                "ፍሽክታ",
                "ፀዳል",
                "ኪዳን",
                "አዲስ አለም"
        };
        return source;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setOnFragmentlistener(FragmentListener listener) {
        this.listener = listener;
    }
}
