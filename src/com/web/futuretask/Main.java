package com.web.futuretask;

import java.util.concurrent.ExecutionException;

/**
 * Ceated by web on 15/6/22.
 *
 * @desc 测试用例
 * 在子线程加载数据完成之前，主线程一直在阻塞
 *
 */
public class Main {

    public static void main(String[] a){

        PreLoadProductInfo preLoadProductInfo=new PreLoadProductInfo();
        preLoadProductInfo.start();
        try {

            System.out.println(" load data 1 ");
            //
            ProductInfo productInfo = preLoadProductInfo.get();

            System.out.println(" load date 2 ");
            System.out.println(productInfo.getName());

            System.out.println(" load data finished.");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
