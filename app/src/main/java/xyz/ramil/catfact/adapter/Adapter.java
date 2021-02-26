package xyz.ramil.catfact.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;

import java.util.List;

import xyz.ramil.catfact.R;
import xyz.ramil.catfact.data.db.DataBaseManager;
import xyz.ramil.catfact.data.model.CatFactModel;
import xyz.ramil.catfact.model.Facts;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<CatFactModel> facts;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;
    private Context context;

    public Adapter(Context context, List<CatFactModel> data, ItemClickListener itemClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.facts = data;
        clickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CatFactModel animal = facts.get(position);
        holder.textView.setText(animal.type);
        holder.textDescription.setText(animal.fact);
        Glide.with(this.context)
                .load(animal.cat)
                .circleCrop()
                .signature(new ObjectKey(System.currentTimeMillis()))
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    CatFactModel getItem(int id) {
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