package co.com.app.homerepair.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.com.app.homerepair.R;
import co.com.app.homerepair.view.fragment.MenuPrincipalFragment.OnListFragmentInteractionListener;
import co.com.app.homerepair.view.fragment.content.MenuPrincipalContent;

/**
 * {@link RecyclerView.Adapter} that can display a {@link co.com.app.homerepair.view.fragment.content.MenuPrincipalContent.Item} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ItemMenuAdapter extends RecyclerView.Adapter<ItemMenuAdapter.ViewHolder> {

    private final List<MenuPrincipalContent.Item> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ItemMenuAdapter(List<MenuPrincipalContent.Item> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIconView.setImageResource(mValues.get(position).icon);
        holder.mMessageView.setText(mValues.get(position).message);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIconView;
        public final TextView mMessageView;
        public MenuPrincipalContent.Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIconView = view.findViewById(R.id.icon_item_menu);
            mMessageView = view.findViewById(R.id.message_item_menu);
        }
    }
}
