package co.com.app.homerepair.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import co.com.app.homerepair.R;
import co.com.app.homerepair.utils.BitmapScaler;
import co.com.app.homerepair.view.fragment.CargaImagenFragment.OnListFragmentInteractionListener;
import co.com.app.homerepair.view.fragment.content.ImagenContent.Item;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Item} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ItemImagenAdapter extends RecyclerView.Adapter<ItemImagenAdapter.ViewHolder> {

    private final List<Item> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;
    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 200;

    public ItemImagenAdapter(List<Item> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_image, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setImageBitmap(scaleBitmap(R.mipmap.ic_image_load));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.mItem);
                    mListener.onImageViewInteraction(holder.mIdView);
                }
            }
        });
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
        public final ImageView mIdView;
        public Item mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.icon_item_image);
        }

    }
}
