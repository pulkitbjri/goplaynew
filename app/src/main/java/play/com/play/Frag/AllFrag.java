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
import play.com.play.Adapters.GroundAdapter;
import play.com.play.Adapters.TeamsAdapter;
import play.com.play.Adapters.TournmemtAdapgter;
import play.com.play.FragmentInterface;
import play.com.play.Modals.Grounds;
import play.com.play.Modals.Players;
import play.com.play.Modals.Tournaments;
import play.com.play.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllFrag extends Fragment implements FragmentInterface {


    public AllFrag() {
        // Required empty public constructor
    }

    @BindView(R.id.rvTeams)
    RecyclerView rvTeams;
    @BindView(R.id.rvGround)
    RecyclerView rvGround;
    @BindView(R.id.rvTournament)
    RecyclerView rvTournament;

    List<Tournaments> tournalist=new ArrayList();
    List<Players> playerslist=new ArrayList();
    List<Grounds> groundslist=new ArrayList();

    TournmemtAdapgter tournmemtAdapgter;
    GroundAdapter groundAdapter;
    TeamsAdapter  teamsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_all, container, false);
        ButterKnife.bind(this,v);

        if (getArguments() != null) {
            tournalist=getArguments().getParcelableArrayList("tourn");
            playerslist=getArguments().getParcelableArrayList("players");
            groundslist=getArguments().getParcelableArrayList("venues");

        }

        rvTournament.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvGround.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTeams.setLayoutManager(new LinearLayoutManager(getActivity()));



        tournmemtAdapgter=new TournmemtAdapgter(getActivity(),tournalist);
        rvTournament.setAdapter(tournmemtAdapgter);

        teamsAdapter=new TeamsAdapter(getActivity(),playerslist);
        rvTeams.setAdapter(teamsAdapter);

        groundAdapter=new GroundAdapter(getActivity(),groundslist);
        rvGround.setAdapter(groundAdapter);




        return v;
    }

    @Override
    public void search(String s) {
        groundAdapter.getFilter().filter(s);
        tournmemtAdapgter.getFilter().filter(s);
        teamsAdapter.getFilter().filter(s);
    }
}
