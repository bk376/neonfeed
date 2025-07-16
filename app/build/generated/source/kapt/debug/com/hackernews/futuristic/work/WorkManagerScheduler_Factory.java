package com.hackernews.futuristic.work;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class WorkManagerScheduler_Factory implements Factory<WorkManagerScheduler> {
  private final Provider<Context> contextProvider;

  public WorkManagerScheduler_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public WorkManagerScheduler get() {
    return newInstance(contextProvider.get());
  }

  public static WorkManagerScheduler_Factory create(Provider<Context> contextProvider) {
    return new WorkManagerScheduler_Factory(contextProvider);
  }

  public static WorkManagerScheduler newInstance(Context context) {
    return new WorkManagerScheduler(context);
  }
}
