package io.github.deepaksood619.draganddroprecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnDragStartListener {

    private RecyclerView mRecyclerView;

    ArrayList<String> strings;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);

        MyAdapter myAdapter = new MyAdapter(this, strings);

        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(myAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onDragStarted(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
