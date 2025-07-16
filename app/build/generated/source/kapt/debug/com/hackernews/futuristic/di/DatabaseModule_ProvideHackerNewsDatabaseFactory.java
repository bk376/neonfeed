package com.hackernews.futuristic.di;

import android.content.Context;
import com.hackernews.futuristic.data.local.HackerNewsDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideHackerNewsDatabaseFactory implements Factory<HackerNewsDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideHackerNewsDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public HackerNewsDatabase get() {
    return provideHackerNewsDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideHackerNewsDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideHackerNewsDatabaseFactory(contextProvider);
  }

  public static HackerNewsDatabase provideHackerNewsDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideHackerNewsDatabase(context));
  }
}
