package xyz.ramil.catfact;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import xyz.ramil.catfact.adapter.Adapter;
import xyz.ramil.catfact.data.model.CatFactModel;
import xyz.ramil.catfact.model.Facts;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    Adapter adapter;

    List<CatFactModel> facts = new ArrayList<>();

    TextView textView;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.tvWelcome);

        RecyclerView recyclerView = findViewById(R.id.rvFacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, facts);
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);

        presenter.getDataBaseManager().getData(this).observe(this, new Observer<List<CatFactModel>>() {

            @Override
            public void onChanged(List<CatFactModel> catFactModels) {
               facts.addAll(catFactModels);
               adapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.download:
                textView.setVisibility(View.GONE);
                presenter.loadData(getApplicationContext());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void update(CatFactModel model) {



    }
}