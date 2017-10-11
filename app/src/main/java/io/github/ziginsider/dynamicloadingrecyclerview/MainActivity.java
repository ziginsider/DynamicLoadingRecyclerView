package io.github.ziginsider.dynamicloadingrecyclerview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.github.ziginsider.dynamicloadingrecyclerview.Adapter.MyAdapter;
import io.github.ziginsider.dynamicloadingrecyclerview.Interface.ILoadMore;
import io.github.ziginsider.dynamicloadingrecyclerview.Model.Item;

public class MainActivity extends AppCompatActivity {

    List<Item> items = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random10Data(0, 10);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(recyclerView, this, items);
        recyclerView.setAdapter(adapter);

        //Set Load more
        adapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if (items.size() <= 100) {
                    items.add(null);
                    adapter.notifyItemInserted(items.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size() - 1);
                            adapter.notifyItemRemoved(items.size());

                            //random more data
                            int index = items.size();
                            int end = index + 10;
                            random10Data(index, end);
                            adapter.notifyDataSetChanged();
                            adapter.setLoaded();
                        }
                    }, 3000);
                } else {
                    Toast.makeText(MainActivity.this, "Load data completed !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void random10Data(int indexStart, int indexEnd) {
        //random data
        for (int i = indexStart; i < indexEnd; i++) {
            String name = UUID.randomUUID().toString();
            Item newItem = new Item(name, name.length());
            items.add(newItem);
        }
    }
}
