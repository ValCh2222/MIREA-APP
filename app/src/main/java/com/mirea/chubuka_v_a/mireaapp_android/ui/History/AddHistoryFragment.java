package com.mirea.chubuka_v_a.mireaapp_android.ui.History;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.mirea.chubuka_v_a.mireaapp_android.App;
import com.mirea.chubuka_v_a.mireaapp_android.AppDatabase;
import com.mirea.chubuka_v_a.mireaapp_android.ApparatFragment;
import com.mirea.chubuka_v_a.mireaapp_android.CalculateFragment;
import com.mirea.chubuka_v_a.mireaapp_android.R;


public class AddHistoryFragment extends Fragment {

    private EditText storyEditText;
    private FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_history, container, false);
        storyEditText = view.findViewById(R.id.editTextStory);

        view.findViewById(R.id.saveStoryBtn).setOnClickListener(this::onClickSaveStory);

        return view;
    }

    public void onClickSaveStory(View view){

        AppDatabase db = App.getInstance().getDatabase();
        HistoryDao historyDao = db.historyDao();

        History history = new History();
        history.story = storyEditText.getText().toString();

        historyDao.insert(history);


        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}