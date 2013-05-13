package display;

import gameLogic.Map;
import gameLogic.Spray;

import java.util.List;

public interface DisplayRefreshable {
	public abstract void refreshMapElements();
	public abstract void provideMapData(Map m, List<Spray> sprays);
	public abstract void gameEnded(long round);
}

