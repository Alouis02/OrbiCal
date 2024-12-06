package com.tmsimple.orbical;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

   //This list represents all the elements we wish to reuse in our recyclerview for the popup, we construct this class using an external list of elements
    private List<String> events;
    public EventAdapter(List<String> events){
        this.events = events;
    }

    //build a new view that can be reused in the app
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new EventViewHolder(view);
    }

    //fill our reclyclable view with items from the "events" list above at each "position"
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.textView.setText(events.get(position));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
