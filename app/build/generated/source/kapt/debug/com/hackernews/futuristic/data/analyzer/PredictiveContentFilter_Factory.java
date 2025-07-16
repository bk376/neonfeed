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
public final class PredictiveContentFilter_Factory implements Factory<PredictiveContentFilter> {
  @Override
  public PredictiveContentFilter get() {
    return newInstance();
  }

  public static PredictiveContentFilter_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static PredictiveContentFilter newInstance() {
    return new PredictiveContentFilter();
  }

  private static final class InstanceHolder {
    private static final PredictiveContentFilter_Factory INSTANCE = new PredictiveContentFilter_Factory();
  }
}
