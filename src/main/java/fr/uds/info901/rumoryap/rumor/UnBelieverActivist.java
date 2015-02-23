package fr.uds.info901.rumoryap.rumor;

import java.util.List;

import fr.uds.info901.rumoryap.SocialLink;

public class UnBelieverActivist extends AbstractRumorState {
	public static int  THRESHOLD = 0;
	public static String CLASS_NAME = BelieverActivist.class.getSimpleName();
	@Override
	public String getColorInGraph() {
		return "fill-color: rgb(0,255,0);";
	}

	@Override
	public void spread(List<SocialLink> friendList) {
		for(SocialLink socialLink : friendList){
			socialLink.forwardRumor();
		}
	}
}
