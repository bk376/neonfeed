package com.hackernews.futuristic.ui.summary;

import com.hackernews.futuristic.data.analyzer.NeuralSynthesizer;
import com.hackernews.futuristic.data.repository.HackerNewsRepository;
import com.hackernews.futuristic.data.service.CloudLLMService;
import com.hackernews.futuristic.data.service.GeminiApiService;
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

  private final Provider<CloudLLMService> cloudLLMServiceProvider;

  private final Provider<GeminiApiService> geminiApiServiceProvider;

  public DailySummaryViewModel_Factory(Provider<HackerNewsRepository> repositoryProvider,
      Provider<NeuralSynthesizer> neuralSynthesizerProvider,
      Provider<CloudLLMService> cloudLLMServiceProvider,
      Provider<GeminiApiService> geminiApiServiceProvider) {
    this.repositoryProvider = repositoryProvider;
    this.neuralSynthesizerProvider = neuralSynthesizerProvider;
    this.cloudLLMServiceProvider = cloudLLMServiceProvider;
    this.geminiApiServiceProvider = geminiApiServiceProvider;
  }

  @Override
  public DailySummaryViewModel get() {
    return newInstance(repositoryProvider.get(), neuralSynthesizerProvider.get(), cloudLLMServiceProvider.get(), geminiApiServiceProvider.get());
  }

  public static DailySummaryViewModel_Factory create(
      Provider<HackerNewsRepository> repositoryProvider,
      Provider<NeuralSynthesizer> neuralSynthesizerProvider,
      Provider<CloudLLMService> cloudLLMServiceProvider,
      Provider<GeminiApiService> geminiApiServiceProvider) {
    return new DailySummaryViewModel_Factory(repositoryProvider, neuralSynthesizerProvider, cloudLLMServiceProvider, geminiApiServiceProvider);
  }

  public static DailySummaryViewModel newInstance(HackerNewsRepository repository,
      NeuralSynthesizer neuralSynthesizer, CloudLLMService cloudLLMService,
      GeminiApiService geminiApiService) {
    return new DailySummaryViewModel(repository, neuralSynthesizer, cloudLLMService, geminiApiService);
  }
}
