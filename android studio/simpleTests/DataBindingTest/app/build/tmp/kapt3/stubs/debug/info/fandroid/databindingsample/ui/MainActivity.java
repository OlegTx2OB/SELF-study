package info.fandroid.databindingsample.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0012H\u0002J\b\u0010\u0019\u001a\u00020\u0012H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u001a"}, d2 = {"Linfo/fandroid/databindingsample/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "viewModel", "error/NonExistentClass", "getViewModel", "()Lerror/NonExistentClass;", "viewModel$delegate", "Lerror/NonExistentClass;", "getAssociatedColor", "", "popularity", "Linfo/fandroid/databindingsample/data/Popularity;", "context", "Landroid/content/Context;", "getDrawablePopularity", "Landroid/graphics/drawable/Drawable;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onLike", "view", "Landroid/view/View;", "updateLikes", "updateName", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private final error.NonExistentClass viewModel$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    public MainActivity() {
        super();
    }
    
    private final error.NonExistentClass getViewModel() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * This method is triggered by the `android:onclick` attribute in the layout. It puts business
     * logic in the activity, which is not ideal. We should do something about that.
     */
    public final void onLike(@org.jetbrains.annotations.NotNull
    android.view.View view) {
    }
    
    /**
     * So much findViewById! We'll fix that with Data Binding.
     */
    private final void updateName() {
    }
    
    /**
     * This method has many problems:
     * - It's calling findViewById multiple times
     * - It has untestable logic
     * - It's updating two views when called even if they're not changing
     */
    private final void updateLikes() {
    }
    
    private final int getAssociatedColor(info.fandroid.databindingsample.data.Popularity popularity, android.content.Context context) {
        return 0;
    }
    
    private final android.graphics.drawable.Drawable getDrawablePopularity(info.fandroid.databindingsample.data.Popularity popularity, android.content.Context context) {
        return null;
    }
}