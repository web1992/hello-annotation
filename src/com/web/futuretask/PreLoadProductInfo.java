package com.web.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by web on 15/6/22.
 *
 * @desc 异步的加载数据，后续使用
 */
public class PreLoadProductInfo {

    private FutureTask<ProductInfo> futureTask = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }

    });


    private final Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }


    public ProductInfo get() throws ExecutionException, InterruptedException {
        try {
            return futureTask.get();
        } catch (Exception e) {
            Throwable throwable = e.getCause();
            if (throwable instanceof ExecutionException) {
                throw (ExecutionException) throwable;
            } else
                throw (InterruptedException) throwable;
        }
    }

    private ProductInfo loadProductInfo() {
        ProductInfo productInfo=new ProductInfo();
        try {
            // 暂停10秒
            Thread.sleep(10*1000);
            productInfo.setName("I`m a product.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return productInfo;
    }
}
