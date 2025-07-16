package com.hackernews.futuristic.data.repository;

import com.hackernews.futuristic.data.api.HackerNewsApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class HackerNewsRepository_Factory implements Factory<HackerNewsRepository> {
  private final Provider<HackerNewsApi> apiProvider;

  public HackerNewsRepository_Factory(Provider<HackerNewsApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public HackerNewsRepository get() {
    return newInstance(apiProvider.get());
  }

  public static HackerNewsRepository_Factory create(Provider<HackerNewsApi> apiProvider) {
    return new HackerNewsRepository_Factory(apiProvider);
  }

  public static HackerNewsRepository newInstance(HackerNewsApi api) {
    return new HackerNewsRepository(api);
  }
}
