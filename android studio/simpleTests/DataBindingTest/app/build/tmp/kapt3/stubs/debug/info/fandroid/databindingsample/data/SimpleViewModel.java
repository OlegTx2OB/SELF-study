package info.fandroid.databindingsample.data;

import java.lang.System;

/**
 * A simple VM for [com.example.android.databinding.basicsample.ui.PlainActivity].
 */
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0006R\u0011\u0010\u000e\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0014"}, d2 = {"Linfo/fandroid/databindingsample/data/SimpleViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "lastName", "", "getLastName", "()Ljava/lang/String;", "<set-?>", "", "likes", "getLikes", "()I", "name", "getName", "popularity", "Linfo/fandroid/databindingsample/data/Popularity;", "getPopularity", "()Linfo/fandroid/databindingsample/data/Popularity;", "onLike", "", "app_debug"})
public final class SimpleViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = "Grace";
    @org.jetbrains.annotations.NotNull
    private final java.lang.String lastName = "Hopper";
    private int likes = 0;
    
    public SimpleViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getLastName() {
        return null;
    }
    
    public final int getLikes() {
        return 0;
    }
    
    /**
     * Increments the number of likes.
     */
    public final void onLike() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final info.fandroid.databindingsample.data.Popularity getPopularity() {
        return null;
    }
}