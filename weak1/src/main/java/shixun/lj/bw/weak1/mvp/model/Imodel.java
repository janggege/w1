package shixun.lj.bw.weak1.mvp.model;

/*
  name:刘江
  data:2019
*/public interface Imodel {
    void requestdata(Callback callback);

    interface Callback {
        void Seccess(Object data);

        void error(Exception e);
    }
}
