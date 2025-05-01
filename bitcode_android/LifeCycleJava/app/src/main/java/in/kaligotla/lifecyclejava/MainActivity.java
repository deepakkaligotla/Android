package in.kaligotla.lifecyclejava;

import android.app.Activity;
import android.app.ComponentCaller;
import android.app.DirectAction;
import android.app.Fragment;
import android.app.PictureInPictureUiState;
import android.app.assist.AssistContent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Display;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.TaskStackBuilder;
import androidx.core.graphics.Insets;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("onCreate","onCreate");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Log.e("attachBaseContext","attachBaseContext");
    }

    @Override
    public void setTheme(@Nullable Resources.Theme theme) {
        super.setTheme(theme);
        Log.e("setTheme","setTheme");
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        Log.e("onPostCreate","onPostCreate");
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        Log.e("setSupportActionBar","setSupportActionBar");
    }

    @NonNull
    @Override
    public MenuInflater getMenuInflater() {
        Log.e("getMenuInflater","getMenuInflater");
        return super.getMenuInflater();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        Log.e("setContentView","setContentView");
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
        Log.e("addContentView","addContentView");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("onConfigurationChanged","onConfigurationChanged");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.e("onPostResume","onPostResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy","onDestroy");
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        Log.e("onTitleChanged","onTitleChanged");
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);
        Log.e("onChildTitleChanged","onChildTitleChanged");
    }

    @Override
    public void invalidateMenu() {
        super.invalidateMenu();
        Log.e("invalidateMenu","invalidateMenu");
    }

    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
        Log.e("invalidateOptionsMenu","invalidateOptionsMenu");
    }

    @Override
    public void onSupportActionModeStarted(@NonNull ActionMode mode) {
        super.onSupportActionModeStarted(mode);
        Log.e("onSupportActionModeStarted","onSupportActionModeStarted");
    }

    @Override
    public void onSupportActionModeFinished(@NonNull ActionMode mode) {
        super.onSupportActionModeFinished(mode);
        Log.e("onSupportActionModeFinished","onSupportActionModeFinished");
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(@NonNull ActionMode.Callback callback) {
        Log.e("onWindowStartingSupportActionMode","onWindowStartingSupportActionMode");
        return super.onWindowStartingSupportActionMode(callback);
    }

    @Nullable
    @Override
    public ActionMode startSupportActionMode(@NonNull ActionMode.Callback callback) {
        Log.e("startSupportActionMode","startSupportActionMode");
        return super.startSupportActionMode(callback);
    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
        Log.e("onCreateSupportNavigateUpTaskStack","onCreateSupportNavigateUpTaskStack");
    }

    @Override
    public void onPrepareSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onPrepareSupportNavigateUpTaskStack(builder);
        Log.e("onPrepareSupportNavigateUpTaskStack","onPrepareSupportNavigateUpTaskStack");
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.e("onSupportNavigateUp","onSupportNavigateUp");
        return super.onSupportNavigateUp();
    }

    @Nullable
    @Override
    public Intent getSupportParentActivityIntent() {
        Log.e("getSupportParentActivityIntent","getSupportParentActivityIntent");
        return super.getSupportParentActivityIntent();
    }

    @Override
    public boolean supportShouldUpRecreateTask(@NonNull Intent targetIntent) {
        Log.e("supportShouldUpRecreateTask","supportShouldUpRecreateTask");
        return super.supportShouldUpRecreateTask(targetIntent);
    }

    @Override
    public void supportNavigateUpTo(@NonNull Intent upIntent) {
        super.supportNavigateUpTo(upIntent);
        Log.e("supportNavigateUpTo","supportNavigateUpTo");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Log.e("onContentChanged","onContentChanged");
    }

    @Nullable
    @Override
    public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate() {
        Log.e("getDrawerToggleDelegate","getDrawerToggleDelegate");
        return super.getDrawerToggleDelegate();
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Log.e("onMenuOpened","onMenuOpened");
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onPanelClosed(int featureId, @NonNull Menu menu) {
        super.onPanelClosed(featureId, menu);
        Log.e("onPanelClosed","onPanelClosed");
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.e("dispatchKeyEvent","dispatchKeyEvent");
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("onKeyDown","onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();
        Log.e("openOptionsMenu","openOptionsMenu");
    }

    @Override
    public void closeOptionsMenu() {
        super.closeOptionsMenu();
        Log.e("closeOptionsMenu","closeOptionsMenu");
    }

    @Override
    protected void onNightModeChanged(int mode) {
        super.onNightModeChanged(mode);
        Log.e("onNightModeChanged","onNightModeChanged");
    }

    @Override
    protected void onLocalesChanged(@NonNull LocaleListCompat locales) {
        super.onLocalesChanged(locales);
        Log.e("onLocalesChanged","onLocalesChanged");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        Log.e("onAttachFragment","onAttachFragment");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e("onAttachedToWindow","onAttachedToWindow");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("onSaveInstanceState","onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("onRestoreInstanceState","onRestoreInstanceState");
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        Log.e("onApplyThemeResource","onApplyThemeResource");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("onRestart","onRestart");
    }

    @Nullable
    @Override
    public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback, int type) {
        Log.e("onWindowStartingActionMode","onWindowStartingActionMode");
        return super.onWindowStartingActionMode(callback, type);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Log.e("onContextItemSelected","onContextItemSelected");
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.e("onCreateOptionsMenu","onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        Log.e("onGenericMotionEvent","onGenericMotionEvent");
        return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.e("onKeyLongPress","onKeyLongPress");
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        Log.e("onKeyMultiple","onKeyMultiple");
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        Log.e("onKeyShortcut","onKeyShortcut");
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.e("onKeyUp","onKeyUp");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onNavigateUp() {
        Log.e("onNavigateUp","onNavigateUp");
        return super.onNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.e("onOptionsItemSelected","onOptionsItemSelected");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPictureInPictureRequested() {
        Log.e("onPictureInPictureRequested","onPictureInPictureRequested");
        return super.onPictureInPictureRequested();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.e("onPrepareOptionsMenu","onPrepareOptionsMenu");
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onSearchRequested(@Nullable SearchEvent searchEvent) {
        Log.e("onSearchRequested","onSearchRequested");
        return super.onSearchRequested(searchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("onTouchEvent","onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        Log.e("onTrackballEvent","onTrackballEvent");
        return super.onTrackballEvent(event);
    }

    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        Log.e("onCreateDescription","onCreateDescription");
        return super.onCreateDescription();
    }

    @Override
    public Uri onProvideReferrer() {
        Log.e("onProvideReferrer","onProvideReferrer");
        return super.onProvideReferrer();
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        Log.e("onCreatePanelView","onCreatePanelView");
        return super.onCreatePanelView(featureId);
    }

    @Override
    public void onActionModeFinished(android.view.ActionMode mode) {
        super.onActionModeFinished(mode);
        Log.e("onActionModeFinished","onActionModeFinished");
    }

    @Override
    public void onActionModeStarted(android.view.ActionMode mode) {
        super.onActionModeStarted(mode);
        Log.e("onActionModeStarted","onActionModeStarted");
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        Log.e("onActivityReenter","onActivityReenter");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);
        Log.e("onActivityResult","onActivityResult");
    }

    @Override
    public void onContextMenuClosed(@NonNull Menu menu) {
        super.onContextMenuClosed(menu);
        Log.e("onContextMenuClosed","onContextMenuClosed");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.e("onCreateContextMenu","onCreateContextMenu");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("onDetachedFromWindow","onDetachedFromWindow");
    }

    @Override
    public void onGetDirectActions(@NonNull CancellationSignal cancellationSignal, @NonNull Consumer<List<DirectAction>> callback) {
        super.onGetDirectActions(cancellationSignal, callback);
        Log.e("onGetDirectActions","onGetDirectActions");
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        Log.e("onEnterAnimationComplete","onEnterAnimationComplete");
    }

    @Override
    public void onLocalVoiceInteractionStopped() {
        super.onLocalVoiceInteractionStopped();
        Log.e("onLocalVoiceInteractionStopped","onLocalVoiceInteractionStopped");
    }

    @Override
    public void onLocalVoiceInteractionStarted() {
        super.onLocalVoiceInteractionStarted();
        Log.e("onLocalVoiceInteractionStarted","onLocalVoiceInteractionStarted");
    }

    @Override
    public void onNewIntent(@NonNull Intent intent, @NonNull ComponentCaller caller) {
        super.onNewIntent(intent, caller);
        Log.e("onNewIntent","onNewIntent");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e("onLowMemory","onLowMemory");
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        Log.e("onOptionsMenuClosed","onOptionsMenuClosed");
    }

    @Override
    public void onPerformDirectAction(@NonNull String actionId, @NonNull Bundle arguments, @NonNull CancellationSignal cancellationSignal, @NonNull Consumer<Bundle> resultListener) {
        super.onPerformDirectAction(actionId, arguments, cancellationSignal, resultListener);
        Log.e("onPerformDirectAction","onPerformDirectAction");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
        Log.e("onPointerCaptureChanged","onPointerCaptureChanged");
    }

    @Override
    public void onCreateNavigateUpTaskStack(android.app.TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
        Log.e("onCreateNavigateUpTaskStack","onCreateNavigateUpTaskStack");
    }

    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        super.onProvideAssistContent(outContent);
        Log.e("onProvideAssistContent","onProvideAssistContent");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.e("onCreate","onCreate");
    }

    @Override
    public void onPictureInPictureUiStateChanged(@NonNull PictureInPictureUiState pipState) {
        super.onPictureInPictureUiStateChanged(pipState);
        Log.e("onPictureInPictureUiStateChanged","onPictureInPictureUiStateChanged");
    }

    @Override
    public void onPrepareNavigateUpTaskStack(android.app.TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
        Log.e("onPrepareNavigateUpTaskStack","onPrepareNavigateUpTaskStack");
    }

    @Override
    public void onProvideAssistData(Bundle data) {
        super.onProvideAssistData(data);
        Log.e("onProvideAssistData","onProvideAssistData");
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
        Log.e("onProvideKeyboardShortcuts","onProvideKeyboardShortcuts");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);
        Log.e("onRequestPermissionsResult","onRequestPermissionsResult");
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.e("onRestoreInstanceState","onRestoreInstanceState");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("onSaveInstanceState","onSaveInstanceState");
    }

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
        Log.e("onTopResumedActivityChanged","onTopResumedActivityChanged");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult","onActivityResult");
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Log.e("onUserInteraction","onUserInteraction");
    }

    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        Log.e("onNewIntent","onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause","onPause");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.e("onPostCreate","onPostCreate");
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
        Log.e("onWindowAttributesChanged","onWindowAttributesChanged");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume","onResume");
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        Log.e("onResumeFragments","onResumeFragments");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.e("onUserLeaveHint","onUserLeaveHint");
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        Log.e("onCreatePanelMenu","onCreatePanelMenu");
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, @Nullable View view, @NonNull Menu menu) {
        Log.e("onPreparePanel","onPreparePanel");
        return super.onPreparePanel(featureId, view, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Log.e("onCreateView","onCreateView");
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, @NonNull Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        Log.e("onMultiWindowModeChanged","onMultiWindowModeChanged");
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, @NonNull Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        Log.e("onPictureInPictureModeChanged","onPictureInPictureModeChanged");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("onRequestPermissionsResult","onRequestPermissionsResult");
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        Log.e("onStateNotSaved","onStateNotSaved");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.e("onTrimMemory","onTrimMemory");
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        Log.e("onCreateThumbnail","onCreateThumbnail");
        return super.onCreateThumbnail(outBitmap, canvas);
    }

    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        Log.e("onNavigateUpFromChild","onNavigateUpFromChild");
        return super.onNavigateUpFromChild(child);
    }

    @Override
    public void onVisibleBehindCanceled() {
        super.onVisibleBehindCanceled();
        Log.e("onVisibleBehindCanceled","onVisibleBehindCanceled");
    }

    @NonNull
    @Override
    public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {
        Log.e("getOnBackInvokedDispatcher","getOnBackInvokedDispatcher");
        return super.getOnBackInvokedDispatcher();
    }

    @Nullable
    @Override
    public Display getDisplay() {
        Log.e("getDisplay","getDisplay");
        return super.getDisplay();
    }
}