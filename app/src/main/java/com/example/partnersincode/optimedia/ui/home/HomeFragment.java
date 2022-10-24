package com.example.partnersincode.optimedia.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.partnersincode.optimedia.DatabaseHandler;
import com.example.partnersincode.optimedia.R;
import com.example.partnersincode.optimedia.adapters.LibraryAdapter;
import com.example.partnersincode.optimedia.databinding.FragmentHomeBinding;
import com.example.partnersincode.optimedia.models.Library;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DatabaseHandler dbHandler = new DatabaseHandler(this.getContext());
        dbHandler.deleteTemp();

        List<Library> libraryList = dbHandler.getAllLibraries();
        RecyclerView reListLib_Home = root.findViewById(R.id.reListLib_Home);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        reListLib_Home.setLayoutManager(layoutManager);
        LibraryAdapter adapter = new LibraryAdapter(libraryList);
        reListLib_Home.setAdapter(adapter);

        adapter.setOnClickListener( view -> {
            LibraryAdapter.LibraryViewHolder viewHolder = (LibraryAdapter.LibraryViewHolder) reListLib_Home.findContainingViewHolder(view);
            Library library = viewHolder.library;
//            Toast.makeText(this.getContext(), library.getLibraryName(),Toast.LENGTH_LONG).show();
            Bundle bundle = new Bundle();
            bundle.putParcelable("libraryInfo", library);

            if (library.getLibraryType().equals("Book")) {
                Navigation.findNavController(view).navigate(R.id.nav_viewBookLibrary, bundle);
            } else if (library.getLibraryType().equals("Game")) {
                Navigation.findNavController(view).navigate(R.id.nav_viewGameLibrary, bundle);
            } else if (library.getLibraryType().equals("Watch")) {
                Navigation.findNavController(view).navigate(R.id.nav_viewWatchLibrary, bundle);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}