package shixun.lj.bw.weak1.mvp.presenter;

import java.lang.ref.SoftReference;

import shixun.lj.bw.weak1.mvp.model.Imodel;
import shixun.lj.bw.weak1.mvp.model.ShowModel;
import shixun.lj.bw.weak1.mvp.view.Showview;

/*
  name:刘江
  data:2019
*/public class Showpresenter implements Ipresenter {

    private ShowModel showModel;
    private SoftReference<Imodel> imodelSoftReference;
    Showview showview;

    @Override
    public void attachview(Showview showview) {
        showModel = new ShowModel();
        imodelSoftReference = new SoftReference<Imodel>(showModel);
        this.showview = showview;
    }

    @Override
    public void detachview() {
        imodelSoftReference.clear();
    }

    @Override
    public void getdata() {
        showModel.requestdata(new Imodel.Callback() {
            @Override
            public void Seccess(Object data) {
                showview.getdata(data);
            }

            @Override
            public void error(Exception e) {

            }
        });


    }
}
