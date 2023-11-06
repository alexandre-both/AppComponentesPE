package br.dev.alexandreboth.mobile.componentespe.ui.componente;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import br.dev.alexandreboth.mobile.componentespe.databinding.FragmentConComponenteBinding;
import br.dev.alexandreboth.mobile.componentespe.model.Componente;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Componente}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ConComponenteRecyclerViewAdapter extends RecyclerView.Adapter<ConComponenteRecyclerViewAdapter.ViewHolder> {

    private final List<Componente> mValues;

    public ConComponenteRecyclerViewAdapter(List<Componente> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConComponenteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(mValues.get(position).getId()));
        holder.mContentView.setText(mValues.get(position).getNome() +
                " Esp. " + mValues.get(position).getDeEspaco() +
                " Gav. " + mValues.get(position).getDeGaveta() +
                " Tp. " + mValues.get(position).getDeTipo());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Componente mItem;

        public ViewHolder(FragmentConComponenteBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}