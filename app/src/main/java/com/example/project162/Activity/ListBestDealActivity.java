package com.example.project162.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project162.Adapter.BestFruitsAdapter;
import com.example.project162.Domain.Foods;
import com.example.project162.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListBestDealActivity extends AppCompatActivity {
    private RecyclerView bestDealsListView;
    private ProgressBar progressBar;
    private ArrayList<Foods> foodList;
    private BestFruitsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_best_deal);

        // Initialize UI
        bestDealsListView = findViewById(R.id.bestDealsListView);
        progressBar = findViewById(R.id.progressBar);
        ImageView backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(v -> onBackPressed());

        // Configure RecyclerView
        bestDealsListView.setLayoutManager(new LinearLayoutManager(this));
        foodList = new ArrayList<>();
        adapter = new BestFruitsAdapter(this, foodList);
        bestDealsListView.setAdapter(adapter);
        // Load data from Firebase
        loadBestDeals();
    }

    private void loadBestDeals() {
        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Foods");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Foods food = data.getValue(Foods.class);
                    if (food != null && food.isBestFood()) {
                        foodList.add(food);
                    }
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
