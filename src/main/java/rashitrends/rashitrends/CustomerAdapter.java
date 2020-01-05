package rashitrends.rashitrends;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    List<Customers> allCustomers = new ArrayList<>();

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.userdisp, parent, false);
        return new CustomerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customers customer = allCustomers.get(position);
        holder.username.setText(customer.getName());
        byte[] imgarr = customer.getProfile_image();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgarr, 0, imgarr.length);
        holder.userimage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return allCustomers.size();
    }

    public void updateCustomers(List<Customers> allCustomers) {
        this.allCustomers = allCustomers;
        notifyDataSetChanged();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        CircularImageView userimage;
        TextView username;
        public CustomerViewHolder(View itemView) {
            super(itemView);
            userimage = itemView.findViewById(R.id.user_image);
            username = itemView.findViewById(R.id.username);
        }
    }
}
