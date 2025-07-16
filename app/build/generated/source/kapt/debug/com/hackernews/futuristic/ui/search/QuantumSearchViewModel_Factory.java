package com.hackernews.futuristic.ui.search;

import com.hackernews.futuristic.data.analyzer.QuantumSearchEngine;
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
public final class QuantumSearchViewModel_Factory implements Factory<QuantumSearchViewModel> {
  private final Provider<HackerNewsRepository> repositoryProvider;

  private final Provider<QuantumSearchEngine> quantumSearchEngineProvider;

  public QuantumSearchViewModel_Factory(Provider<HackerNewsRepository> repositoryProvider,
      Provider<QuantumSearchEngine> quantumSearchEngineProvider) {
    this.repositoryProvider = repositoryProvider;
    this.quantumSearchEngineProvider = quantumSearchEngineProvider;
  }

  @Override
  public QuantumSearchViewModel get() {
    return newInstance(repositoryProvider.get(), quantumSearchEngineProvider.get());
  }

  public static QuantumSearchViewModel_Factory create(
      Provider<HackerNewsRepository> repositoryProvider,
      Provider<QuantumSearchEngine> quantumSearchEngineProvider) {
    return new QuantumSearchViewModel_Factory(repositoryProvider, quantumSearchEngineProvider);
  }

  public static QuantumSearchViewModel newInstance(HackerNewsRepository repository,
      QuantumSearchEngine quantumSearchEngine) {
    return new QuantumSearchViewModel(repository, quantumSearchEngine);
  }
}
