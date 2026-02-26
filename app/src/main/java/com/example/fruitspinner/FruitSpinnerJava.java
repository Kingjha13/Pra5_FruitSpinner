package com.example.fruitspinner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FruitSpinnerJava extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView dropdown = findViewById(R.id.dropdown);
        ImageView fruitImage = findViewById(R.id.fruitImage);

        String[] fruits = getResources().getStringArray(R.array.fruit_names);

        int[] images = {
                R.drawable.banana,
                R.drawable.apple,
                R.drawable.mango
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                fruits
        );

        dropdown.setAdapter(adapter);
        dropdown.setText(fruits[0], false);
        fruitImage.setImageResource(images[0]);

        dropdown.setOnClickListener(v -> dropdown.showDropDown());

        dropdown.setOnItemClickListener((parent, view, position, id) -> {
            dropdown.setText(fruits[position], false);
            fruitImage.setImageResource(images[position]);
        });
    }
}