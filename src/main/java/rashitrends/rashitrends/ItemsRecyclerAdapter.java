package rashitrends.rashitrends;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ItemsViewHolder>
implements Filterable{
    private List<Items> allItems = new ArrayList<>();
    private List<Items> allItemsDup = new ArrayList<>(allItems);
    OnItemsClickListener listener;
    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.itemsdisplay, parent, false);
        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        Items items = allItems.get(position);
        holder.name.setText(items.getItem_name());
        holder.quantity.setText(String.valueOf(items.getQuantity()));
        holder.price.setText(String.valueOf(items.getAvg_price()));
        holder.dimensions.setText(items.getDimensions());
        //show image
        byte[] imgarr = items.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgarr, 0, imgarr.length);
        holder.photo.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return allItems.size();
    }

    public void updateItems(List<Items> allItems) {
        this.allItems = allItems;
        allItemsDup = new ArrayList<>(allItems);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Items> filteredItems = new ArrayList<>();
            if(constraint != null && constraint.length() != 0) {
                for(Items i : allItemsDup) {
                    String s = constraint.toString().toLowerCase().trim();
                    if(i.getItem_name().toLowerCase().contains(s))
                        filteredItems.add(i);
                }
            }
            else {
                filteredItems.addAll(allItems);
            }
            FilterResults results = new FilterResults();
            results.values = filteredItems;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            allItems.clear();
            allItems.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public class ItemsViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, quantity, dimensions;
        ImageView photo;
        Button delete;
        public ItemsViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemName);
            price = itemView.findViewById(R.id.itemPrice);
            quantity = itemView.findViewById(R.id.itemQuantity);
            photo = itemView.findViewById(R.id.itemImage);
            dimensions = itemView.findViewById(R.id.Dimensions);
            delete = itemView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClickedForDelete(allItems.get(getAdapterPosition()));
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClicked(allItems.get(getAdapterPosition()));
                }
            });
        }
    }

    interface OnItemsClickListener {
        public void itemClicked(Items item);
        public void itemClickedForDelete(Items item);
    }

    public void setItemsClickListener(OnItemsClickListener listener) {
        this.listener = listener;
    }
}
