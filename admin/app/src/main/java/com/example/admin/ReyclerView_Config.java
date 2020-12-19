package com.example.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReyclerView_Config {
    private Context mContext ;

    private EmplsAdapter mEmplsAdapter ;
    public void setConfig (RecyclerView recyclerView, Context context, List<Empl> empls , List<String> keys) {
        mContext = context ;
        mEmplsAdapter = new EmplsAdapter(empls,keys) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mEmplsAdapter);

    }


    class EmplItemView extends RecyclerView.ViewHolder{
        private TextView mName , mPass , mState ;
        private String key ;

        public EmplItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.empl_list_item,parent,false));

            mName= (TextView) itemView.findViewById(R.id.name_textView);
            mPass= (TextView) itemView.findViewById(R.id.pass_textView);
            mState= (TextView) itemView.findViewById(R.id.state_txtView);
        }
        public void bind (Empl empl , String key) {
            mName.setText(empl.getName());
            mPass.setText(empl.getPassword());
            mPass.setText(empl.getState());
            this.key = key ;
        }
    }
    class EmplsAdapter extends RecyclerView.Adapter<EmplItemView> {
        private List <Empl> mEmplList ;
        private List <String> mKeys ;
        public EmplsAdapter(List<Empl> mEmplList , List<String> mKeys) {
            this.mEmplList=mEmplList;
            this.mKeys=mKeys;
        }

        @NonNull
        @Override
        public EmplItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new EmplItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull EmplItemView holder, int position) {
            holder.bind(mEmplList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mEmplList.size();
        }
    }
}
