package com.hackernews.futuristic.di;

import com.hackernews.futuristic.data.local.HackerNewsDatabase;
import com.hackernews.futuristic.data.local.dao.CommentDao;
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
public final class DatabaseModule_ProvideCommentDaoFactory implements Factory<CommentDao> {
  private final Provider<HackerNewsDatabase> databaseProvider;

  public DatabaseModule_ProvideCommentDaoFactory(Provider<HackerNewsDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CommentDao get() {
    return provideCommentDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideCommentDaoFactory create(
      Provider<HackerNewsDatabase> databaseProvider) {
    return new DatabaseModule_ProvideCommentDaoFactory(databaseProvider);
  }

  public static CommentDao provideCommentDao(HackerNewsDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCommentDao(database));
  }
}
