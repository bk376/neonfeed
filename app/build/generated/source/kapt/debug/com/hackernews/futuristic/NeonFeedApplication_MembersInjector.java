package com.hackernews.futuristic;

import androidx.hilt.work.HiltWorkerFactory;
import com.hackernews.futuristic.work.WorkManagerScheduler;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class NeonFeedApplication_MembersInjector implements MembersInjector<NeonFeedApplication> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  private final Provider<WorkManagerScheduler> workManagerSchedulerProvider;

  public NeonFeedApplication_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider,
      Provider<WorkManagerScheduler> workManagerSchedulerProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
    this.workManagerSchedulerProvider = workManagerSchedulerProvider;
  }

  public static MembersInjector<NeonFeedApplication> create(
      Provider<HiltWorkerFactory> workerFactoryProvider,
      Provider<WorkManagerScheduler> workManagerSchedulerProvider) {
    return new NeonFeedApplication_MembersInjector(workerFactoryProvider, workManagerSchedulerProvider);
  }

  @Override
  public void injectMembers(NeonFeedApplication instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
    injectWorkManagerScheduler(instance, workManagerSchedulerProvider.get());
  }

  @InjectedFieldSignature("com.hackernews.futuristic.NeonFeedApplication.workerFactory")
  public static void injectWorkerFactory(NeonFeedApplication instance,
      HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }

  @InjectedFieldSignature("com.hackernews.futuristic.NeonFeedApplication.workManagerScheduler")
  public static void injectWorkManagerScheduler(NeonFeedApplication instance,
      WorkManagerScheduler workManagerScheduler) {
    instance.workManagerScheduler = workManagerScheduler;
  }
}
