package com.babuwyt.daili.utils.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.babuwyt.daili.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JIGUANG-Example";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Bundle bundle = intent.getExtras();
            Log.d("打印获取到的bundle", printBundle(bundle));
            JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
            if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
                String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
                Log.d("Registration", regId);
            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
                Log.d("==推送自定义消息==", bundle.getString(JPushInterface.EXTRA_MESSAGE));
                processCustomMessage(context, bundle);
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//                processCustomMessage1(context);
                SoundPool soundPool;
                if (Build.VERSION.SDK_INT >= 21) {
                    SoundPool.Builder builder = new SoundPool.Builder();
                    //传入音频的数量
                    builder.setMaxStreams(1);
                    //AudioAttributes是一个封装音频各种属性的类
                    AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
                    //设置音频流的合适属性
                    attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
                    builder.setAudioAttributes(attrBuilder.build());
                    soundPool = builder.build();
                } else {
                    //第一个参数是可以支持的声音数量，第二个是声音类型，第三个是声音品质
                    soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
                }
                //第一个参数Context,第二个参数资源Id，第三个参数优先级
                soundPool.load(context, R.raw.order_comming, 1);
                soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                        soundPool.play(1, 1, 1, 0, 0, 1);
                    }
                });
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
                Logger.d("打开通知", "");
//                openActivity(context,type,bundle);

            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
                Logger.d(TAG, "[MyReceiver-] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
            } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
                boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
                Logger.w(TAG, "[MyReceiver-]" + intent.getAction() + " connected state change to " + connected);
            } else {
                Logger.d(TAG, "[MyReceiver-] Unhandled intent - " + intent.getAction());
            }
        } catch (Exception e) {
            Log.d("==error==", e + "");
        }
    }

//    private void openActivity(Context context, String type, Bundle bundle) {
//        if (UHelper.isAppAlive(context,"com.babuwyt.siji")){
//
//
//            Intent mainIntent = new Intent(context, MainActivity.class);
//            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            Intent i = new Intent();
//            switch (type) {
//                case "2":
//                case "6":
//                    i.setClass(context, MyWalletActivity.class);
//                    i.putExtras(bundle);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
//                    Intent[] intents = {mainIntent, i};
//                    context.startActivities(intents);
//                    break;
//                default:
//                    i.setClass(context, MsgActivity.class);
//                    i.putExtras(bundle);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    Intent[] intents1 = {mainIntent, i};
//                    context.startActivities(intents1);
//                    break;
//            }
//        }
////        }else {
////            UHelper.showToast(context,"没有启动APP");
////            Intent launchIntent = context.getPackageManager().
////                    getLaunchIntentForPackage("com.babuwyt.siji");
////            launchIntent.setFlags(
////                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
////            launchIntent.putExtra(Constants.EXTRA_BUNDLE, type);
////            context.startActivity(launchIntent);
////        }
//    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            Log.d("==key==", key);
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    Logger.i(TAG, "没有extra");
                    continue;
                }
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();
                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" +
                                myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Logger.e(TAG, "Get message extra JSON error!");
                }

            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }

    //发送消息到首页
    private void processCustomMessage(Context context, Bundle bundle) {
        /**
         * 通知在这里处理
         */
//                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//                NotificationCompat.Builder notification = new NotificationCompat.Builder(context);
////                notification.setAutoCancel(true)
////                        .setContentText("自定义通知测试一下")
////                        .setContentTitle("极光测试")
////                        .setSmallIcon(R.mipmap.ic_launcher);
//
//                notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.auth_success));
////                Intent mIntent = new Intent(context,TestActivity.class);
////                mIntent.putExtras(bundle);
////                context.startActivity(mIntent);
////                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
//
////                notification.setContentIntent(pendingIntent);
//
//                notificationManager.notify(2, notification.build());
//        if (true) {
//            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//            if (!Util.isEmpty(extras)) {
//                try {
//                    JSONObject extraJson = new JSONObject(extras);
//                    if (extraJson.length() > 0) {
//                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//                    }
//                } catch (JSONException e) {
//                }
//            }
//            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//        }

    }

//    private void processCustomMessage1(Context context){
//        Intent msgIntent = new Intent(MainActivity.ACTION_NOTIFICATION_RECEIVED);
//        LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//    }


}
