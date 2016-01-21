package com.sym.demopicasso;

/**
 * Created by Administrator on 2016/1/21.
 */
public class AsyncHttp {

    private CallBack callBack;

    public interface CallBack {
        public void success(String message);
        public void error(String message);
    }

    public void get(final CallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                String content = "the result from interect";
                if (content != null && !"".equals(content)) {
                    String result = content;
                    callBack.success(result);
                } else {
                    String result = "error";
                    callBack.error(result);
                }
            }
        }.start();
    }

}
