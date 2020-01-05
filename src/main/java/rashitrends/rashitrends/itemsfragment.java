package rashitrends.rashitrends;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link itemsfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link itemsfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class itemsfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RashiTrendsViewModel viewModel;
    ItemsRecyclerAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    Items updateItem;
    SearchView searchView;
    int id;
    public static final int ITEMS_REQUEST_CODE = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public itemsfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment itemsfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static itemsfragment newInstance(String param1, String param2) {
        itemsfragment fragment = new itemsfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_itemsfragment, container, false);
        fab = v.findViewById(R.id.fabitems);
        searchView = v.findViewById(R.id.searchView);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        recyclerView = v.findViewById(R.id.itemsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new ItemsRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(RashiTrendsViewModel.class);
        viewModel.getAllItems().observe(this, new Observer<List<Items>>() {
            @Override
            public void onChanged(@Nullable List<Items> items) {
                adapter.updateItems(items);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ItemInfo.class);
                startActivityForResult(intent, ITEMS_REQUEST_CODE);
            }
        });

        adapter.setItemsClickListener(new ItemsRecyclerAdapter.OnItemsClickListener() {
            @Override
            public void itemClicked(Items item) {
                id = item.getId();
                Intent intent = new Intent(getActivity(), ItemInfo.class);
                intent.putExtra("name", item.getItem_name());
                intent.putExtra("price", String.valueOf(item.getAvg_price()));
                intent.putExtra("quantity", String.valueOf(item.getQuantity()));
                intent.putExtra("dimensions", item.getDimensions());
                intent.putExtra("image", item.getImage());
                startActivityForResult(intent, 10);
            }

            @Override
            public void itemClickedForDelete(Items item) {
                viewModel.delete(item);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return  v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ITEMS_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Items item = new Items(data.getStringExtra("name"),
                        data.getDoubleExtra("price", 0.0),
                        data.getStringExtra("dimensions"),
                        data.getIntExtra("quantity", 0),
                        data.getByteArrayExtra("image"));
                viewModel.insert(item);
            }
        }

        if(requestCode == 10) {
            if(resultCode == RESULT_OK) {
                updateItem = new Items(data.getStringExtra("name"),
                        data.getDoubleExtra("price", 0.0),
                        data.getStringExtra("dimensions"),
                        data.getIntExtra("quantity", 0),
                        data.getByteArrayExtra("image"));
                updateItem.setId(id);
                viewModel.update(updateItem);
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
