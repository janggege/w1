package shixun.lj.bw.weak1.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import shixun.lj.bw.weak1.R;
import shixun.lj.bw.weak1.mvp.data.Datas;

/*
  name:刘江
  data:2019
*/public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Datas datas;
    Context context;
    public int one = 1;
    public int two = 2;


    public Myadapter(Datas datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return one;
        }
        return two;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        if (type == 1) {
            View view = LayoutInflater.from(context).inflate(R.layout.iteam, null, false);
            viholdre1 viholdre1 = new viholdre1(view);
            return viholdre1;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.iteam1, null, false);
            viholdre2 viholdre2 = new viholdre2(view);
            return viholdre2;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof viholdre1) {
            ((viholdre1) viewHolder).textView.setText(datas.getResult().getPzsh().getCommodityList().get(i).getCommodityName());
            String masterPic = datas.getResult().getPzsh().getCommodityList().get(i).getMasterPic();
            Glide.with(context).load(masterPic).into(((viholdre1) viewHolder).imageView);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.onclick(v.getId());

                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onclick.onlongclick(v.getId());
                    onremove(i);
                    return true;
                }
            });
        } else if (viewHolder instanceof viholdre2) {
            ((viholdre2) viewHolder).textView.setText(datas.getResult().getPzsh().getCommodityList().get(i).getCommodityName());
            String masterPic = datas.getResult().getPzsh().getCommodityList().get(i).getMasterPic();
            Glide.with(context).load(masterPic).into(((viholdre2) viewHolder).imageView);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.onclick(v.getId());
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onclick.onlongclick(v.getId());
                    onremove(i);
                    return true;
                }
            });
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.onlongclick(v.getId());
                    onclick.onclick(v.getId());

                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return datas.getResult().getPzsh().getCommodityList().size();
    }

    class viholdre1 extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public viholdre1(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
        }
    }

    class viholdre2 extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public viholdre2(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image1);
            textView = itemView.findViewById(R.id.text1);
        }
    }

    public interface onclick {
        void onclick(int id);

        void onlongclick(int id);
    }

    onclick onclick;

    public void setOnclick(Myadapter.onclick onclick) {
        this.onclick = onclick;
    }

    public void onremove(int i) {
        datas.getResult().getPzsh().getCommodityList().remove(i);
        notifyItemRemoved(i);//刷新被删除的地方
        notifyItemRangeChanged(i, getItemCount());


    }
}
