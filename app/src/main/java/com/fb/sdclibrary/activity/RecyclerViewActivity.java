package com.fb.sdclibrary.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import com.fb.sdclibrary.DataManager;
import com.fb.sdclibrary.R;
import com.fb.sdclibrary.adapter.BaseRecyclerAdapter;
import com.fb.sdclibrary.adapter.RecyclerViewHolder;
import com.fb.sdclibrary.event.OnRecyclerItemClickListener;
import com.fb.sdclibrary.event.ItemTouchHelperCallback;
import com.fb.sdclibrary.widget.CustomToast;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class RecyclerViewActivity extends BaseActivity {

    @BindView(R.id.rv_list_data)
    RecyclerView rv_list_data;

    private List<String> mDatas;
    private BaseRecyclerAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

    }

    @Override
    protected void initView() {
        mDatas = DataManager.getData(20);
        mAdapter = new BaseRecyclerAdapter<String>(this, mDatas) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.list_item_data;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, String item) {
                holder.getTextView(R.id.tv_fruit_name).setText(item);
            }
        };
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv_list_data.setLayoutManager(layoutManager);
        rv_list_data.setAdapter(mAdapter);

        rv_list_data.addOnItemTouchListener(new OnRecyclerItemClickListener(rv_list_data) {

            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
//                RecyclerViewHolder holder = (RecyclerViewHolder) viewHolder;
//                holder.getTextView(R.id.tv_fruit_name).setText("123456");
                int position = viewHolder.getAdapterPosition();
                CustomToast.getInstance(RecyclerViewActivity.this).showToastBottomShort("点击: " + mDatas.get(position));
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder viewHolder) {
//                RecyclerViewHolder holder = (RecyclerViewHolder) viewHolder;
//                holder.getTextView(R.id.tv_fruit_name).setText("654321");
                int position = viewHolder.getAdapterPosition();
                CustomToast.getInstance(RecyclerViewActivity.this).showToastBottomShort("长按: " + mDatas.get(position));
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(rv_list_data);
    }

    ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback(){
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            if (toPosition == 0) {
                return false;
            }
//            if (fromPosition < toPosition) {
//                for (int i = fromPosition; i < toPosition; i++) {
//                    Collections.swap(mDatas, i, i + 1);
//                }
//            } else {
//                for (int i = fromPosition; i > toPosition; i--) {
//                    Collections.swap(mDatas, i, i - 1);
//                }
//            }
            Collections.swap(mDatas, fromPosition, toPosition);
            mAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int adapterPosition = viewHolder.getAdapterPosition();
            mDatas.remove(adapterPosition);
            mAdapter.notifyItemRemoved(adapterPosition);
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }
    };
}
