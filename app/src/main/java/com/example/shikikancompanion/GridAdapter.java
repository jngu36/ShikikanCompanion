package com.example.shikikancompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<indexHolder> dolls;

    public GridAdapter(Context context, ArrayList<indexHolder> arr) {
        this.context = context;
        dolls = arr;
    }

    @Override
    public int getCount() {
        return dolls.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public indexHolder getItem(int position) {
        return dolls.get(position);
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        indexHolder holder = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.img_index_xml, null);
        } else {
            v = convertView;
        }

        TextView indexNum = v.findViewById(R.id.index);
        TextView name = v.findViewById(R.id.name);
        ImageView nameBox = v.findViewById(R.id.nameBox);
        ImageView thumb = v.findViewById(R.id.thumbnail);
        ImageView border = v.findViewById(R.id.border);
        ImageView icon = v.findViewById(R.id.type);
        ImageView star = v.findViewById(R.id.star);

        Glide
                .with(context)
                .load(holder.getPicture())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading))
                .into(thumb);

        Glide
                .with(context)
                .load("https://jngu36.github.io/icon/border.png")
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading))
                .into(border);

        Glide
                .with(context)
                .load(getIcon(holder.getType(), holder.getRarity()))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading))
                .into(icon);

        Glide
                .with(context)
                .load(getStar(holder.getRarity()))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading))
                .into(star);

        Glide
                .with(context)
                .load(getBox(holder.getRarity()))
                .into(nameBox);

        indexNum.setText("" + holder.getId());

        if (holder.getName().length() > 12) {
            name.setTextSize(12);
            name.setText(holder.getName());
            name.setTextSize(14);
        } else
            name.setText(holder.getName());
        return v;
    }

    private String getBox(int rarity) {
        switch (rarity) {
            case 1:
                return "https://jngu36.github.io/icon/ebox.png";
            case 2:
                return "https://jngu36.github.io/icon/2box.png";
            case 3:
                return "https://jngu36.github.io/icon/3box.png";
            case 4:
                return "https://jngu36.github.io/icon/4box.png";
            case 5:
                return "https://jngu36.github.io/icon/5box.png";
            default:
                return "";
        }
    }

    private String getStar(int rarity) {
        switch (rarity) {
            case 2:
                return "https://jngu36.github.io/icon/2s.png";
            case 3:
                return "https://jngu36.github.io/icon/3s.png";
            case 4:
                return "https://jngu36.github.io/icon/4s.png";
            case 5:
                return "https://jngu36.github.io/icon/5s.png";
            default:
                return "";
        }
    }

    private String getIcon(String type, int rarity) {

        String[] HG = {
                "",
                "https://jngu36.github.io/icon/icon_HGExtra.png",
                "https://jngu36.github.io/icon/icon_HG2.png",
                "https://jngu36.github.io/icon/icon_HG3.png",
                "https://jngu36.github.io/icon/icon_HG4.png",
                "https://jngu36.github.io/icon/icon_HG5.png"
        };

        String[] AR = {
                "",
                "https://jngu36.github.io/icon/icon_ARExtra.png",
                "https://jngu36.github.io/icon/icon_AR2.png",
                "https://jngu36.github.io/icon/icon_AR3.png",
                "https://jngu36.github.io/icon/icon_AR4.png",
                "https://jngu36.github.io/icon/icon_AR5.png"
        };

        String[] SMG = {
                "",
                "https://jngu36.github.io/icon/icon_SMGExtra.png",
                "https://jngu36.github.io/icon/icon_SMG2.png",
                "https://jngu36.github.io/icon/icon_SMG3.png",
                "https://jngu36.github.io/icon/icon_SMG4.png",
                "https://jngu36.github.io/icon/icon_SMG5.png"
        };

        String[] RF = {
                "",
                "https://jngu36.github.io/icon/icon_RFExtra.png",
                "https://jngu36.github.io/icon/icon_RF2.png",
                "https://jngu36.github.io/icon/icon_RF3.png",
                "https://jngu36.github.io/icon/icon_RF4.png",
                "https://jngu36.github.io/icon/icon_RF5.png"
        };

        String[] MG = {
                "",
                "https://jngu36.github.io/icon/icon_RFExtra.png",
                "https://jngu36.github.io/icon/icon_MG2.png",
                "https://jngu36.github.io/icon/icon_MG3.png",
                "https://jngu36.github.io/icon/icon_MG4.png",
                "https://jngu36.github.io/icon/icon_MG5.png"
        };

        String[] SG = {
                "",
                "https://jngu36.github.io/icon/icon_SGExtra.png",
                "",
                "https://jngu36.github.io/icon/icon_SG3.png",
                "https://jngu36.github.io/icon/icon_SG4.png",
                "https://jngu36.github.io/icon/icon_SG5.png"
        };

        switch (type) {
            case "HG":
            case "HG-M":
                return HG[rarity];
            case "AR":
            case "AR-M":
                return AR[rarity];
            case "SMG":
            case "SMG-M":
                return SMG[rarity];
            case "RF":
            case "RF-M":
                return RF[rarity];
            case "MG":
            case "MG-M":
                return MG[rarity];
            case "SG":
            case "SG-M":
                return SG[rarity];
            case "HG-E":
                return HG[1];
            case "AR-E":
                return AR[1];
            case "SMG-E":
                return SMG[1];
            case "RF-E":
                return RF[1];
            case "SG-E":
                return SG[1];
            case "MG-E":
                return MG[1];
            default:
                return "";
        }
    }
}