package nickqiao.com.oahelper;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by chenyuqiao on 2017/6/15 .
 */

public class AutoInstallAccessibility extends AccessibilityService {

    private static String TAG = "Accessibility"; //AutoInstallAccessibility.class.getSimpleName();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event){
        int eventType = event.getEventType();//事件类型
        Log.d(TAG, "onAccessibilityEvent");
        switch(eventType){
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:// 通知栏事件
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED://窗体状态改变
                break;
            case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED://View获取到焦点
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_START:
                break;
            case AccessibilityEvent.TYPE_GESTURE_DETECTION_END:
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                break;
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                break;
            case AccessibilityEvent.TYPE_VIEW_SCROLLED:
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED:
                break;
        }
        for(CharSequence txt : event.getText()){
            Log.e(TAG, "text:" + txt);//输出当前事件包含的文本信息
        }
        try{
//            findAndPerformActionButton("继续");
//            findAndPerformActionButton("安装");
//            findAndPerformActionButton("下一步");
//            findAndPerformActionButton("打开");
            findAndPerformActionTextView("继续");
            findAndPerformActionTextView("下一步");
            findAndPerformActionTextView("安装");

            findAndPerformActionTextView("打开");
        }catch(Exception e){
        }
    }

    @Override
    public void onInterrupt(){
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void findAndPerformActionButton(String text){
        if(getRootInActiveWindow() == null)//取得当前激活窗体的根节点
            return;
        //通过文字找到当前的节点
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(text);
        for(int i = 0; i < nodes.size(); i++){
            AccessibilityNodeInfo node = nodes.get(i);
            // 执行点击行为
            if(node.getClassName().equals("android.widget.Button") && node.isEnabled()){
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void findAndPerformActionTextView(String text){
        if(getRootInActiveWindow() == null)
            return;
        //通过文字找到当前的节点
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(text);
        for(int i = 0; i < nodes.size(); i++){
            AccessibilityNodeInfo node = nodes.get(i);
            // 执行按钮点击行为
            if(node.getClassName().equals("android.widget.TextView") && node.isEnabled()){
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }
}
