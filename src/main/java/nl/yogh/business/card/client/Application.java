package nl.yogh.business.card.client;

import nl.yogh.business.card.client.di.ApplicationGinjector;
import nl.yogh.business.card.client.resources.R;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Application implements EntryPoint {
  @Override
  public void onModuleLoad() {
    ApplicationGinjector.INSTANCE.inject(this);

    R.init();

    RootPanel.get().add(ApplicationGinjector.INSTANCE.getApplicationRootView());
  }
}
