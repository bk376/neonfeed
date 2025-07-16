package com.hackernews.futuristic.data.analyzer;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class NeuralNetworkAnalyzer_Factory implements Factory<NeuralNetworkAnalyzer> {
  @Override
  public NeuralNetworkAnalyzer get() {
    return newInstance();
  }

  public static NeuralNetworkAnalyzer_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static NeuralNetworkAnalyzer newInstance() {
    return new NeuralNetworkAnalyzer();
  }

  private static final class InstanceHolder {
    private static final NeuralNetworkAnalyzer_Factory INSTANCE = new NeuralNetworkAnalyzer_Factory();
  }
}
