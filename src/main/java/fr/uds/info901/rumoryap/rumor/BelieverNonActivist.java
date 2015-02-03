package fr.uds.info901.rumoryap.rumor;

import java.util.List;

import fr.uds.info901.rumoryap.SocialLink;

public class BelieverNonActivist extends AbstractRumorState {

	@Override
	public String getColorInGraph() {
		return "fill-color: rgb(255,0,255);";
	}

	@Override
	public void spread(List<SocialLink> friendList) {
		
	}



}
