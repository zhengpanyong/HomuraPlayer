package ljl.com.homuraproject.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import douzi.android.view.LrcRow;
import ljl.com.homuraproject.Adapter.LrcSelectionAdapter;
import ljl.com.homuraproject.Control.FileIO;
import ljl.com.homuraproject.R;

public class LrcSelectionActivity extends Activity {
    private Button lrc_ok_button;
    private Button lrc_cancel_button;
    private List<LrcRow> lrcRows;
    private ListView lrc_select_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lrc_selelction);
        lrcRows = getIntent().getParcelableArrayListExtra("LrcRows");
        initView();
    }

    private void initView() {
        this.lrc_ok_button = (Button) this.findViewById(R.id.lrc_ok_button);
        this.lrc_cancel_button = (Button) this.findViewById(R.id.lrc_cancel_button);
        this.lrc_select_view = (ListView) this.findViewById(R.id.lrc_select_view);
        final LrcSelectionAdapter lrcSelectionAdapter = new LrcSelectionAdapter(LrcSelectionActivity.this, lrcRows);
        this.lrc_select_view.setAdapter(lrcSelectionAdapter);
        lrcSelectionAdapter.notifyDataSetChanged();
        this.lrc_ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //return cover uri and lyrics to target activity
                Intent intent = new Intent();
                intent.setClass(LrcSelectionActivity.this, FileActivity.class);
                intent.putExtra("CoverUri", FileIO.getImageUri(FileActivity.getCurrentPlayingFile().getParentFile()));
                intent.putStringArrayListExtra("Content", lrcSelectionAdapter.getLrcSelectionResultByArray());
                setResult(0, intent);
                finish();
            }
        });
        this.lrc_cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do nothing
                finish();
            }
        });
    }
}
