package com.example.partnersincode.optimedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.models.Library;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder> {
    private final List<Library> libraryList;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public LibraryAdapter(List<Library> libraryList){
        this.libraryList = libraryList;
    }

    public LibraryAdapter(List<Library> libraryList, View.OnClickListener onClickListener) {
        this.libraryList = libraryList;
        this.onClickListener = onClickListener;
    }

    /**
     * TODO: Recycler View with library information
     */
    public static class LibraryViewHolder extends RecyclerView.ViewHolder{
        public TextView txtRecyclerLibraryName;
        public TextView txtRecyclerLibraryType;
        public Library library;

        public LibraryViewHolder(@NonNull View itemView) {
            super(itemView);

            txtRecyclerLibraryName = itemView.findViewById(R.id.txtRecyclerLibraryName);
            txtRecyclerLibraryType = itemView.findViewById(R.id.txtRecyclerLibraryType);
        }

        public void setLibrary(Library library) {
            // TODO: set which library is selected
            this.library = library;

            txtRecyclerLibraryName.setText(library.getLibraryName());
            txtRecyclerLibraryType.setText(library.getLibraryType());
        }
    }


    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_library_generic,parent, false);
        LibraryViewHolder viewHolder = new LibraryViewHolder(view);
        return viewHolder;
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder holder, int position) {
        Library library = libraryList.get(position);
        holder.setLibrary(library);
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return libraryList.size();
    }


}
