package com.web.bit;

/**
 * Created by erbao.wang on 2016/1/25.
 *
 * @desc http://www.qlcoder.com/task/751e
 *
 */
public class BitDemo {

    public static void  main(String[] args){

        int targetIndex=2333;
        int begin=1;
        int count=0;
        do{

            if(begin%2==0 || begin%3==0){
                count++;
                if(count == targetIndex){
                    System.out.println("the num is:"+begin);
                    break;
                }
            }
            begin++;

        }while (true);
    }
}
