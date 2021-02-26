package xyz.ramil.catfact;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    boolean isRemove = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tvWelcome);

        RecyclerView recyclerView = findViewById(R.id.rvFacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, facts, new Adapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.getDataBaseManager().delete(MainActivity.this.getApplicationContext(), facts.get(position));
                facts.remove(facts.get(position));
               adapter.notifyItemRemoved(position);
               isRemove = true;


            }
        });
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);


        presenter.getDataBaseManager().getData(this).observe(this, new Observer<List<CatFactModel>>() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(List<CatFactModel> catFactModels) {
                if(!catFactModels.isEmpty())
                    textView.setVisibility(View.GONE);
                if(facts.isEmpty()) {
                    textView.setVisibility(View.VISIBLE);
                facts.addAll(catFactModels);
                    adapter.notifyDataSetChanged();
                } else {
                    if(!isRemove) {
                        facts.add(catFactModels.get(catFactModels.size() - 1));
                        adapter.notifyItemChanged(adapter.getItemCount() - 1);
                    }
                    isRemove = false;
                }
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
                presenter.loadData(getApplicationContext());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}