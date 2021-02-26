package xyz.ramil.catfact;

import moxy.MvpView;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import xyz.ramil.catfact.data.model.CatFactModel;
import xyz.ramil.catfact.model.Facts;

@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface MainView extends MvpView {

    void lockView(boolean is);

}