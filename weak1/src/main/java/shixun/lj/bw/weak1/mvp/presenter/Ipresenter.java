package shixun.lj.bw.weak1.mvp.presenter;

import shixun.lj.bw.weak1.mvp.view.Showview;

/*
  name:刘江
  data:2019
*/public interface Ipresenter {
    void attachview(Showview showview);

    void detachview();

    void getdata();


}
