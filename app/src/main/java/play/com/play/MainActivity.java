package play.com.play;

import android.graphics.Color;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import play.com.play.Frag.AllFrag;
import play.com.play.Frag.TournamentsFrag;
import play.com.play.Modals.Grounds;
import play.com.play.Modals.Players;
import play.com.play.Modals.Tournaments;
import play.com.play.network.MyVolley;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout frameLayout;
//CDDCE4
    @BindView(R.id.all)
    Button all;
    @BindView(R.id.tourn)
    Button tourn;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    FragmentTransaction ft;
    @BindView(R.id.editText)
    EditText editText;

    List<Tournaments> tournalist=new ArrayList();
    List<Players> playerslist=new ArrayList();
    List<Grounds> groundslist=new ArrayList();

    FragmentInterface fragmentInterface;
/*adf ads
// a d
     ad
     as
         a as

                 afa

    a s
            f a
                     f sa
                             s f
                                     as a/*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.VISIBLE);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fragmentInterface.search(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getdata();
    }

    private void getdata() {

        String url=" http://13.232.26.169/api/venue/search_explore/?lat=28.4283254&long=77.093013";
        StringRequest request=new StringRequest(Request.Method.GET, url, response -> {
                Log.i("", "onResponse: Added"+response);
                progressBar.setVisibility(View.GONE);
            try {
                parseResponse(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            progressBar.setVisibility(View.GONE);
            Log.i("", "onResponse: Error");

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("Authorization","Bearer nNWfGQnsx1oWsxWXhq3md3uP4046rv");
                return map;
            }

        };
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                return 0;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        MyVolley.getInstance().getRequestQueue().add(request);
    }


    private void parseResponse(String response) throws JSONException {
        Gson gson=new Gson();

        JSONObject object=new JSONObject(response);
        JSONArray venuesarray=object.getJSONArray("venues");
        for (int i = 0; i < venuesarray.length(); i++) {
            Grounds grounds=gson.fromJson(venuesarray.getJSONObject(i).toString(),Grounds.class);
            groundslist.add(grounds);
        }
        JSONArray tournarray=object.getJSONArray("tournament");
        for (int i = 0; i < venuesarray.length(); i++) {
            Tournaments grounds=gson.fromJson(tournarray.getJSONObject(i).toString(),Tournaments.class);
            tournalist.add(grounds);
        }
        JSONArray playerarray=object.getJSONArray("players");
        for (int i = 0; i < venuesarray.length(); i++) {
            Players grounds=gson.fromJson(playerarray.getJSONObject(i).toString(),Players.class);
            playerslist.add(grounds);
        }
        setuptab();
    }

    private void setuptab() {
        AllFrag newFragment = new AllFrag();
        Bundle b=new Bundle();
        b.putParcelableArrayList("players", (ArrayList<? extends Parcelable>) playerslist);
        b.putParcelableArrayList("tourn", (ArrayList<? extends Parcelable>) tournalist);
        b.putParcelableArrayList("venues", (ArrayList<? extends Parcelable>) groundslist);
        newFragment.setArguments(b);
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame, newFragment).commit();
        fragmentInterface= newFragment;

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllFrag newFragment = new AllFrag();
                Bundle b=new Bundle();
                b.putParcelableArrayList("players", (ArrayList<? extends Parcelable>) playerslist);
                b.putParcelableArrayList("tourn", (ArrayList<? extends Parcelable>) tournalist);
                b.putParcelableArrayList("venues", (ArrayList<? extends Parcelable>) groundslist);
                newFragment.setArguments(b);
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, newFragment).commit();
                fragmentInterface= newFragment;


                all.setTextColor(Color.WHITE);
                all.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.background_rounded_corner));
                tourn.setTextColor(Color.BLACK);
                tourn.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.background_rounded_corner_unselected));
                tourn.setPadding(10,0,10,0);
            }
        });

        tourn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TournamentsFrag newFragment = new TournamentsFrag();
                Bundle b=new Bundle();
                b.putParcelableArrayList("tourn", (ArrayList<? extends Parcelable>) tournalist);
                newFragment.setArguments(b);
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, newFragment).commit();
                fragmentInterface= newFragment;

                all.setTextColor(Color.BLACK);
                all.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.background_rounded_corner_unselected));
                tourn.setTextColor(Color.WHITE);
                tourn.setBackground(ContextCompat.getDrawable(MainActivity.this,R.drawable.background_rounded_corner));
                tourn.setPadding(10,0,10,0);

            }
        });

    }




}
