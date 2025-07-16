package com.hackernews.futuristic.work;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.hackernews.futuristic.data.repository.HackerNewsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class DailySummaryWorker_Factory {
  private final Provider<HackerNewsRepository> repositoryProvider;

  public DailySummaryWorker_Factory(Provider<HackerNewsRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  public DailySummaryWorker get(Context context, WorkerParameters params) {
    return newInstance(context, params, repositoryProvider.get());
  }

  public static DailySummaryWorker_Factory create(
      Provider<HackerNewsRepository> repositoryProvider) {
    return new DailySummaryWorker_Factory(repositoryProvider);
  }

  public static DailySummaryWorker newInstance(Context context, WorkerParameters params,
      HackerNewsRepository repository) {
    return new DailySummaryWorker(context, params, repository);
  }
}
