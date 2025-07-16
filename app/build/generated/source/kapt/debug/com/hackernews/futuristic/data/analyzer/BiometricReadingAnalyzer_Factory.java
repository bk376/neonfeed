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
public final class BiometricReadingAnalyzer_Factory implements Factory<BiometricReadingAnalyzer> {
  @Override
  public BiometricReadingAnalyzer get() {
    return newInstance();
  }

  public static BiometricReadingAnalyzer_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static BiometricReadingAnalyzer newInstance() {
    return new BiometricReadingAnalyzer();
  }

  private static final class InstanceHolder {
    private static final BiometricReadingAnalyzer_Factory INSTANCE = new BiometricReadingAnalyzer_Factory();
  }
}
