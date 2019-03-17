package shixun.lj.bw.weak1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import shixun.lj.bw.weak1.mvp.adapter.Myadapter;
import shixun.lj.bw.weak1.mvp.data.Datas;
import shixun.lj.bw.weak1.mvp.presenter.Showpresenter;
import shixun.lj.bw.weak1.mvp.view.Showview;

public class MainActivity extends AppCompatActivity implements Showview {

    @BindView(R.id.recy)
    RecyclerView recy;
    private Showpresenter showpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showpresenter = new Showpresenter();

        showpresenter.attachview(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recy.setLayoutManager(linearLayoutManager);
        showpresenter.getdata();
    }

    @Override
    public void getdata(Object data) {
        Datas datas = (Datas) data;
        Myadapter myadapter = new Myadapter(datas, MainActivity.this);
        myadapter.setOnclick(new Myadapter.onclick() {
            @Override
            public void onclick(int id) {

            }

            @Override
            public void onlongclick(int id) {
                Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });
        recy.setAdapter(myadapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showpresenter.detachview();
    }
}
