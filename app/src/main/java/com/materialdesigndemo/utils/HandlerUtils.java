package com.materialdesigndemo.utils;

import android.os.Handler;
import android.os.Message;

/**
 * Created by KHQ on 2017/4/19.
 */

public class HandlerUtils {

    public static void sendMessage(Handler mHandler, int num){

        Message message = mHandler.obtainMessage(num);
        mHandler.sendMessage(message);

    }

}
