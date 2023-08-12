//10120111 - Syafiq Pramana Irawan - IF3
package com.example.tugasuas_10120111.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.tugasuas_10120111.AddActivity;
import com.example.tugasuas_10120111.CustomNotesAdapter;
import com.example.tugasuas_10120111.MyDBHelper;
import com.example.tugasuas_10120111.NoteModel;
import com.example.tugasuas_10120111.R;
import com.example.tugasuas_10120111.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    CustomNotesAdapter customNotesAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.recycleNote);
        add_button = root.findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });

        MyDBHelper db = new MyDBHelper(getActivity());
        ArrayList<NoteModel> data = db.getNotes();

        customNotesAdapter = new CustomNotesAdapter(getActivity(), data);
        recyclerView.setAdapter(customNotesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}