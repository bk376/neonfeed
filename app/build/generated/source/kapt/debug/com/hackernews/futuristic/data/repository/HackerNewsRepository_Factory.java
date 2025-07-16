package com.hackernews.futuristic.data.repository;

import com.hackernews.futuristic.data.api.HackerNewsApi;
import com.hackernews.futuristic.data.local.dao.CommentDao;
import com.hackernews.futuristic.data.local.dao.StoryDao;
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

  private final Provider<StoryDao> storyDaoProvider;

  private final Provider<CommentDao> commentDaoProvider;

  public HackerNewsRepository_Factory(Provider<HackerNewsApi> apiProvider,
      Provider<StoryDao> storyDaoProvider, Provider<CommentDao> commentDaoProvider) {
    this.apiProvider = apiProvider;
    this.storyDaoProvider = storyDaoProvider;
    this.commentDaoProvider = commentDaoProvider;
  }

  @Override
  public HackerNewsRepository get() {
    return newInstance(apiProvider.get(), storyDaoProvider.get(), commentDaoProvider.get());
  }

  public static HackerNewsRepository_Factory create(Provider<HackerNewsApi> apiProvider,
      Provider<StoryDao> storyDaoProvider, Provider<CommentDao> commentDaoProvider) {
    return new HackerNewsRepository_Factory(apiProvider, storyDaoProvider, commentDaoProvider);
  }

  public static HackerNewsRepository newInstance(HackerNewsApi api, StoryDao storyDao,
      CommentDao commentDao) {
    return new HackerNewsRepository(api, storyDao, commentDao);
  }
}
