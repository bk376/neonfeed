package com.hackernews.futuristic.data.profile;

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
public final class UserProfileManager_Factory implements Factory<UserProfileManager> {
  @Override
  public UserProfileManager get() {
    return newInstance();
  }

  public static UserProfileManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static UserProfileManager newInstance() {
    return new UserProfileManager();
  }

  private static final class InstanceHolder {
    private static final UserProfileManager_Factory INSTANCE = new UserProfileManager_Factory();
  }
}
