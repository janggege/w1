package shixun.lj.bw.weak1.mvp.model;

import shixun.lj.bw.weak1.mvp.data.Datas;
import shixun.lj.bw.weak1.mvp.okhttp.Okhttputils;

/*
  name:刘江
  data:2019
*/public class ShowModel implements Imodel {
    String url = "http://172.17.8.100/small/commodity/v1/commodityList";

    @Override
    public void requestdata(final Callback callback) {
        Okhttputils.getinstance().doGet(url, Datas.class, new Okhttputils.NetCallBack() {
            @Override
            public void LoadSuccess(Object obj) {
                callback.Seccess(obj);
            }

            @Override
            public void LoadFail() {

            }
        });
    }
}
