package rashitrends.rashitrends;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DimensionAdapter extends RecyclerView.Adapter<DimensionAdapter.DimensionHolder> {
    List<String> alldata = new ArrayList<>();

    @NonNull
    @Override
    public DimensionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.dimensionrecycler, parent, false);
        return new DimensionHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DimensionHolder holder, int position) {
        holder.textView.setText(alldata.get(position));
    }

    @Override
    public int getItemCount() {
        return alldata.size();
    }

    public void updateDimensions(List<String> alldata) {
        this.alldata = alldata;
        notifyDataSetChanged();
    }

    public class DimensionHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public DimensionHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView5);
        }
    }
}
