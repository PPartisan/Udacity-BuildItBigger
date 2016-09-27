package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class AddNamesDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String TAG = AddNamesDialogFragment.class.getSimpleName();
    private static final String NAME_MODELS_KEY = "name_models_key";

    private AddNamesAdapter mAdapter;

    private EditText mAddNameEntryBox;

    public static AddNamesDialogFragment newInstance(ArrayList<NameModel> nameModels) {

        Bundle args = new Bundle();
        args.putParcelableArrayList(NAME_MODELS_KEY, nameModels);

        AddNamesDialogFragment fragment = new AddNamesDialogFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.dialog_fragment_add_names, container, false);

        EmptyRecyclerView recyclerView = (EmptyRecyclerView) root.findViewById(R.id.add_names_recycler);
        recyclerView.setEmptyView(root.findViewById(R.id.add_names_empty_view));
        mAdapter = new AddNamesAdapter(getNameModels());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Button addNameButton = (Button) root.findViewById(R.id.add_names_button);
        addNameButton.setOnClickListener(this);

        mAddNameEntryBox = (EditText) root.findViewById(R.id.add_names_entry_box);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }}

    @Override
    public void onClick(View view) {

        final String name = mAddNameEntryBox.getText().toString();

        if (!TextUtils.isEmpty(name)) {
            long id = DataUtils.addNameToDatabase(getContext(), name);
            mAdapter.addNameModel(new NameModel(id, name));
            mAddNameEntryBox.getText().clear();
        }

    }

    public void setNameModels(ArrayList<NameModel> nameModels) {
        mAdapter.setNameModels(nameModels);
        getArguments().putParcelableArrayList(NAME_MODELS_KEY, nameModels);
    }

    public List<NameModel> getNameModels() {
        return getArguments().getParcelableArrayList(NAME_MODELS_KEY);
    }

}
