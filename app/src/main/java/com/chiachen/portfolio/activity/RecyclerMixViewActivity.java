package com.chiachen.portfolio.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chiachen.portfolio.R;
import com.chiachen.portfolio.adapter.mix.MainAdapter;
import com.chiachen.portfolio.adapter.mix.SingleHorizontal;
import com.chiachen.portfolio.adapter.mix.SingleVertical;

import java.util.ArrayList;

//Ref. https://medium.com/@anujguptawork/simple-android-app-with-mvp-rxjava2-and-retrofit-part-2-adding-search-using-rxjava2-operators-a3ebc1826541
public class RecyclerMixViewActivity extends AppCompatActivity {

    private ArrayList<Object> objects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_mix_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        MainAdapter adapter = new MainAdapter(this, getObject());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private ArrayList<Object> getObject() {
        objects.add(getVerticalData().get(0));
        objects.add(getHorizontalData().get(0));
        return objects;
    }

    public static ArrayList<SingleVertical> getVerticalData() {
        ArrayList<SingleVertical> singleVerticals = new ArrayList<>();
        singleVerticals.add(new SingleVertical("Charlie Chaplin", "Sir Charles Spencer \"Charlie\" Chaplin, KBE was an English comic actor,....", R.mipmap.charlie));
        singleVerticals.add(new SingleVertical("Mr.Bean", "Mr. Bean is a British sitcom created by Rowan Atkinson and Richard Curtis, and starring Atkinson as the title character.", R.mipmap.mrbean));
        singleVerticals.add(new SingleVertical("Jim Carrey", "James Eugene \"Jim\" Carrey is a Canadian-American actor, comedian, impressionist, screenwriter...", R.mipmap.jim));
        return singleVerticals;
    }


    public static ArrayList<SingleHorizontal> getHorizontalData() {
        ArrayList<SingleHorizontal> singleHorizontals = new ArrayList<>();
        singleHorizontals.add(new SingleHorizontal(R.mipmap.charlie, "Charlie Chaplin", "Sir Charles Spencer \"Charlie\" Chaplin, KBE was an English comic actor,....", "2010/2/1"));
        singleHorizontals.add(new SingleHorizontal(R.mipmap.mrbean, "Mr.Bean", "Mr. Bean is a British sitcom created by Rowan Atkinson and Richard Curtis, and starring Atkinson as the title character.", "2010/2/1"));
        singleHorizontals.add(new SingleHorizontal(R.mipmap.jim, "Jim Carrey", "James Eugene \"Jim\" Carrey is a Canadian-American actor, comedian, impressionist, screenwriter...", "2010/2/1"));
        return singleHorizontals;
    }
}
