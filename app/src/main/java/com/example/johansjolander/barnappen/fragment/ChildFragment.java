package com.example.johansjolander.barnappen.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.johansjolander.barnappen.R;
import com.example.johansjolander.barnappen.model.Child;
import com.example.johansjolander.barnappen.repository.inmemory.InMemoryChildRepository;
import com.example.johansjolander.barnappen.repository.impl.ChildRepository;

import java.util.List;

public class ChildFragment extends Fragment {
    private static final String BUNDLE_ID_KEY = "bundle_id";
    private ChildRepository childRepository;
    private Callbacks callBacks;

    private ChildListAdapter adapter;

    public interface Callbacks {
        void onListItemClicked(Child child);
    }

    public static Fragment newInstance() {
        return new ChildFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callBacks = (Callbacks) context;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Hosting activity must implement callbacks");
        }
    }

    //TODO Continue here  !!!!!!!!!!!!!!

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        childRepository = new InMemoryChildRepository();
    }

    //TODO TEST
    public void updateDataSet(ChildRepository childRepository) {
        this.childRepository = childRepository;

        adapter.setData(this.childRepository.getAllChildren());

        adapter.notifyDataSetChanged();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child, container, false);

        adapter = new ChildListAdapter(childRepository.getAllChildren(),
                new ChildListAdapter.OnItemClickedListener() {
                    @Override
                    public void onItemClicked(Child child) {
                        callBacks.onListItemClicked(child);
                    }
                });


        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_child_fragment);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.notifyDataSetChanged(); //TODO ?????
        return view;
    }

    private static final class ChildListAdapter extends RecyclerView.Adapter<ChildListAdapter.ChildViewHolder> {
        private List<Child> childList;
        private final OnItemClickedListener onItemClickedListener;

        private ChildListAdapter(List<Child> childList, OnItemClickedListener onItemClickedListener) {
            this.childList = childList;
            this.onItemClickedListener = onItemClickedListener;
        }

        private void setData(List<Child> childListNew){
            childList = childListNew;
        }

        @Override
        public ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.child_item, parent, false);
            return new ChildViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ChildViewHolder holder, int position) {
            Child child = childList.get(position);
            holder.bindView(child, onItemClickedListener);
        }

        @Override
        public int getItemCount() {
            return childList.size();
        }

        public interface OnItemClickedListener {
            void onItemClicked(Child child);
        }


        static final class ChildViewHolder extends RecyclerView.ViewHolder {
            private final TextView fullNameTextView;
            private final TextView ageTextView;

            ChildViewHolder(View itemView) {
                super(itemView);
                fullNameTextView = (TextView) itemView.findViewById(R.id.child_item_name);
                ageTextView = (TextView) itemView.findViewById(R.id.child_item_age);
            }

            void bindView(final Child child, final OnItemClickedListener onItemClickedListener) {
                fullNameTextView.setText(child.getName());  //TODO NullpointerException on fullNameTextView
                Log.i("test ", child.getName());
                ageTextView.setText(String.valueOf(child.getDays()));
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickedListener.onItemClicked(child);
                    }
                });
            }
        }
    }
}