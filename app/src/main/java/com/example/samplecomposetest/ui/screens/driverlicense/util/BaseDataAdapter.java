package com.example.samplecomposetest.ui.screens.driverlicense.util;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

// TODO convert to kotlin
public abstract class BaseDataAdapter<T, VH extends ViewHolder> extends RecyclerView.Adapter<VH> {

    protected final List<T> data = new ArrayList<>();

    public void setData(List<T> list) {
        data.clear();

        if (list != null) {
            data.addAll(list);
        }

        notifyDataSetChanged();
    }

    public void setData(@NonNull List<T> list, @Nullable DiffUtil.DiffResult diffResult) {
        if (diffResult != null) {
            setItemQuiet(list);
            dispatchDiffUpdates(diffResult);
        } else {
            this.data.clear();
            this.data.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setItemQuiet(@NonNull List<T> listData) {
        data.clear();
        data.addAll(listData);
    }

    protected void dispatchDiffUpdates(@NonNull DiffUtil.DiffResult diffResult) {
        diffResult.dispatchUpdatesTo(this);
    }

    public void appendItem(T item) {
        int preSize = data.size();
        data.add(item);
        notifyItemInserted(preSize);
    }

    public void prependItems(List<T> items) {
        data.addAll(0, items);
        notifyItemRangeInserted(0, items.size());
    }

    public void setItem(int index, T item) {
        data.set(index, item);
        notifyItemChanged(index);
    }

    public void removeItem(int index) {
        data.remove(index);
        notifyItemRemoved(index);
    }

    public T getItem(int position) {
        if (position < data.size()) {
            return data.get(position);
        } else {
            return null;
        }
    }

    @Override public int getItemCount() {
        return data.size();
    }
}
