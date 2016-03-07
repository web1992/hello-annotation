package com.web.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by erbao.wang on 2016/2/24.
 *
 * @desc
 */
public class ThreadLocalDemo {


    public static void main(String[] args) throws InterruptedException {

        System.out.println( 1l<<63);


//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//                System.out.println("run1");
//                try {
//                    System.out.println(DateUtil.formatDate("2016-02-26 16:44:28"));
////                    System.out.println(DateUtil.formatDateStr(new Date()));
//                    Thread.sleep(200000);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//        thread1.start();
////        Thread.sleep(1000);
//
//        Thread thread2 = new Thread() {
//            @Override
//            public void run() {
//                System.out.println("run2");
//                try {
//                    System.out.println(DateUtil.formatDate("2017-02-26 16:44:18"));
////                    System.out.println(DateUtil.formatDateStr(new Date()));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        thread2.start();
    }


    static class DateUtil {

        private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();


        private static SimpleDateFormat get() {
            if (null == threadLocal.get()) {
                threadLocal.set(dateFormat);
            }

            return threadLocal.get();


//         return    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }


        public static Date formatDate(String _date) throws ParseException {
            return get().parse(_date);
        }



        public static String formatDateStr(Date _date) throws ParseException {
            return get().format(_date);
        }

        public static SimpleDateFormat getDateFormat() {
            return get();
        }

    }

}




