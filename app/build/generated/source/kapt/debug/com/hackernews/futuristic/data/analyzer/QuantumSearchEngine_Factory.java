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
public final class QuantumSearchEngine_Factory implements Factory<QuantumSearchEngine> {
  @Override
  public QuantumSearchEngine get() {
    return newInstance();
  }

  public static QuantumSearchEngine_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static QuantumSearchEngine newInstance() {
    return new QuantumSearchEngine();
  }

  private static final class InstanceHolder {
    private static final QuantumSearchEngine_Factory INSTANCE = new QuantumSearchEngine_Factory();
  }
}
