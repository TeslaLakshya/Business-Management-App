package rashitrends.rashitrends;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DimensionsEditor extends AppCompatActivity {
    EditText attribute, value;
    FloatingActionButton fab;
    List<String> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimensions_editor);
        attribute = findViewById(R.id.textView9);
        value = findViewById(R.id.textView8);
        fab = findViewById(R.id.fabitems);
        final DimensionAdapter adapter = new DimensionAdapter();
        RecyclerView recyclerView = findViewById(R.id.somerecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        try{
            Intent intent = getIntent();
            if(intent.hasExtra("dimensions")) {
                String lines[] = intent.getStringExtra("dimensions").split("\\r?\\n");
                for(String i : lines)
                    data.add(i);
                adapter.updateDimensions(data);
            }
        }catch (Exception e){}

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(attribute.getText().toString() + " : " + value.getText().toString());
                adapter.updateDimensions(data);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                data.remove(viewHolder.getAdapterPosition());
                adapter.updateDimensions(data);
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.hsave :
                Intent intent = new Intent();
                String s = "";
                for(String i : data) {
                    s = s + i + "\n";
                }
                intent.putExtra("dimensions", s.substring(0, s.length() - 1));
                setResult(RESULT_OK, intent);
                finish();
                //return true;
            default :
                return super.onOptionsItemSelected(item);

        }
    }
}
