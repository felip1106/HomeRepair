package co.com.app.homerepair.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.com.app.homerepair.R;
import co.com.app.homerepair.enums.CategoriaEnum;
import co.com.app.homerepair.model.Categoria;
import co.com.app.homerepair.utils.BitmapScaler;
import co.com.app.homerepair.view.fragment.CategoriaFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Categoria} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ItemCategoriaAdapter extends RecyclerView.Adapter<ItemCategoriaAdapter.ViewHolder> {

    private final List<Categoria> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;
    private static final int IMAGE_WIDTH = 100;
    private static final int IMAGE_HEIGHT = 100;

    public ItemCategoriaAdapter(List<Categoria> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_categoria, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int resId = getIconCategoria(mValues.get(position).getId());
        holder.mItem = mValues.get(position);
        holder.mIconView.setImageBitmap(scaleBitmap(resId));
        holder.mMessageView.setText(mValues.get(position).getCat_descripcion());

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

    private int getIconCategoria(Long id) {
        int resIconCategoria = R.mipmap.ic_request_create;

        for (CategoriaEnum categoriaEnum : CategoriaEnum.values()) {
            if (categoriaEnum.id == id) {
                resIconCategoria =  categoriaEnum.resIconCategoria;
                break;
            }
        }

        return resIconCategoria;
    }

    private Bitmap scaleBitmap(int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        return BitmapScaler.scaleToFill(bitmap, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIconView;
        public final TextView mMessageView;
        public Categoria mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIconView = view.findViewById(R.id.icon_item_categoria);
            mMessageView = view.findViewById(R.id.message_item_categoria);
        }
    }
}
