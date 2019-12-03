package com.yadhukrishnane.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messages;

    ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == Message.LEFT_SIDE) {
            View view = inflater.inflate(R.layout.single_left_msg, parent, false);
            return new LeftViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.single_right_msg, parent, false);
            return new RightViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if (holder instanceof LeftViewHolder) {
            LeftViewHolder viewHolder = (LeftViewHolder) holder;
            viewHolder.tvMessage.setText(message.getMessage());
        } else {
            RightViewHolder viewHolder = (RightViewHolder) holder;
            viewHolder.tvMessage.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);

        return message.getSide();
    }

    class LeftViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView tvMessage;
        private AppCompatTextView tvTiming;

        LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_left_msg);
        }
    }

    class RightViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView tvMessage;
        private AppCompatTextView tvTiming;

        RightViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMessage = itemView.findViewById(R.id.tv_right_msg);

        }
    }
}
