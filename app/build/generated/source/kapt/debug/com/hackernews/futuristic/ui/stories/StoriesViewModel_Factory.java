package com.hackernews.futuristic.ui.stories;

import com.hackernews.futuristic.data.analyzer.NeuralNetworkAnalyzer;
import com.hackernews.futuristic.data.profile.UserProfileManager;
import com.hackernews.futuristic.data.repository.HackerNewsRepository;
import com.hackernews.futuristic.data.repository.SettingsRepository;
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
public final class StoriesViewModel_Factory implements Factory<StoriesViewModel> {
  private final Provider<HackerNewsRepository> repositoryProvider;

  private final Provider<NeuralNetworkAnalyzer> neuralNetworkAnalyzerProvider;

  private final Provider<UserProfileManager> userProfileManagerProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  public StoriesViewModel_Factory(Provider<HackerNewsRepository> repositoryProvider,
      Provider<NeuralNetworkAnalyzer> neuralNetworkAnalyzerProvider,
      Provider<UserProfileManager> userProfileManagerProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    this.repositoryProvider = repositoryProvider;
    this.neuralNetworkAnalyzerProvider = neuralNetworkAnalyzerProvider;
    this.userProfileManagerProvider = userProfileManagerProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public StoriesViewModel get() {
    return newInstance(repositoryProvider.get(), neuralNetworkAnalyzerProvider.get(), userProfileManagerProvider.get(), settingsRepositoryProvider.get());
  }

  public static StoriesViewModel_Factory create(Provider<HackerNewsRepository> repositoryProvider,
      Provider<NeuralNetworkAnalyzer> neuralNetworkAnalyzerProvider,
      Provider<UserProfileManager> userProfileManagerProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new StoriesViewModel_Factory(repositoryProvider, neuralNetworkAnalyzerProvider, userProfileManagerProvider, settingsRepositoryProvider);
  }

  public static StoriesViewModel newInstance(HackerNewsRepository repository,
      NeuralNetworkAnalyzer neuralNetworkAnalyzer, UserProfileManager userProfileManager,
      SettingsRepository settingsRepository) {
    return new StoriesViewModel(repository, neuralNetworkAnalyzer, userProfileManager, settingsRepository);
  }
}
