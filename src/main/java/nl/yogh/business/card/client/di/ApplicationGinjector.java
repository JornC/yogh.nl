package nl.yogh.business.card.client.di;

import nl.yogh.business.card.client.Application;
import nl.yogh.business.card.client.ApplicationRootView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(value = ApplicationClientModule.class)
public interface ApplicationGinjector extends Ginjector {
  ApplicationGinjector INSTANCE = GWT.create(ApplicationGinjector.class);

  void inject(Application application);

  ApplicationRootView getApplicationRootView();
}
