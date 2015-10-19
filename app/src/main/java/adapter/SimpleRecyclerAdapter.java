package adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iotech.discover.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrien on 14/10/2015.
 */
public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.VersionViewHolder> {
    //ColorGenerator generator = ColorGenerator.MATERIAL;
    List<String> noms;
    List<String> types;
    Boolean isHomeList = false;
    //String letter;

    public static List<String> homeActivitiesList = new ArrayList<String>();
    public static List<String> homeActivitiesSubList = new ArrayList<String>();
    Context context;
    OnItemClickListener clickListener;


    public void setGuidesList(Context context) {
        String[] listArray = context.getResources().getStringArray(R.array.home_activities);
        String[] subTitleArray = context.getResources().getStringArray(R.array.home_activities_subtitle);
        for (int i = 0; i < listArray.length; ++i) {
            homeActivitiesList.add(listArray[i]);
            homeActivitiesSubList.add(subTitleArray[i]);
        }
    }

    /*public SimpleRecyclerAdapter(Context context) {
        isHomeList = true;
        this.context = context;
        setGuidesList(context);
    }*/

    public SimpleRecyclerAdapter(List<String> noms, List<String> types) {
        isHomeList = false;
        this.noms = noms;
        this.types = types;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerlist_item, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
 /*       versionViewHolder.title.setText(versionModels.get(i));
//        Get the first letter of list item
        letter = String.valueOf(versionModels.get(i).charAt(0));

//        Create a new TextDrawable for our image's background
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letter, generator.getRandomColor());

        versionViewHolder.cardItemLayout..letter.setImageDrawable(drawable);
*/
        if (isHomeList) {
            versionViewHolder.title.setText(homeActivitiesList.get(i));
            versionViewHolder.subTitle.setText(homeActivitiesSubList.get(i));
        } else {
            versionViewHolder.title.setText(noms.get(i));
            versionViewHolder.subTitle.setText(types.get(i));
        }
    }

    @Override
    public int getItemCount() {
        if (isHomeList)
            return homeActivitiesList == null ? 0 : homeActivitiesList.size();
        else
            return noms == null ? 0 : noms.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardItemLayout;
        TextView title;
        TextView subTitle;

        public VersionViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            title = (TextView) itemView.findViewById(R.id.listitem_name);
            subTitle = (TextView) itemView.findViewById(R.id.listitem_subname);

            itemView.setOnClickListener(this);

/*            if (isHomeList) {
                itemView.setOnClickListener(this);
            } else {
                //subTitle.setVisibility(View.GONE);
            }*/

        }

        @Override
        public void onClick(View v) {
            //clickListener.onItemClick(v, getLayoutPosition());
            int position = getLayoutPosition(); // gets item position
            Snackbar.make(v, noms.get(position), Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .setActionTextColor(Color.RED)
                    .show();
            System.out.println(position);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
