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

import play.com.play.Modals.Players;
import play.com.play.Modals.Tournaments;
import play.com.play.R;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.MyViewHolder> implements Filterable {
    List<Players> listmain=new ArrayList();

    List<Players> list=new ArrayList();
    Context context;
    public TeamsAdapter(Context c, List<Players> list)
    {
        this.list=list;
        this.context=c;
        listmain=list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.view_teams,null);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.game.setText("Cricket");
        myViewHolder.name.setText(list.get(i).getName());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context).load(list.get(i).getProfile_pic()).apply(options).into(myViewHolder.image);

    }

    @Override
    public int getItemCount() {
        return Math.min(list.size(), 3);

    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name,game;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.image);
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

                final List<Players> list = listmain;

                int count = list.size();
                final ArrayList<Players> nlist = new ArrayList<Players>(count);

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
                list = (ArrayList<Players>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
