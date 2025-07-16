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
public final class NeuralSynthesizer_Factory implements Factory<NeuralSynthesizer> {
  @Override
  public NeuralSynthesizer get() {
    return newInstance();
  }

  public static NeuralSynthesizer_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static NeuralSynthesizer newInstance() {
    return new NeuralSynthesizer();
  }

  private static final class InstanceHolder {
    private static final NeuralSynthesizer_Factory INSTANCE = new NeuralSynthesizer_Factory();
  }
}
