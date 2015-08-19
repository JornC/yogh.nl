package nl.yogh.business.card.client;

import nl.yogh.business.card.client.di.ApplicationGinjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Application implements EntryPoint {
  @Override
  public void onModuleLoad() {
    ApplicationGinjector.INSTANCE.inject(this);

    RootPanel.get().add(ApplicationGinjector.INSTANCE.getApplicationRootView());
  }
}
