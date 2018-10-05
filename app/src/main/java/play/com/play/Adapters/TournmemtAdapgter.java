package play.com.play.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import play.com.play.Modals.Tournaments;
import play.com.play.R;

public class TournmemtAdapgter extends RecyclerView.Adapter<TournmemtAdapgter.MyViewHolder>implements Filterable {

    List<Tournaments> list=new ArrayList();
    List<Tournaments> listmain=new ArrayList();
    Context context;
    public TournmemtAdapgter(Context c, List<Tournaments> list)
    {
        this.list=list;
        listmain=list;
        this.context=c;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.view_trounament,null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.game.setText(list.get(i).getSport_type());
        myViewHolder.location.setText(list.get(i).getAddress());
        myViewHolder.name.setText(list.get(i).getName());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context).load(list.get(i).getImage()).apply(options).into(myViewHolder.image);


    }

    @Override
    public int getItemCount() {
        return Math.min(list.size(), 3);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name,game,location;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.image);
            location=itemView.findViewById(R.id.location4);
            game=itemView.findViewById(R.id.game);
            name=itemView.findViewById(R.id.name);

        }

    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                String filterString = charSequence.toString().toLowerCase();

                final List<Tournaments> list = listmain;

                int count = list.size();
                final ArrayList<Tournaments> nlist = new ArrayList<Tournaments>(count);

                String filterableString ;

                for (int i = 0; i < count; i++) {
                    filterableString = list.get(i).getName();
                    if (filterableString.toLowerCase().contains(filterString.toLowerCase())) {
                        nlist.add(list.get(i));
                    }
                }

                results.values = nlist;
                results.count = nlist.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<Tournaments>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
