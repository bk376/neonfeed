package com.hackernews.futuristic.ui.summary;

import com.hackernews.futuristic.data.analyzer.NeuralSynthesizer;
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
public final class DailySummaryViewModel_Factory implements Factory<DailySummaryViewModel> {
  private final Provider<HackerNewsRepository> repositoryProvider;

  private final Provider<NeuralSynthesizer> neuralSynthesizerProvider;

  public DailySummaryViewModel_Factory(Provider<HackerNewsRepository> repositoryProvider,
      Provider<NeuralSynthesizer> neuralSynthesizerProvider) {
    this.repositoryProvider = repositoryProvider;
    this.neuralSynthesizerProvider = neuralSynthesizerProvider;
  }

  @Override
  public DailySummaryViewModel get() {
    return newInstance(repositoryProvider.get(), neuralSynthesizerProvider.get());
  }

  public static DailySummaryViewModel_Factory create(
      Provider<HackerNewsRepository> repositoryProvider,
      Provider<NeuralSynthesizer> neuralSynthesizerProvider) {
    return new DailySummaryViewModel_Factory(repositoryProvider, neuralSynthesizerProvider);
  }

  public static DailySummaryViewModel newInstance(HackerNewsRepository repository,
      NeuralSynthesizer neuralSynthesizer) {
    return new DailySummaryViewModel(repository, neuralSynthesizer);
  }
}
