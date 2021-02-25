package xyz.ramil.catfact.adapter;

import android.content.Context;
import android.content.pm.Signature;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.ObjectKey;

import java.util.List;
import java.util.UUID;

import xyz.ramil.catfact.R;
import xyz.ramil.catfact.model.Facts;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Facts> facts;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;
    private Context context;

    public Adapter(Context context, List<Facts> data) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.facts = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Facts animal = facts.get(position);
        holder.textView.setText(animal.getType());
        holder.textDescription.setText(animal.getText());
        Glide.with(this.context)
                .load("https://cataas.com/cat")
                .circleCrop()
                .signature(new ObjectKey(System.currentTimeMillis()))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    Facts getItem(int id) {
        return facts.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        TextView textDescription;

        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvName);
            textDescription = itemView.findViewById(R.id.tvDescription);
            imageView = itemView.findViewById(R.id.ivImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }
}