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
public final class CommentThreadAnalyzer_Factory implements Factory<CommentThreadAnalyzer> {
  @Override
  public CommentThreadAnalyzer get() {
    return newInstance();
  }

  public static CommentThreadAnalyzer_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CommentThreadAnalyzer newInstance() {
    return new CommentThreadAnalyzer();
  }

  private static final class InstanceHolder {
    private static final CommentThreadAnalyzer_Factory INSTANCE = new CommentThreadAnalyzer_Factory();
  }
}
