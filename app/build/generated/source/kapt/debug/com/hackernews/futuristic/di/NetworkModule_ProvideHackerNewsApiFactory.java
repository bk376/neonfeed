package com.hackernews.futuristic.di;

import com.hackernews.futuristic.data.api.HackerNewsApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideHackerNewsApiFactory implements Factory<HackerNewsApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideHackerNewsApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public HackerNewsApi get() {
    return provideHackerNewsApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideHackerNewsApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideHackerNewsApiFactory(retrofitProvider);
  }

  public static HackerNewsApi provideHackerNewsApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideHackerNewsApi(retrofit));
  }
}
