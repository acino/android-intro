package se.acino.todointro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.recyclerview)
    public RecyclerView recyclerView;
    @Bind(R.id.input)
    public TextView editText;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        adapter = createAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.button)
    public void onButtonClick() {
        saveTodo(editText.getText().toString());
        editText.setText(null);
    }

    private SimpleAdapter createAdapter() {
        return new SimpleAdapter();
    }

    private void saveTodo(String text) {
        adapter.add(text);
    }

    class SimpleAdapter extends RecyclerView.Adapter {
        private List<String> items = new ArrayList<>();

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_todo, parent, false);
            return new SimpleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((SimpleViewHolder) holder).text.setText(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items == null ? 0 : items.size();
        }

        public void add(String s) {
            items.add(s);
            notifyItemInserted(items.size() - 1);
        }
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.checkbox)
        public CheckBox checkbox;
        @Bind(R.id.text)
        public TextView text;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
