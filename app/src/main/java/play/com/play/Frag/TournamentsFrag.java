package play.com.play.Frag;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import play.com.play.Adapters.TournmemtAdapterFull;
import play.com.play.FragmentInterface;
import play.com.play.Modals.Tournaments;
import play.com.play.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TournamentsFrag extends Fragment implements FragmentInterface {

    @BindView(R.id.rvTournament)
    RecyclerView rvTournament;

    List<Tournaments> tournalist=new ArrayList();

    TournmemtAdapterFull tournmemtAdapgter;

    public TournamentsFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_tournaments, container, false);

        ButterKnife.bind(this,v);

        if (getArguments() != null)
            tournalist=getArguments().getParcelableArrayList("tourn");

        rvTournament.setLayoutManager(new LinearLayoutManager(getActivity()));
        tournmemtAdapgter=new TournmemtAdapterFull(getActivity(),tournalist);
        rvTournament.setAdapter(tournmemtAdapgter);

        return v;
    }

    @Override
    public void search(String s) {
        tournmemtAdapgter.getFilter().filter(s);
    }
}
