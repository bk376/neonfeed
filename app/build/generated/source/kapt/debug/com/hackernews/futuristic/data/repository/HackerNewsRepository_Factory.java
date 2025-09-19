package com.hackernews.futuristic.data.repository;

import com.hackernews.futuristic.data.api.HackerNewsApi;
import com.hackernews.futuristic.data.local.dao.BookmarkDao;
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

  private final Provider<BookmarkDao> bookmarkDaoProvider;

  public HackerNewsRepository_Factory(Provider<HackerNewsApi> apiProvider,
      Provider<StoryDao> storyDaoProvider, Provider<CommentDao> commentDaoProvider,
      Provider<BookmarkDao> bookmarkDaoProvider) {
    this.apiProvider = apiProvider;
    this.storyDaoProvider = storyDaoProvider;
    this.commentDaoProvider = commentDaoProvider;
    this.bookmarkDaoProvider = bookmarkDaoProvider;
  }

  @Override
  public HackerNewsRepository get() {
    return newInstance(apiProvider.get(), storyDaoProvider.get(), commentDaoProvider.get(), bookmarkDaoProvider.get());
  }

  public static HackerNewsRepository_Factory create(Provider<HackerNewsApi> apiProvider,
      Provider<StoryDao> storyDaoProvider, Provider<CommentDao> commentDaoProvider,
      Provider<BookmarkDao> bookmarkDaoProvider) {
    return new HackerNewsRepository_Factory(apiProvider, storyDaoProvider, commentDaoProvider, bookmarkDaoProvider);
  }

  public static HackerNewsRepository newInstance(HackerNewsApi api, StoryDao storyDao,
      CommentDao commentDao, BookmarkDao bookmarkDao) {
    return new HackerNewsRepository(api, storyDao, commentDao, bookmarkDao);
  }
}
