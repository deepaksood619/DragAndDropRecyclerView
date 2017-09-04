package io.github.deepaksood619.draganddroprecyclerview;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by deepak on 04/09/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements ItemTouchHelperAdapter {

    private final OnDragStartListener mDragStartListener;
    List<String> items;

    public MyAdapter(OnDragStartListener dragStartListener, ArrayList<String> items) {
        this.items = items;
        mDragStartListener = dragStartListener;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(items, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(items, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(items.get(position));

        holder.handleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) ==
                        MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onDragStarted(holder);
                }
                return false;
            }
        });
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnDragStartListener {
        void onDragStarted(RecyclerView.ViewHolder viewHolder);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView handleView;

        public MyViewHolder(View v) {
            super(v);
            tv = v.findViewById(R.id.textView);
            handleView = v.findViewById(R.id.handle);
        }
    }


}
