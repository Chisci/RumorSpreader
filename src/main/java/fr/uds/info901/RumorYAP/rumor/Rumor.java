package fr.uds.info901.RumorYAP.rumor;

public class Rumor {
	private AbstractRumorState rumorState;

	public AbstractRumorState getRumorState() {
		return rumorState;
	}

	public void setRumorState(AbstractRumorState rumorState) {
		this.rumorState = rumorState;
	}
}
