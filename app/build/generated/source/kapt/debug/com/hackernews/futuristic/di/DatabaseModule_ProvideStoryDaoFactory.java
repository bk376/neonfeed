package com.hackernews.futuristic.di;

import com.hackernews.futuristic.data.local.HackerNewsDatabase;
import com.hackernews.futuristic.data.local.dao.StoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideStoryDaoFactory implements Factory<StoryDao> {
  private final Provider<HackerNewsDatabase> databaseProvider;

  public DatabaseModule_ProvideStoryDaoFactory(Provider<HackerNewsDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public StoryDao get() {
    return provideStoryDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideStoryDaoFactory create(
      Provider<HackerNewsDatabase> databaseProvider) {
    return new DatabaseModule_ProvideStoryDaoFactory(databaseProvider);
  }

  public static StoryDao provideStoryDao(HackerNewsDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideStoryDao(database));
  }
}
