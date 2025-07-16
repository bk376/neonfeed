package com.hackernews.futuristic.ui.detail;

import com.hackernews.futuristic.data.analyzer.CommentThreadAnalyzer;
import com.hackernews.futuristic.data.repository.HackerNewsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class StoryDetailViewModel_Factory implements Factory<StoryDetailViewModel> {
  private final Provider<HackerNewsRepository> repositoryProvider;

  private final Provider<CommentThreadAnalyzer> commentThreadAnalyzerProvider;

  public StoryDetailViewModel_Factory(Provider<HackerNewsRepository> repositoryProvider,
      Provider<CommentThreadAnalyzer> commentThreadAnalyzerProvider) {
    this.repositoryProvider = repositoryProvider;
    this.commentThreadAnalyzerProvider = commentThreadAnalyzerProvider;
  }

  @Override
  public StoryDetailViewModel get() {
    return newInstance(repositoryProvider.get(), commentThreadAnalyzerProvider.get());
  }

  public static StoryDetailViewModel_Factory create(
      Provider<HackerNewsRepository> repositoryProvider,
      Provider<CommentThreadAnalyzer> commentThreadAnalyzerProvider) {
    return new StoryDetailViewModel_Factory(repositoryProvider, commentThreadAnalyzerProvider);
  }

  public static StoryDetailViewModel newInstance(HackerNewsRepository repository,
      CommentThreadAnalyzer commentThreadAnalyzer) {
    return new StoryDetailViewModel(repository, commentThreadAnalyzer);
  }
}
