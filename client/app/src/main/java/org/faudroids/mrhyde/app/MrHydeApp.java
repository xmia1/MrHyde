package org.faudroids.mrhyde.app;


import android.app.Application;

import com.karumi.dexter.Dexter;

import org.faudroids.mrhyde.BuildConfig;
import org.faudroids.mrhyde.bitbucket.BitbucketModule;
import org.faudroids.mrhyde.git.GitModule;
import org.faudroids.mrhyde.github.GitHubModule;
import org.faudroids.mrhyde.jekyll.JekyllModule;

import timber.log.Timber;

public class MrHydeApp extends Application {

  private AppComponent component;

  @Override
  public void onCreate() {
    super.onCreate();

    // setup dependency injection
    component = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .gitHubModule(new GitHubModule())
        .jekyllModule(new JekyllModule())
        .gitModule(new GitModule())
        .bitbucketModule(new BitbucketModule())
        .build();

    // setup logging
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      Timber.plant(new Timber.DebugTree());
      // Fabric.with(this, new Crashlytics());
      // Timber.plant(new CrashReportingTree());
    }

    // setup permission requests
    Dexter.initialize(this);
  }

  public AppComponent getComponent() {
    return component;
  }


  /*
  private static final class CrashReportingTree extends Timber.HollowTree {

		@Override
		public void e(String msg, Object... args) {
			Crashlytics.log(msg);
		}

		@Override
		public void e(Throwable e, String msg, Object... args) {
			// Crashlytics.log(msg);
			// Crashlytics.logException(e);
		}

		@Override
		public void w(String msg, Object... args) {
			Crashlytics.log(msg);
		}

		@Override
		public void w(Throwable e, String msg, Object... args) {
			// Crashlytics.log(msg);
			// Crashlytics.logException(e);
		}

	}
	*/
}
